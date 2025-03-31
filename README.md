
# Automation Project

Este proyecto de automatización está diseñado para realizar pruebas de login, logout y validaciones en el sitio [SauceDemo](https://www.saucedemo.com). Se utilizan tecnologías como Selenium (con Java), JUnit 5, y Maven para la administración de dependencias y ejecución de las pruebas automatizadas.

## Tecnologías utilizadas

- 💻 macOS
- ☕ JAVA JDK 23
- 🔨 Maven 3.9.9
- 📝 Zed IDE

## Descripción

El proyecto incluye:
1. Clases de Page Object (Page Object Model - POM) que representan las diferentes pantallas:
   - LoginPage (para el formulario de login).
   - InventoryPage (para el inventario de productos y el menú lateral).
   - Driver (clase base para la configuración de la ventana, tamaño y posición del navegador).
2. Clases de prueba (Test classes) que usan JUnit 5:
   - AppTest (pruebas enfocadas en la página de login).
   - LoginLogoutTest (pruebas de login y logout, incluyendo la validación de diferentes usuarios).
3. Clases de configuración y variables compartidas:
   - TestVariables (constantes de URL, mensajes de error, credenciales de acceso y tiempos de espera básicos).

## Requisitos y configuración

1. Java 11+ (o versión superior).  
2. Maven 3.9.9 (para compilar y ejecutar las pruebas).  
3. Un navegador compatible:
   - De forma predeterminada se usa ChromeDriver en AppTest y SafariDriver en LoginLogoutTest.  
   - Asegúrate de tener instalados los binarios correspondientes para tu SO:  
     - [ChromeDriver](https://chromedriver.chromium.org/) (si usas Chrome).  
     - [SafariDriver](https://developer.apple.com/documentation/webkit/testing_with_webdriver_in_safari) (en Mac con Safari incluido).  
   - Para Safari, asegúrate de activar "Permitir control remoto de Safari" (Desarrollar → Permitir Automación remota) en las preferencias de Safari, de ser necesario.

4. Configuraciones específicas del proyecto:
   - El archivo TestVariables.java contiene las URLs (BASE_URL) y credenciales para la prueba.  
   - El plugin Maven Surefire se encarga de detectar y ejecutar las pruebas en la carpeta “src/test/java/”.  

## Pasos para configurar y ejecutar las pruebas

1. Clona o descarga el repositorio en tu máquina local.  
   git clone https://github.com/SoyEdwinCabrera/automation_project.git  

2. Asegúrate de tener Java y Maven instalados y configurados en tu variable PATH.  
   - Puedes verificar tu versión con:  
     java -version  
     mvn -version  

3. (Opcional para Chrome) Si ejecutas AppTest y deseas usar Chrome, descarga ChromeDriver y colócalo en tu PATH, o configura su ubicación.  

4. (Opcional para Safari) Si ejecutas LoginLogoutTest y deseas utilizar Safari, habilita la automatización de Safari:  
   - Abre Safari → Preferencias → Avanzado → Activa “Mostrar el menú Desarrollar en la barra de menús”.  
   - Ve al menú “Desarrollar” → Selecciona “Permitir Automación remota” (si aparece).  

5. Ubícate en la carpeta raíz del proyecto y ejecuta las pruebas con Maven:  
   mvn clean test  

6. Observa los resultados de la consola. Si la configuración está correcta, deberías ver un “BUILD SUCCESS” al final, junto con los reportes de las pruebas que se ejecutaron.  

## Estructura del proyecto

- ┣ src  
  ┃ ┣ main  
  ┃ ┃ ┗ java (código principal, si lo hubiera)  
  ┃ ┗ test  
  ┃ ┃ ┣ java  
  ┃ ┃ ┃ ┗ org  
  ┃ ┃ ┃   ┗ portfolio  
  ┃ ┃ ┃     ┣ config  
  ┃ ┃ ┃     ┣ page  
  ┃ ┃ ┃     ┣ AppTest.java  
  ┃ ┃ ┃     ┗ LoginLogoutTest.java  
- ┣ pom.xml (archivo de configuración de Maven)  
- ┗ README.md (este archivo con la descripción y pasos)  

## Personalizaciones adicionales

- Para cambiar la configuración del tiempo de espera (timeout) en las pruebas, modifica la constante DEFAULT_WAIT_TIMEOUT en TestVariables.java.  
- Si deseas cambiar credenciales de acceso o URL, actualiza las variables en TestVariables.java.  
- Para ejecutar las pruebas en un navegador específico (por ejemplo, Chrome o Safari), ajusta las clases de prueba (p.ej., LoginLogoutTest, AppTest) cambiando la instancia del WebDriver que desees usar.

## Contacto

Si tienes preguntas o sugerencias sobre este proyecto, no dudes en abrir un Issue o un Pull Request en este repositorio. ¡Gracias por contribuir!
