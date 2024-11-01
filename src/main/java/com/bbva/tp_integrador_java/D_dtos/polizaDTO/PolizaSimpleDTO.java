package com.bbva.tp_integrador_java.D_dtos.polizaDTO;

//Imports importantes
//----------------------------
import com.bbva.tp_integrador_java.D_models.Poliza;
import lombok.*;
import java.time.LocalDate;
//----------------------------

//Notations importantes
//----------------------------
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor (access = AccessLevel.PUBLIC)
//----------------------------

public class PolizaSimpleDTO {

    //Definicion de los atributos
    //----------------------------
    private String codigoPoliza;

    private LocalDate fechaEmision;

    private LocalDate fechaVencimiento;

    private float montoCobertura;
    //----------------------------

    //Constructor personalizado para lograr el mapeo de la entidad
    //en el DTO.
    //---------------------------------------------------------
    public void guardarDTO (final Poliza poliza) {

        poliza.setCodigoPoliza(this.codigoPoliza);

        poliza.setFechaEmision(this.fechaEmision);

        poliza.setFechaVencimiento(this.fechaVencimiento);

        poliza.setMontoCobertura(this.montoCobertura);
    }
    //---------------------------------------------------------

}

