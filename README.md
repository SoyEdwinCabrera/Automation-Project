# Automation Project

Este proyecto de automatización está diseñado para realizar pruebas de login, logout y validaciones en el sitio [SauceDemo](https://www.saucedemo.com/v1/index.html). Utilizando un enfoque de Page Object Model (POM), el proyecto implementa pruebas automatizadas para verificar el comportamiento de la aplicación bajo diferentes escenarios de autenticación.

[![Video de demostración](/Automation-Project/automation_project/assets/Automation_1.mp4)](/Automation-Project/automation_project/assets/Automation_1.mp4)

## Tecnologías utilizadas

- 💻 macOS
- ☕ JAVA JDK 23
- 🔨 Maven 3.9.9
- 🌐 Selenium WebDriver 4.29.0
- 🧪 JUnit Jupiter 5.8.2
- 🧩 Patrón Page Object Model (POM)

## Descripción del proyecto

El proyecto implementa un framework de automatización completo con:

### 1. Clases de Page Object Model (POM)

- **Driver**: Clase base que configura la ventana del navegador, su tamaño y posición, optimizada para mostrar el navegador en la mitad derecha de la pantalla para mejor visualización durante las pruebas.

- **LoginPage**: Encapsula todas las operaciones relacionadas con la página de inicio de sesión:
  - Métodos para ingresar credenciales
  - Verificación de mensajes de error
  - Comprobación de inicio de sesión exitoso
  - Implementación de logout

- **InventoryPage**: Maneja las interacciones con la página de inventario:
  - Verificación de carga correcta
  - Manipulación del menú lateral
  - Proceso de cierre de sesión
  - Verificación de redirección

### 2. Clases de prueba

- **AppTest**: Enfocado en probar la funcionalidad de la página de login:
  - Validación de campos vacíos
  - Validación de usuario sin contraseña
  - Login exitoso con credenciales correctas
  - Manejo de usuarios bloqueados

- **LoginLogoutTest**: Prueba el flujo completo de login y logout:
  - Prueba con usuario estándar
  - Iteración a través de todos los tipos de usuarios válidos

### 3. Configuración y variables compartidas

- **TestVariables**: Centraliza todas las constantes utilizadas en las pruebas:
  - URLs de la aplicación
  - Mensajes de error esperados
  - Credenciales de prueba
  - Tiempos de espera configurables
  - Lista de usuarios para pruebas de iteración

## Pruebas implementadas

### Pruebas de login (AppTest)

1. **emptyLoginShouldShowUsernameRequired**: Verifica que al intentar iniciar sesión sin credenciales se muestre el mensaje de error de usuario requerido.
2. **userOnlyLoginShouldShowPasswordRequired**: Comprueba que al proporcionar solo el nombre de usuario, se muestre el mensaje de error de contraseña requerida.
3. **validLoginShouldRedirectToInventory**: Confirma que al proporcionar credenciales válidas, el usuario sea redirigido a la página de inventario.
4. **lockedUserLoginShouldShowLockedMessage**: Verifica que al intentar iniciar sesión con un usuario bloqueado, se muestre el mensaje de error correspondiente.

### Pruebas de login y logout (LoginLogoutTest)

1. **testLoginLogoutStandardUser**: Prueba el flujo completo de inicio y cierre de sesión para un usuario estándar.
2. **testLoginLogoutAllUsers**: Itera a través de todos los usuarios válidos definidos en TestVariables, realizando el flujo completo de login y logout para cada uno.

## Requisitos y configuración

1. **Java 23** (o versión compatible).
2. **Maven 3.9.9** (o versión compatible).
3. **Navegadores compatibles**:
   - **Chrome**: AppTest está configurado para usar ChromeDriver.
   - **Safari**: LoginLogoutTest está configurado para usar SafariDriver.

4. **Drivers de navegador**:
   - [ChromeDriver](https://chromedriver.chromium.org/) para pruebas con Chrome.
   - [SafariDriver](https://developer.apple.com/documentation/webkit/testing_with_webdriver_in_safari) (incluido en macOS).

5. **Configuración de Safari para pruebas**:
   - Habilitar el menú de desarrollador: Safari → Preferencias → Avanzado → "Mostrar menú Desarrollar en la barra de menús".
   - Habilitar Automation: Menú Desarrollar → "Permitir Automación remota".

## Pasos para ejecutar las pruebas

1. **Clonar el repositorio**:
   ```
   git clone https://github.com/SoyEdwinCabrera/Automation-Project.git
   ```

2. **Verificar la instalación de Java y Maven**:
   ```
   java -version
   mvn -version
   ```

3. **Configurar los navegadores**:
   - Para Chrome: Descargar ChromeDriver y agregarlo al PATH.
   - Para Safari: Habilitar la automación remota como se indicó anteriormente.

4. **Ejecutar las pruebas**:
   - Para ejecutar todas las pruebas:
     ```
     mvn clean test
     ```
   - Para ejecutar una clase de prueba específica:
     ```
     mvn clean test -Dtest=AppTest
     ```
     o
     ```
     mvn clean test -Dtest=LoginLogoutTest
     ```

5. **Visualizar resultados**:
   Los resultados se mostrarán en la consola. Además, las pruebas están configuradas con parámetros de visualización que permiten observar el comportamiento del navegador durante la ejecución.

## Estructura del proyecto

```
Automation-Project/
├── README.md
└── automation_project/
    ├── pom.xml
    └── src/
        ├── main/
        │   └── java/
        │       └── org/
        │           └── portfolio/
        │               └── App.java
        └── test/
            └── java/
            └── org/
                └── portfolio/
                    ├── config/
                    │   └── TestVariables.java
                    ├── page/
                    │   ├── Driver.java
                    │   ├── InventoryPage.java
                    │   └── LoginPage.java
                    ├── AppTest.java
                    └── LoginLogoutTest.java
                    
## Personalizaciones adicionales

- **Tiempos de espera**: Modificar la constante `DEFAULT_WAIT_TIMEOUT` en TestVariables.java para cambiar el tiempo de espera predeterminado.
- **Tiempo de visualización**: Ajustar `VISUALIZATION_DELAY` para aumentar o disminuir el tiempo de pausa entre acciones para visualización.
- **Navegadores**: Para cambiar el navegador utilizado en una prueba específica, modificar la inicialización del WebDriver en el método `setUp()`:
  - En AppTest.java para cambiar de Chrome a otro navegador.
  - En LoginLogoutTest.java para cambiar de Safari a otro navegador.
- **Usuarios de prueba**: Agregar o modificar usuarios en la clase TestVariables.java según sea necesario.

## Características destacadas

- **Robustez en automatización**: Implementa técnicas avanzadas para manejar casos problemáticos como el menú lateral de SauceDemo.
- **Múltiples estrategias de interacción**: Utiliza diferentes enfoques (WebDriver estándar, JavaScript) para garantizar interacciones exitosas.
- **Manejo eficiente de errores**: Implementa alternativas cuando las interacciones primarias fallan.
- **Visualización de pruebas**: Incluye pausas configurables para observar el comportamiento del navegador durante las pruebas.
- **Configuración optimizada de ventana**: Posicionamiento automático del navegador para mejorar la visualización durante las pruebas.
