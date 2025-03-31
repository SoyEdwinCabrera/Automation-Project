package org.portfolio.page;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import java.awt.Toolkit;

public class Driver {
    protected WebDriver driver;

    public Driver(WebDriver driver, String url) {
        this.driver = driver;
        
        // Obtener el tamaño de la pantalla
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        
        // Calcular dimensiones para mitad derecha
        int halfWidth = screenWidth / 2;
        
        // Primero maximizar para estabilizar Safari
        // this.driver.manage().window().maximize();
        
        try {
            Thread.sleep(500); // Pequeña pausa para estabilización
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Configurar tamaño para ocupar exactamente la mitad derecha
        this.driver.manage().window().setSize(new Dimension(halfWidth, screenHeight));
        
        // Posicionar en la mitad derecha
        this.driver.manage().window().setPosition(new Point(halfWidth, 0));
        
        // Navegar a la URL
        this.driver.get(url);
        
        System.out.println("Ventana configurada con tamaño: " + halfWidth + "x" + screenHeight);
        System.out.println("Ventana posicionada en X=" + halfWidth + ", Y=0");
        System.out.println("Navegando a: " + url);
    }
    
    public void close() throws InterruptedException {
        System.out.println("Shutting down...");
        Thread.sleep(2000);
        this.driver.quit();
    }
}