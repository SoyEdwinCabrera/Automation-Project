package org.portfolio;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.portfolio.config.TestVariables;
import org.portfolio.page.InventoryPage;
import org.portfolio.page.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Login/Logout Tests")
public class LoginLogoutTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeEach
    public void setUp() {
        // Configurar Safari
        SafariOptions options = new SafariOptions();
        options.setCapability("safari:automaticInspection", false);
        
        // Inicializar el driver
        driver = new SafariDriver(options);
        
        // Inicializar páginas
        loginPage = new LoginPage(driver, TestVariables.BASE_URL);
    }
    
    @AfterEach
    public void tearDown() throws InterruptedException {
        waitForDemoVisualization();
        if (driver != null) {
            driver.quit();
        }
    }

    // Método de visualización para demostraciones
    private void waitForDemoVisualization() {
        try {
            Thread.sleep(TestVariables.VISUALIZATION_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Login y Logout para usuario estándar")
    public void testLoginLogoutStandardUser() {
        System.out.println("""
            
            ************************************************
            TEST LOGIN Y LOGOUT PARA USUARIO ESTÁNDAR
            ************************************************
            """);
        // Login
        loginPage.login(TestVariables.STANDARD_USERNAME, TestVariables.PASSWORD);
        
        // Verificar login exitoso
        assertTrue(loginPage.isLoginSuccessful(), "Login failed with standard user");
        
        // Inicializar inventoryPage después del login exitoso
        inventoryPage = new InventoryPage(driver);
        
        // Verificar que estamos en la página de inventario
        assertTrue(inventoryPage.isOnInventoryPage(), "Not on inventory page after login");
        assertEquals("Products", inventoryPage.getProductLabelText(), "Product label text doesn't match");
        
        // Ejecutar logout
        inventoryPage.logout();
        
        // Verificar logout exitoso
        assertTrue(inventoryPage.isLogoutSuccessful(), "Logout failed");
        System.out.println("""
            
            ************************************************
            
            """);
    }
    
    @Test
    @DisplayName("Login y Logout para todos los usuarios válidos")
    public void testLoginLogoutAllUsers() {
        System.out.println("""
            
            ************************************************
            TEST LOGIN Y LOGOUT PARA TODOS LOS USUARIOS
            ************************************************
            """);
        // Iterar por todos los usuarios válidos
        for (String username : TestVariables.ALL_USERNAMES) {
            System.out.println("\n--- Probando usuario: " + username + " ---");
            
            // Login
            loginPage.login(username, TestVariables.PASSWORD);
            
            // Verificar login exitoso
            assertTrue(loginPage.isLoginSuccessful(), 
                      "Login failed with user: " + username);
            
            // Inicializar inventoryPage después del login exitoso
            inventoryPage = new InventoryPage(driver);
            
            // Verificar que estamos en la página de inventario
            assertTrue(inventoryPage.isOnInventoryPage(), 
                      "Not on inventory page after login with user: " + username);
            
            // Ejecutar logout
            inventoryPage.logout();
            
            // Verificar logout exitoso
            assertTrue(inventoryPage.isLogoutSuccessful(), 
                      "Logout failed for user: " + username);
            
            // Pausa breve entre usuarios
            waitForDemoVisualization();
            System.out.println("""
                
                ************************************************
                
                """);
        }
    }
}