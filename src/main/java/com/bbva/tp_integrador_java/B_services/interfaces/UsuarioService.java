package com.bbva.tp_integrador_java.B_services.interfaces;

import com.bbva.tp_integrador_java.D_dtos.usuarioDTO.CrearUsuarioDTO;
import com.bbva.tp_integrador_java.D_dtos.usuarioDTO.UsuarioDTO;
import com.bbva.tp_integrador_java.D_models.Usuario;
import java.util.List;

public interface UsuarioService {

    //1- Obtener Usuario por username.
    Usuario getByUsername(final String username);

    //2- Obtener lista de Usuarios.
    List<UsuarioDTO> listar ();

    //3- Crear Usuario.
    void signUp(final CrearUsuarioDTO crearUsuarioDTO);
}
