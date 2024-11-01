package com.bbva.tp_integrador_java.D_dtos.usuarioDTO;

import com.bbva.tp_integrador_java.D_models.Usuario;
import lombok.*;
import java.util.Arrays;
import java.util.List;

//Notations importantes
//----------------------------
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//----------------------------

public class UsuarioDTO {

    //Definicion de los atributos
    //----------------------------
    private Long id;
    private String username;
    private Boolean activo;
    private List<String> roles;
    //----------------------------

    //Constructor personalizado para lograr el mapeo de la entidad
    //en el DTO.
    //---------------------------------------------------------
    public UsuarioDTO fromUsuario(final Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.activo = usuario.getActivo();
        this.roles = Arrays.asList(usuario.getRoles().split(","));
        return this;
    }
    //---------------------------------------------------------
}