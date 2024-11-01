package com.bbva.tp_integrador_java.B_services.interfaces;

import java.util.Map;

public interface AuthService {

    //1- Login.
    Map<String, Object> signIn (final String username, final String password);

    //2- Generar Hash.
    public String generarHash (String password);
}
