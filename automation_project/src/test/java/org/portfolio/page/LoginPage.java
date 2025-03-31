package org.portfolio.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.portfolio.config.TestVariables;

import java.time.Duration;

public class LoginPage extends Driver {
    // Localizadores de elementos
    @FindBy(id = "user-name")
    private WebElement userInput;
    
    @FindBy(id = "password")
    private WebElement passInput;
    
    @FindBy(id = "login-button")
    private WebElement loginButton;
    
    // Selector para el mensaje de error
    private static final By ERROR_MESSAGE = By.cssSelector("h3[data-test='error']");
    
    // Constructor
    public LoginPage(WebDriver driver, String url) {
        super(driver, url);
        PageFactory.initElements(driver, this);
    }

    // Método mejorado para realizar login
    public void login(String username, String password) {
        System.out.println("Intentando login con usuario: " + username);
        
        // Asegurarse de que estamos en la página de login
        if (!driver.getCurrentUrl().equals(TestVariables.BASE_URL)) {
            driver.get(TestVariables.BASE_URL);
        }
        
        // Esperar a que los elementos sean interactuables
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestVariables.DEFAULT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(userInput));
        wait.until(ExpectedConditions.elementToBeClickable(passInput));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        
        // Usar JavascriptExecutor para mayor compatibilidad
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        // Limpiar y rellenar campos
        js.executeScript("arguments[0].value = '';", userInput);
        userInput.sendKeys(username);
        
        js.executeScript("arguments[0].value = '';", passInput);
        passInput.sendKeys(password);
        
        // Hacer clic en el botón de login
        js.executeScript("arguments[0].click();", loginButton);
        
        // Esperar brevemente para que se procese
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("URL después del login: " + driver.getCurrentUrl());
    }
    
    // Método para obtener mensaje de error
    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestVariables.DEFAULT_WAIT_TIMEOUT));
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
        return errorMsg.getText();
    }
    
    // Verificar si el login fue exitoso
    public boolean isLoginSuccessful() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestVariables.DEFAULT_WAIT_TIMEOUT));
            return wait.until(ExpectedConditions.urlContains(TestVariables.INVENTORY_PAGE));
        } catch (Exception e) {
            return false;
        }
    }
    
        public void logout() {
            try {
                System.out.println("Ejecutando logout...");

                // Open menu
                System.out.println("Abriendo menú...");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestVariables.DEFAULT_WAIT_TIMEOUT));
                WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bm-burger-button")));
                menuButton.click();
                System.out.println("Menú abierto correctamente");

                // Add a small delay to ensure menu is fully expanded
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Try multiple approaches to find and click the logout link
                try {
                    // Approach 1: Wait for the menu container to be visible first
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bm-menu-wrap")));

                    // Approach 2: Use JavaScript to click the element
                    WebElement logoutLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logout_sidebar_link")));
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", logoutLink);
                    System.out.println("Logout ejecutado correctamente con JavaScript");
                    return;
                } catch (Exception e) {
                    System.out.println("Error al usar JavaScript para logout: " + e.getMessage());
                }

                // Approach 3: Try with CSS selector instead of ID
                try {
                    WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(
                            By.cssSelector("#logout_sidebar_link")));
                    logoutLink.click();
                    System.out.println("Logout ejecutado correctamente con CSS selector");
                    return;
                } catch (Exception e) {
                    System.out.println("Error al usar CSS selector para logout: " + e.getMessage());
                }

                // Approach 4: Try with XPath
                try {
                    WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@id='logout_sidebar_link']")));
                    logoutLink.click();
                    System.out.println("Logout ejecutado correctamente con XPath");
                    return;
                } catch (Exception e) {
                    System.out.println("Error al usar XPath para logout: " + e.getMessage());
                }

                // If all approaches fail, throw exception
                throw new RuntimeException("No se pudo hacer click en el enlace de logout con ningún método");

            } catch (Exception e) {
                System.out.println("Error durante el logout: " + e.getMessage());
                // Fallback: Navigate directly to login page
                driver.navigate().to("https://www.saucedemo.com/v1/index.html");
                System.out.println("Navegación forzada a página de login como alternativa");
            }
        }
}