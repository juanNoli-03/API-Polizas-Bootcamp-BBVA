package com.bbva.tp_integrador_java.D_models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Notations importantes
//----------------------------
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//----------------------------

//Como nuestra clase "TipoSeguro" es una tabla en mi BD, vamos a definirla como "Entity" y "Table" haciendo
//referencia a su nombre dentro de la BD.
@Entity
@Table (name = "tipos_seguros")
public class TipoSeguro {

    //Definicion del ID de mi tabla "tipos_seguros"
    //----------------------------
    @JsonIgnore //Agregamos esta notation para ignorar los ID en la respuesta del tipo Json
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Lo definimos como "autoincremental"
    @Column(name = "id")
    private long id;
    //----------------------------

    //Defincion de los atributos y creacion de las columnas
    //----------------------------
    @Column(name = "tipo_seguro", nullable = false)
    private String tipoSeguro;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private double valor;
    //----------------------------
}