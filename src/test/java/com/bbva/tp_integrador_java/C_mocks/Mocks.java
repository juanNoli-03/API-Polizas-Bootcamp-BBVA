package com.bbva.tp_integrador_java.C_mocks;

import com.bbva.tp_integrador_java.D_dtos.clienteDTO.ClienteCompletoDTO;
import com.bbva.tp_integrador_java.D_dtos.polizaDTO.PolizaCompletaDTO;
import com.bbva.tp_integrador_java.D_dtos.polizaDTO.PolizaSimpleDTO;
import com.bbva.tp_integrador_java.D_dtos.tipoSeguroDTO.TipoSeguroCompletoDTO;
import com.bbva.tp_integrador_java.D_dtos.usuarioDTO.CrearUsuarioDTO;
import com.bbva.tp_integrador_java.D_dtos.usuarioDTO.UsuarioDTO;
import com.bbva.tp_integrador_java.D_models.Cliente;
import com.bbva.tp_integrador_java.D_models.Poliza;
import com.bbva.tp_integrador_java.D_models.TipoSeguro;
import com.bbva.tp_integrador_java.D_models.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mocks {

    public static PolizaSimpleDTO polizaSimpleDTOMock() {

        //Inicializamos vacia la Poliza.
        PolizaSimpleDTO polizaSimpleDTO = PolizaSimpleDTO.builder().build();

        //Seteamos los valores.
        polizaSimpleDTO.setCodigoPoliza("#489");
        polizaSimpleDTO.setFechaEmision(LocalDate.of(2024, 10, 11));
        polizaSimpleDTO.setFechaVencimiento(LocalDate.of(2025, 10, 11));
        polizaSimpleDTO.setMontoCobertura(400000);

        return polizaSimpleDTO;
    }

    public static Poliza polizaMock() {

        //Inicializamos vacia la Poliza.
        Poliza poliza = Poliza.builder().build();

        //Seteamos los valores.
        poliza.setCodigoPoliza("#489");
        poliza.setFechaEmision(LocalDate.of(2024, 10, 11));
        poliza.setFechaVencimiento(LocalDate.of(2025, 10, 11));
        poliza.setMontoCobertura(400000);
        poliza.setCliente(Cliente.builder()
                .idCliente(1)
                .nombre("Juan Manuel")
                .apellido("Noli")
                .dni("45233126")
                .email("juanmanuelnoli03@gmail.com")
                .telefono("1123861789")
                .fechaAlta(LocalDate.of(2024, 5, 12))
                .build());
        List<TipoSeguro> listaTiposSeguos = new ArrayList<TipoSeguro>();
        listaTiposSeguos.add(TipoSeguro.builder()
                .tipoSeguro("Salud")
                .descripcion("Seguro de Salud")
                .valor(10000)
                .build());
        poliza.setTipoSeguro(listaTiposSeguos);
        return poliza;
    }

    public static PolizaCompletaDTO polizaCompletaDTOMock() {

        //Inicializamos vacia la Poliza.
        PolizaCompletaDTO polizaCompletaDTO = PolizaCompletaDTO.builder().build();

        //Seteamos los valores
        polizaCompletaDTO.setCodigoPoliza(polizaMock().getCodigoPoliza());
        polizaCompletaDTO.setFechaEmision(polizaMock().getFechaEmision());
        polizaCompletaDTO.setFechaVencimiento(polizaMock().getFechaVencimiento());
        polizaCompletaDTO.setMontoCobertura(polizaMock().getMontoCobertura());
        polizaCompletaDTO.setCliente(new ClienteCompletoDTO(polizaMock().getCliente()));
        polizaCompletaDTO.setTipoSeguro(
                polizaMock().getTipoSeguro().stream()
                        .map(TipoSeguroCompletoDTO::new)
                        .collect(Collectors.toList())
        );

        return polizaCompletaDTO;
    }

    public static Cliente clienteMock () {

        //Inicializamos vacia la Poliza.
        Cliente cliente = Cliente.builder().build();

        //Seteamos los valores
        cliente.setNombre("Juan");
        cliente.setApellido("Noli");
        cliente.setDni("44569874");
        cliente.setEmail("a2003@gmail.com");
        cliente.setTelefono("1128789635");
        cliente.setFechaAlta(LocalDate.now());

        return cliente;
    }

    public static TipoSeguro tipoSeguroMock () {

        //Inicializamos vacia la Poliza.
        TipoSeguro tipoSeguro = TipoSeguro.builder().build();

        //Seteamos los valores
        tipoSeguro.setTipoSeguro("Seguro x");
        tipoSeguro.setDescripcion("Descripcion x");
        tipoSeguro.setValor(10000000);

        return tipoSeguro;
    }

    public static CrearUsuarioDTO crearUsuarioDTOMock() {

        //Inicializamos vacio el CrearUsuarioDTO.
        CrearUsuarioDTO crearUsuarioDTO = CrearUsuarioDTO.builder().build();

        //Seteamos los valores.
        crearUsuarioDTO.setUsername("Juan");
        crearUsuarioDTO.setPassword("123456");
        crearUsuarioDTO.setRoles("ADMIN, USER");

        return crearUsuarioDTO;
    }

    public static UsuarioDTO usuarioDTOMock() {

        //Inicializamos vacio el UsuarioDTO.
        UsuarioDTO usuarioDTO = UsuarioDTO.builder().build();

        //Seteamos los valores
        usuarioDTO.setId(1l);
        usuarioDTO.setActivo(true);
        usuarioDTO.setUsername("Juan");
        usuarioDTO.setRoles(List.of("USER", "ADMIN"));

        return usuarioDTO;
    }

    public static Usuario usuarioMock() {

        //Inicializamos vacio el UsuarioDTO.
        Usuario usuario = Usuario.builder().build();

        //Seteamos los valores
        usuario.setId(1l);
        usuario.setActivo(true);
        usuario.setUsername("Juan");
        usuario.setPassword("123456");
        usuario.setRoles("USER, ADMIN");

        return usuario;
    }
}