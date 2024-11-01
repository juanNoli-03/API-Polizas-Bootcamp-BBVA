package com.bbva.tp_integrador_java.D_dtos.usuarioDTO;

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

public class CrearUsuarioDTO {

    private String username;
    private String password;
    private String roles;
}