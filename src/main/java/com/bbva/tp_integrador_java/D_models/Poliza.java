package com.bbva.tp_integrador_java.D_models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

//Notations importantes
//----------------------------
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//----------------------------

//Como nuestra clase "Poliza" es una tabla en mi BD, vamos a definirla como "Entity" y "Table" haciendo
//referencia a su nombre dentro de la BD.
@Entity
@Table (name = "polizas")
public class Poliza {

    //Definicion del ID de mi tabla "polizas"
    //----------------------------
    @JsonIgnore //Agregamos esta notation para ignorar los ID en la respuesta del tipo Json
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Lo definimos como "autoincremental"
    @Column(name = "id")
    private long id;
    //----------------------------

    //Defincion de los atributos y creacion de las columnas
    //----------------------------
    @NotNull(message = "El codigo de la Poliza no puede ser NULO.")
    @Size(min = 4, max = 4, message = "El codigo de la Poliza debe ser de 4 DIGITOS EN TOTAL. Un # y 3 numeros.")
    @Pattern(regexp = "^#[0-9]{3}$", message = "El codigo de la Poliza debe comenzar con '#' seguido de 3 números.")
    private String codigoPoliza;

    @NotNull(message = "La fecha de emision no puede ser NULA.")
    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @NotNull(message = "La fecha de vencimiento no puede ser NULA.")
    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @NotNull(message = "El monto de cobertura no puede ser NULO.")
    @Min(value = 100000, message = "El monto de cobertura debe ser minimo desde los 100000 para arriba")
    @Column(name = "monto_cobertura")
    private float montoCobertura;
    //----------------------------

    //Definimos las relaciones de Poliza con Cliente y TipoSeguro
    //---------------------------

    //1- Relacion Many to One con Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    //2- Relacion Many to Many con TipoSeguro
    @ManyToMany
    @JoinTable(name = "polizas_x_tipos_seguros", // Nombre de la tabla intermedia
               joinColumns = @JoinColumn(name = "id_poliza"), // FK de la entidad actual
               inverseJoinColumns = @JoinColumn(name = "id_tipo_seguro") // FK de la otra entidad
    )
    private List<TipoSeguro> tipoSeguro;

    //----------------------------
}