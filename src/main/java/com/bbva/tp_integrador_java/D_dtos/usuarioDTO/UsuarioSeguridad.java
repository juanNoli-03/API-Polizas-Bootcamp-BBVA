package com.bbva.tp_integrador_java.D_dtos.usuarioDTO;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

//Notations importantes
//----------------------------
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//----------------------------

public class UsuarioSeguridad {

    private Long id;
    private String username;
    private List<String> roles;
    private LocalDateTime createdAt;
    private LocalDateTime expirationDate;

    public UsuarioSeguridad (Claims claims) {
        this.id = Long.getLong(claims.getId());
        this.username = claims.getSubject();
        this.roles = Arrays.asList(claims.get("roles", String.class).split(","));
        this.createdAt = Instant.ofEpochMilli(claims.getIssuedAt().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        this.expirationDate = Instant.ofEpochMilli(claims.getExpiration().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}