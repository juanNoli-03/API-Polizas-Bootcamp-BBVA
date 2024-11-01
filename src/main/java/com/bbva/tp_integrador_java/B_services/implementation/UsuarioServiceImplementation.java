package com.bbva.tp_integrador_java.B_services.implementation;

import com.bbva.tp_integrador_java.B_services.interfaces.UsuarioService;
import com.bbva.tp_integrador_java.D_dtos.usuarioDTO.CrearUsuarioDTO;
import com.bbva.tp_integrador_java.D_dtos.usuarioDTO.UsuarioDTO;
import com.bbva.tp_integrador_java.D_models.Usuario;
import com.bbva.tp_integrador_java.C_repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImplementation implements UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImplementation.class);

    @Autowired
    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    //1- Obtener Usuario por username.
    public Usuario getByUsername(final String username) {
        return usuarioRepository.findByUsername(username);
    }

    //2- Obtener lista de Usuarios.
    public List<UsuarioDTO> listar() {
        List<Usuario> list = usuarioRepository.findAll();
        return list.stream().map(usuario ->
                new UsuarioDTO().fromUsuario(usuario)
        ).toList();
    }

    //3- Crear Usuario.
    public void signUp(final CrearUsuarioDTO crearUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsername(crearUsuarioDTO.getUsername());
        usuario.setActivo(Boolean.TRUE);
        usuario.setPassword(passwordEncoder.encode(crearUsuarioDTO.getPassword()));
        usuario.setRoles(crearUsuarioDTO.getRoles());
        usuarioRepository.save(usuario);
        logger.info("Registro exitoso...");
    }

}