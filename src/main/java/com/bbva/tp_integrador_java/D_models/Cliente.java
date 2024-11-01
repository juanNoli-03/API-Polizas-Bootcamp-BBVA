package com.bbva.tp_integrador_java.D_models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

//Notations importantes
//----------------------------
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//----------------------------

//Como nuestra clase "Cliente" es una tabla en mi BD, vamos a definirla como "Entity" y "Table" haciendo
//referencia a su nombre dentro de la BD.
@Entity
@Table (name = "clientes")
public class Cliente {

    //Definicion del ID de mi tabla "clientes"
    //----------------------------
    @JsonIgnore //Agregamos esta notation para ignorar los ID en la respuesta del tipo Json
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Lo definimos como "autoincremental
    @Column(name = "id")
    private long idCliente;
    //----------------------------

    //Defincion de los atributos y creacion de las columnas
    //----------------------------
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;
    //----------------------------
}