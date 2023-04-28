# Anonymous Chat 🏴‍☠️

## ¿Qué es? 👀
Anonymous Chat es una aplicación móvil que permite la creación de salas de chat comunitarias a las que cualquier individuo con la aplicación y a una distancia de x metros de quien creó la sala puede unirse.

## ¿Para qué sirve? 🧐
La aplicación sirve como medio de comunicación anónimo para personas cercanas geográficamente con intereses comunes.

## ¿Hasta dónde va a llegar?
- La aplicación tendrá un historial de mensajes de 2 horas, posterior a esto todos los mensajes son eliminados.
- Al entrar a la sala, a cada usuario se le asignará un nombre aleatorio con el fin de mantener el anonimato y evitar nombres inapropiados.
- El creador de la sala puede cerrarla en cualquier momento. En caso de que se desconecte, la sala se cerrará automáticamente.
- Cada sala tendrá un límite de 10 personas que pueden ingresar (en la versión gratuita de la aplicación, la limitación se eliminará en la versión premium).

## ¿Qué herramientas se van a utilizar? 📱
| Aplicación | Uso |
|------------|-----|
| Figma | Diseño de interfaces de usuario |
| Android Studio | Desarrollo de la aplicación móvil |
| API de geolocalización | Identificación de la ubicación del usuario |
| Firebase | Almacenamiento de datos |


## Estructura :building_construction:
- Pantalla de carga 
- Pantalla de inicio de sesión
- Pantalla de registro
- Pantalla para ver las salas a las que anteriormente te has unido
- Ver perfil
- Pantalla de "buscando sala de chat"
- El chat

## Construcción del diseño :hammer_and_wrench:

Véase el siguiente enlace de Figma

[Diseño figma del proyecto]([Figma](https://www.figma.com/file/ddmPxT7uDvlgndoo8PZOQR/Anonymous-Chat-v.1.0?node-id=0%3A1&t=o6GwLl7WQfU6dpRm-0))


## Estructura de carpetas

- `di`: esta carpeta contiene las clases necesarias para la inyección de dependencias en el proyecto.
    - `AppModule.kt`: define los objetos que deben estar disponibles en todo el proyecto.
- `domain`: esta carpeta contiene la lógica de negocio del proyecto.
    - `models`: contiene los modelos de datos que se utilizan en el proyecto.
    - `repositories`: contiene los repositorios que se utilizan para obtener los datos del proyecto.
    - `services`: contiene las interfaces para los servicios que se utilizan en el proyecto.
- `ui`: esta carpeta contiene la interfaz de usuario del proyecto.
    - `screens`: contiene las pantallas que conforman la aplicación.
    - `theme`: contiene el tema de la aplicación, incluyendo colores, fuentes y otros recursos de estilo.
v
## Requisitos :clipboard:

- Android Studio 4.2 o superior.
- Gradle 7.0.2 o superior.
- Android SDK versión 30 o superior.

## Configuración del proyecto 🔧

Para ejecutar el proyecto, siga estos pasos:

1. Clona este repositorio en tu máquina local.
2. Abre Android Studio y selecciona "Abrir proyecto".
3. Navega hasta la carpeta donde clonaste el repositorio y seleccione el archivo `build.gradle` en la raíz del proyecto.
4. Espera a que se carguen las dependencias y los recursos del proyecto.
5. Haz clic en "Ejecutar" para construir y ejecutar la aplicación en un emulador o dispositivo conectado.

¡Listo! Ya puedes empezar a explorar el código y modificarlo para tus propios proyectos. Si tienes alguna pregunta o sugerencia, no dudes en abrir un issue en este repositorio.

## Creditos :trophy:

- [Alan Peña Ortiz](http://github.com/lanazirot)
- [Alan Abiud Castro ](http://github.com/aeax2311)
- [Andrea Joana Martinez Limon](https://github.com/AndreaMartinezLimon-Tec)
- [Eduardo Jared Aguirre Cardenas](https://github.com/Ejaredac)
- [Ezequiel Cantu de la Rosa](https://github.com/Ezequiel-Cantu)