package com.bbva.tp_integrador_java.D_models;

import jakarta.persistence.*;
import lombok.*;

//Notations importantes
//----------------------------
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//----------------------------

//Como nuestra clase "Usuario" es una tabla en mi BD, vamos a definirla como "Entity" y "Table" haciendo
//referencia a su nombre dentro de la BD.
@Entity
@Table(name = "usuarios")
public class Usuario {

    //Definicion del ID de mi tabla "usuarios"
    //----------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    //----------------------------

    //Defincion de los atributos y creacion de las columnas
    //----------------------------
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "roles")
    private String roles;
    //----------------------------
}