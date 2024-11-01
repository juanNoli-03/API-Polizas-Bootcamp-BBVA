package com.bbva.tp_integrador_java.D_dtos.clienteDTO;

//Imports importantes
//----------------------------
import com.bbva.tp_integrador_java.D_models.Cliente;
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

public class ClienteCompletoDTO {

    //Definicion de los atributos
    //----------------------------
    private String nombre;

    private String apellido;

    private String dni;

    private String email;

    private String telefono;

    private LocalDate fechaAlta;
    //----------------------------

    //Constructor personalizado para lograr el mapeo de la entidad
    //en el DTO.
    //---------------------------------------------------------
    public ClienteCompletoDTO (final Cliente cliente) {

        this.nombre = cliente.getNombre();

        this.apellido = cliente.getApellido();

        this.dni = cliente.getDni();

        this.email = cliente.getEmail();

        this.telefono = cliente.getTelefono();

        this.fechaAlta = cliente.getFechaAlta();
    }
    //---------------------------------------------------------
}