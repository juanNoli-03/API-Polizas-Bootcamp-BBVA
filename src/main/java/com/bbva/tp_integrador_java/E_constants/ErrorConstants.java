package com.bbva.tp_integrador_java.E_constants;

public class ErrorConstants {

    public static final String CLIENTE_NO_ENCONTRADO = "ERROR! No se encontró ningún Cliente con el ID ingresado.";

    public static final String POLIZA_NO_ENCONTRADA = "ERROR! No se encontró ningúna Poliza con el ID ingresado.";

    public static final String FECHA_NO_VALIDA = "ERROR! Las fechas ingresadas no son validas. " +
            "La FECHA DE VENCIMIENTO no puede SER MENOR a la FECHA DE EMISIÓN.";

    public static final String CODIGO_POLIZA_NO_VALIDO = "ERROR! El Codigo de Poliza ingresado ya existe!";

    public static final String TIPO_SEGURO_NO_ENCONTRADO = "ERROR! No se encontró ningun Tipo de Seguro con el/los ID ingresado/s";

    public static final String CREDENCIALES_INVALIDAS = "ERROR! Credenciales invalidas!";

    public static final String ERROR_EN_TRANSACCION = "ERROR! Ocurrió un error al querer guardar la Poliza en la BD!";
}
