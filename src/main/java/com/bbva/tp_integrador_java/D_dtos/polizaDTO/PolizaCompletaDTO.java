package com.bbva.tp_integrador_java.D_dtos.polizaDTO;

//Imports importantes
//----------------------------
import com.bbva.tp_integrador_java.D_dtos.clienteDTO.ClienteCompletoDTO;
import com.bbva.tp_integrador_java.D_dtos.tipoSeguroDTO.TipoSeguroCompletoDTO;
import com.bbva.tp_integrador_java.D_models.Poliza;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
//----------------------------

//Notations importantes
//----------------------------
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor (access = AccessLevel.PUBLIC)
//----------------------------

public class PolizaCompletaDTO {

    //Definicion de los atributos
    //----------------------------
    private String codigoPoliza;

    private LocalDate fechaEmision;

    private LocalDate fechaVencimiento;

    private float montoCobertura;

    private ClienteCompletoDTO cliente;

    private List <TipoSeguroCompletoDTO> tipoSeguro;
    //----------------------------

    //Constructor personalizado para lograr el mapeo de la entidad
    //en el DTO.
    //---------------------------------------------------------
    public PolizaCompletaDTO (final Poliza poliza) {

        this.codigoPoliza = poliza.getCodigoPoliza();

        this.fechaEmision = poliza.getFechaEmision();

        this.fechaVencimiento = poliza.getFechaVencimiento();

        this.montoCobertura = poliza.getMontoCobertura();

        this.cliente = new ClienteCompletoDTO(poliza.getCliente());

        this.tipoSeguro = poliza.getTipoSeguro().stream()
                            .map(TipoSeguroCompletoDTO::new)
                            .collect(Collectors.toList());
    }
    //---------------------------------------------------------
}