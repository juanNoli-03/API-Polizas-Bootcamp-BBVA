package com.bbva.tp_integrador_java.D_dtos.tipoSeguroDTO;

//Imports importantes
//----------------------------
import com.bbva.tp_integrador_java.D_models.TipoSeguro;
import lombok.*;
//----------------------------

//Notations importantes
//----------------------------
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor (access = AccessLevel.PUBLIC)
//----------------------------

public class TipoSeguroCompletoDTO {

    //Definicion de los atributos
    //----------------------------
    private String tipoSeguro;

    private String descripcion;

    private double valor;
    //----------------------------

    //Constructor personalizado para lograr el mapeo de la entidad
    //en el DTO.
    //---------------------------------------------------------
    public TipoSeguroCompletoDTO(final TipoSeguro tipoSeguro) {

        this.tipoSeguro = tipoSeguro.getTipoSeguro();

        this.descripcion = tipoSeguro.getDescripcion();

        this.valor = tipoSeguro.getValor();
    }
    //---------------------------------------------------------
}