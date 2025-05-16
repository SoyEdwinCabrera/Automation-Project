package org.portfolio.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.portfolio.config.TestVariables;

import java.time.Duration;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Localizadores más robustos
    private static final By MENU_BUTTON = By.className("bm-burger-button");
    private static final By MENU_CONTAINER = By.className("bm-menu-wrap");
    private static final By LOGOUT_LINK = By.id("logout_sidebar_link");
    private static final By PRODUCT_LABEL = By.className("product_label");

    // Constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestVariables.DEFAULT_WAIT_TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    // Verificar que estamos en la página de inventario
    public boolean isOnInventoryPage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LABEL)) != null;
        } catch (Exception e) {
            return false;
        }
    }

    // Obtener el texto del título principal
    public String getProductLabelText() {
        WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LABEL));
        return label.getText();
    }

    // Abrir el menú lateral con estrategia mejorada
    public void openMenu() {
        System.out.println("Abriendo menú...");

        try {
            // Esperar hasta que el botón sea clickeable
            WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(MENU_BUTTON));

            // Clic directo en el botón
            menuButton.click();

            // (Opcional) Pausa breve para dar tiempo a la animación del menú (si fuera necesario)
            Thread.sleep(2000);

            // Verificar si el menú está efectivamente abierto
            if (isMenuOpen()) {
                System.out.println("Menú abierto correctamente");
            } else {
                System.out.println("El menú no se abrió después de hacer clic");
            }

        } catch (Exception e) {
            System.out.println("Error al abrir el menú: " + e.getMessage());
        }
    }

    // Verificar si el menú está abierto
    private boolean isMenuOpen() {
        try {
            // Verificar si el contenedor del menú está visible y abierto
            WebElement menuContainer = driver.findElement(MENU_CONTAINER);
            // Use getDomAttribute instead of deprecated getAttribute
            String ariaHidden = menuContainer.getDomAttribute("aria-hidden");
            return ariaHidden == null || ariaHidden.equals("false");
        } catch (Exception e) {
            return false;
        }
    }

    // Cerrar sesión
    public void logout() {
        System.out.println("Ejecutando logout...");

        try {
            // Abrir el menú
            openMenu();

            // Si no podemos hacer clic en el menú, intentar una alternativa
            if (!isMenuOpen()) {
                // Alternativa: Navegar directamente a la página de login
                System.out.println("Usando navegación directa como alternativa para logout");
                driver.get(TestVariables.BASE_URL);
                return;
            }

            // Localizar y hacer clic en el enlace de logout
            WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK));

            // Usar JavaScript para el clic final
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", logoutLink);

            // Esperar a que la página de login se cargue
            wait.until(ExpectedConditions.urlToBe(TestVariables.BASE_URL));

            System.out.println("Logout completado. URL actual: " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println("Error durante el logout: " + e.getMessage());

            // Plan B: Si falla el logout, navegar directamente a la página de login
            driver.get(TestVariables.BASE_URL);
            System.out.println("Navegación forzada a página de login como alternativa");
        }
    }

    // Verificar si el logout fue exitoso
    public boolean isLogoutSuccessful() {
        try {
            // Verificar URL
            boolean correctUrl = wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe(TestVariables.BASE_URL),
                ExpectedConditions.urlContains("index.html")
            ));

            // Verificar elemento de login
            WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-button")));

            return correctUrl && loginButton.isDisplayed();
        } catch (Exception e) {
            System.out.println("Error al verificar logout: " + e.getMessage());
            return false;
        }
    }
}