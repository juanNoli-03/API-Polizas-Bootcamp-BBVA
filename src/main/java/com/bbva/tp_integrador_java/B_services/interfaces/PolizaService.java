package com.bbva.tp_integrador_java.B_services.interfaces;

import com.bbva.tp_integrador_java.D_dtos.polizaDTO.PolizaCompletaDTO;
import com.bbva.tp_integrador_java.D_dtos.polizaDTO.PolizaSimpleDTO;
import com.bbva.tp_integrador_java.D_models.Poliza;
import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Set;

public interface PolizaService {

    //1- Crear una nueva Poliza en nuestra BD.
    Poliza save (final PolizaSimpleDTO polizaSimpleDTO, final long idCliente, Set<Long>tiposSeguros) throws Exception;

    //2- Obtener la lista de todas las Polizas de nuestra BD.
    //2.1- Mediante mapping
    List <PolizaCompletaDTO> findAllMapping ();
    //2.2- Mediante Query
    List <Poliza> findAllQuery();

    //3- Actualizar la informacion de una Poliza de nuestra BD.
    Poliza update (final long idPoliza, final PolizaSimpleDTO polizaModificada, final long idCliente,
                   final Set <Long> tiposSeguros) throws MessagingException;

    //4- Eliminamos una Poliza de nuestra BD.
    void delete (final long idPoliza) throws MessagingException;
}
