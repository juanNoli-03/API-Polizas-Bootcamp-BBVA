package com.bbva.tp_integrador_java.B_services.implementation;

import com.bbva.tp_integrador_java.B_services.interfaces.PolizaService;
import com.bbva.tp_integrador_java.C_repositories.ClienteRepository;
import com.bbva.tp_integrador_java.C_repositories.PolizaRepository;
import com.bbva.tp_integrador_java.C_repositories.TipoSeguroRepository;
import com.bbva.tp_integrador_java.D_dtos.polizaDTO.PolizaCompletaDTO;
import com.bbva.tp_integrador_java.D_dtos.polizaDTO.PolizaSimpleDTO;
import com.bbva.tp_integrador_java.D_models.Cliente;
import com.bbva.tp_integrador_java.D_models.Poliza;
import com.bbva.tp_integrador_java.D_models.TipoSeguro;
import com.bbva.tp_integrador_java.E_constants.ErrorConstants;
import com.bbva.tp_integrador_java.E_exceptions.CustomException;
import jakarta.mail.MessagingException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PolizaServiceImplementation implements PolizaService {

    private static final Logger logger = LoggerFactory.getLogger(PolizaServiceImplementation.class);

    @Autowired
    private PolizaRepository polizaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TipoSeguroRepository tipoSeguroRepository;

    @Autowired
    private EmailService emailService;

    //1- Crear una nueva Poliza en nuestra BD.
    //----------------------------------------
    @Override
    @Transactional
    @Cacheable("polizasCache") //Implementamos caché
    public Poliza save(final PolizaSimpleDTO polizaSimpleDTO, final long idCliente, final Set<Long> tiposSeguros)
            throws CustomException, MessagingException {

        logger.info("Iniciando la generacion de una nueva Poliza en la BD...");

        //1- Guardamos la PolizaDTO en Poliza
        //----------------------------
        //Creamos inicialmente la Poliza vacia.
        Poliza poliza = Poliza.builder().build();

        logger.info("Inicializamos una Poliza vacia.");

        //Antes de guardar los datos del DTO en la Poliza, validamos que las fechas sean validas...
        if (validarFechas(polizaSimpleDTO.getFechaEmision(), polizaSimpleDTO.getFechaVencimiento())) {

            throw new CustomException(HttpStatus.CONFLICT, ErrorConstants.FECHA_NO_VALIDA);
        }

        //Tambien validamos que ninguna Poliza con el mismo Codigo (Ej: #235) de Poliza.
        if (validarPoliza(polizaSimpleDTO.getCodigoPoliza())) {

            throw new CustomException(HttpStatus.CONFLICT, ErrorConstants.CODIGO_POLIZA_NO_VALIDO);
        }

        //Finalmente, si no hay errores guardamos el DTO en la Poliza.
        logger.info("Guardamos la Poliza en el DTO");
        polizaSimpleDTO.guardarDTO(poliza);
        //----------------------------

        //2- Obtenemos el Cliente por ID y lo guardamos en la Poliza.
        //----------------------------
        //Antes de setear al Cliente en la Poliza, verificamos que exista en la BD...
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ErrorConstants.CLIENTE_NO_ENCONTRADO));

        //Si existe, lo seteamos.
        logger.info("Seteamos el Cliente en la Poliza.");
        poliza.setCliente(cliente);
        //----------------------------

        //3- Obtenemos los Tipos de Seguro y guardamos el/los TipoSeguro en la Poliza.
        //----------------------------
        logger.info("Seteamos el Tipo de Seguro en la Poliza");
        poliza.setTipoSeguro(traerTipoSeguro(tiposSeguros));
        //----------------------------

        //4- Si no hubo ningun error, guardamos la Poliza completamente cargada en nuestra BD y
        //mandamos aviso por mail.

        Poliza polizaCreada = polizaRepository.save(poliza);

        logger.info("Poliza guardada con éxito y enviando mail...");

        emailService.sendEmail(polizaCreada.getCodigoPoliza(), "juanmanuel.noli@bbva.com", "Creación de Poliza",
                "creó");

        return polizaCreada;
    }

    //Metodo para recorrer el SET de ID´S de los Tipo de Seguro e ir obteniendo el Tipo de Seguro de la BD
    //y guardandolo en una lista final.
    public List<TipoSeguro> traerTipoSeguro(final Set<Long> tiposSeguros) throws CustomException {

        //Inicializamos la lista
        List<TipoSeguro> listaTipoSeguro = new ArrayList<TipoSeguro>();

        //Recorremos nuestro SET y vamos obteniendo los TipoSeguro de acuerdo al elemento que este
        //dentro del SET.
        for (Long idTipoSeguro : tiposSeguros) {

            //Obtenemos el Tipo de Seguro por ID y lo guardamos en la lista.

            //En caso de que no exista el Tipo de Seguro...
            TipoSeguro tipoSeguro = tipoSeguroRepository.findById(idTipoSeguro)
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ErrorConstants.TIPO_SEGURO_NO_ENCONTRADO));

            //En caso de que exista, lo seteamos.
            listaTipoSeguro.add(tipoSeguro);
        }
        return listaTipoSeguro;
    }
    //----------------------------------------

    //2- Obtener la lista de todas las Polizas de nuestra BD.
    //----------------------------------------

    //2.1- Mediante mapping
    //----------------------------------------
    @Override
    @Cacheable("polizasCache") //Implementamos caché
    public List<PolizaCompletaDTO> findAllMapping() {

        //1- Obtenemos la lista de todas las Polizas almacenadas en nuestra BD.
        logger.info("Obteniendo las Polizas de la BD by Mapping...");
        List<Poliza> listaPolizas = polizaRepository.findAll();

        //2- Las mapeamos y las retornamos.
        return mapearListaPolizas(listaPolizas);
    }

    //Creamos un metodo para mapear la lista de Polizas obtenidas en una nueva lista pero esta vez del tipo
    //PolizaCompletaDTO para poder inicializar a nuestras entidades Cliente y TipoSeguro y mostrar dicha lista.
    private List<PolizaCompletaDTO> mapearListaPolizas(final List<Poliza> listaPolizas) {

        return listaPolizas.stream()
                .map(poliza -> new PolizaCompletaDTO(poliza))
                .collect(Collectors.toList());
    }
    //----------------------------------------

    //2.2- Mediante Query
    //----------------------------------------
    @Override
    @Cacheable("polizasCache") //Implementamos caché
    public List<Poliza> findAllQuery() {

        logger.info("Obteniendo las Polizas de la BD by Query...");
        //Simplemente llamamos a la Query del repository y retornamos la lista.
        return polizaRepository.findAllQuery();
    }
    //----------------------------------------

    //----------------------------------------

    //3- Actualizar la informacion de una Poliza de nuestra BD.
    //----------------------------------------
    @Override
    @CacheEvict(value = "polizasCache", allEntries = true) //Implementamos caché
    public Poliza update(final long idPoliza, final PolizaSimpleDTO polizaModificada, final long idCliente,
                         final Set<Long> tiposSeguros) throws CustomException, MessagingException, TransactionSystemException {

        //1- Obtenemos la Poliza que deseamos modificar mediante el ID. Si no esta presente en la BD, lanzamos excepcion.
        Poliza poliza = polizaRepository.findById(idPoliza)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ErrorConstants.POLIZA_NO_ENCONTRADA));

        //2- Si la Poliza buscada existe, seteamos los valores.
        //---------------------------------------

        //Validamos el Codigo de Poliza
        //En caso de que seteemos un codigo nuevo y ya exista en la BD y pertenezca a una Poliza que no es la Poliza que
        //estamos actualizando....
        if (validarPoliza(polizaModificada.getCodigoPoliza()) && poliza.getId() !=
                polizaRepository.findByCodigoPoliza(polizaModificada.getCodigoPoliza()).get().getId()) {

            throw new CustomException(HttpStatus.CONFLICT, ErrorConstants.CODIGO_POLIZA_NO_VALIDO);
        }

        //Antes de setear el nuevo codigo de la Poliza, lo guardamos para usuarlo en el mail.
        final String codPolizaViejo = poliza.getCodigoPoliza();

        //Si el Codigo de la Poliza no pertenece a otra Poliza de nuestra BD, lo seteamos.
        poliza.setCodigoPoliza(polizaModificada.getCodigoPoliza());

        //Seteamos la Fecha de Emision
        poliza.setFechaEmision(polizaModificada.getFechaEmision());

        //Validamos las fechas
        if (validarFechas(polizaModificada.getFechaEmision(), polizaModificada.getFechaVencimiento())) {

            throw new CustomException(HttpStatus.CONFLICT, ErrorConstants.FECHA_NO_VALIDA);
        }

        //Si las fechas son validas, seteamos la de vencimiento.
        poliza.setFechaVencimiento(polizaModificada.getFechaVencimiento());

        poliza.setMontoCobertura(polizaModificada.getMontoCobertura());

        //- Seteamos el Cliente. Validamos de que exista en la BD.
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ErrorConstants.CLIENTE_NO_ENCONTRADO));

        poliza.setCliente(cliente);

        //- Seteamos los Tipos de Seguro. Validamos que existan en la BD.
        poliza.setTipoSeguro(traerTipoSeguro(tiposSeguros));
        //----------------------------------------

        //Validamos que, al guardar la Poliza nuevamente, no ocurra ninguna excepcion durante
        //el save ().
        Poliza polizaActualizada;
        try {
            polizaActualizada = polizaRepository.save(poliza);

        } catch (TransactionSystemException e) {

            if (e.getRootCause() instanceof ConstraintViolationException) {
                throw (ConstraintViolationException) e.getRootCause();
            }
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorConstants.ERROR_EN_TRANSACCION);
        }

        emailService.sendEmail(codPolizaViejo, "juanmanuel.noli@bbva.com", "Actualización de Poliza",
                "actualizó");

        return polizaActualizada;
    }
    //----------------------------------------

    //4- Eliminamos una Poliza de nuestra BD.
    @Override
    @CacheEvict(value = "polizasCache", allEntries = true) //Implementamos caché
    public void delete(final long idPoliza) throws CustomException, MessagingException {

        //Verificamos si la Poliza existe en la BD. En caso de que no, lanzamos excepción.
        Poliza poliza = polizaRepository.findById(idPoliza)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ErrorConstants.POLIZA_NO_ENCONTRADA));

        //Si la poliza existe, la eliminamos de la BD y mandamos aviso por Email.
        logger.info("Poliza eliminada con éxito y enviando mail...");
        emailService.sendEmail(poliza.getCodigoPoliza(), "juanmanuel.noli@bbva.com", "Eliminación de Poliza",
                "eliminó");

        polizaRepository.delete(poliza);
    }

    //Funciones de Validación
    //----------------------------------------------------------------------------------------------------------------
    public boolean validarFechas(final LocalDate fechaEmision, final LocalDate fechaVencimiento) {

        boolean estado = false;

        if (fechaVencimiento.isBefore(fechaEmision)) {

            estado = true;
        }

        return estado;
    }

    public boolean validarPoliza(final String codigoPoliza) {

        boolean estado = false;

        if (polizaRepository.findByCodigoPoliza(codigoPoliza).isPresent()) {

            estado = true;
        }

        return estado;
    }
    //----------------------------------------------------------------------------------------------------------------
}