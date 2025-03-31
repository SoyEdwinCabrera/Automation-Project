package org.portfolio.config;

import java.util.Arrays;
import java.util.List;

public class TestVariables {
    // URLs
    public static final String BASE_URL = "https://www.saucedemo.com/v1/index.html";
    public static final String INVENTORY_PAGE = "inventory.html";
    
    // Mensajes de error
    public static final String ERROR_USERNAME_REQUIRED = "Epic sadface: Username is required";
    public static final String ERROR_PASSWORD_REQUIRED = "Epic sadface: Password is required";
    public static final String ERROR_LOCKED_USER = "Epic sadface: Sorry, this user has been locked out.";
    
    // Credenciales
    public static final String PASSWORD = "secret_sauce";
    public static final String STANDARD_USERNAME = "standard_user";
    public static final String LOCKED_USERNAME = "locked_out_user";
    public static final String PROBLEM_USERNAME = "problem_user";
    public static final String PERFORMANCE_USERNAME = "performance_glitch_user";
    
    // Lista de todos los usuarios para pruebas de iteración
    public static final List<String> ALL_USERNAMES = Arrays.asList(
        STANDARD_USERNAME,
        PROBLEM_USERNAME,
        PERFORMANCE_USERNAME
        // No incluimos LOCKED_USERNAME porque esa prueba fallaría intencionalmente
    );
    
    // Tiempos de espera
    public static final int DEFAULT_WAIT_TIMEOUT = 15;
    public static final int VISUALIZATION_DELAY = 1500;
}