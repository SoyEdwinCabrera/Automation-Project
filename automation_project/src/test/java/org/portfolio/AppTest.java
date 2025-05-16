package org.portfolio;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.portfolio.config.TestVariables;
import org.portfolio.page.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

@DisplayName("Login Page Tests")
public class AppTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // Inicializar el driver
        driver = new ChromeDriver();
        
        // Inicializar la espera explícita
        wait = new WebDriverWait(driver, Duration.ofSeconds(TestVariables.DEFAULT_WAIT_TIMEOUT));
        
        // Inicializar la página de login
        loginPage = new LoginPage(driver, TestVariables.BASE_URL);
        
        // Esperar a que la página se cargue
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
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
    @DisplayName("Login sin credenciales debe mostrar error de usuario requerido")
    public void emptyLoginShouldShowUsernameRequired() {
        System.out.println("""
            
            ************************************************
            TEST USUARIO REQUERIDO
            ************************************************
            """);
        // Ejecutar login con campos vacíos
        loginPage.login("", "");
        
        // Verificar que aparezca el mensaje de error correcto
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        waitForDemoVisualization();
        
        assertEquals(TestVariables.ERROR_USERNAME_REQUIRED, loginPage.getErrorMessage());
        System.out.println("""
            
            ************************************************
            
            """);
    }
    
    @Test
    @DisplayName("Login solo con usuario debe mostrar error de contraseña requerida")
    public void userOnlyLoginShouldShowPasswordRequired() {
        System.out.println("""
            
            ************************************************
            TEST CONTRASEÑA REQUERIDA
            ************************************************
            """);
        // Ejecutar login solo con usuario
        loginPage.login(TestVariables.STANDARD_USERNAME, "");
        
        // Verificar que aparezca el mensaje de error correcto
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        waitForDemoVisualization();
        
        assertEquals(TestVariables.ERROR_PASSWORD_REQUIRED, loginPage.getErrorMessage());
        System.out.println("""
            
            ************************************************
            
            """);
    }
    
    @Test
    @DisplayName("Login con credenciales correctas debe redirigir al inventario")
    public void validLoginShouldRedirectToInventory() {
        System.out.println("""
            
            ************************************************
            TEST LOGIN Y REDIRECCIÓN AL INVENTARIO
            ************************************************
            """);
        // Ejecutar login con credenciales válidas
        loginPage.login(TestVariables.STANDARD_USERNAME, TestVariables.PASSWORD);
        
        // Verificar redirección a la página de inventario
        wait.until(ExpectedConditions.urlContains(TestVariables.INVENTORY_PAGE));
        waitForDemoVisualization();
        
        assertTrue(driver.getCurrentUrl().contains(TestVariables.INVENTORY_PAGE), 
                   "Login failed - Not redirected to inventory page");
        System.out.println("""
            
            ************************************************
            
            """);
    }
    
    @Test
    @DisplayName("Login con usuario bloqueado debe mostrar mensaje de bloqueo")
    public void lockedUserLoginShouldShowLockedMessage() {
        System.out.println("""
            
            ************************************************
            TEST LOGIN CON USUARIO BLOQUEADO
            ************************************************
            """);
        // Ejecutar login con usuario bloqueado
        loginPage.login(TestVariables.LOCKED_USERNAME, TestVariables.PASSWORD);
        
        // Verificar mensaje de error de usuario bloqueado
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        waitForDemoVisualization();
        
        assertEquals(TestVariables.ERROR_LOCKED_USER, loginPage.getErrorMessage());
        System.out.println("""
            
            ************************************************
            
            """);
    }
}
