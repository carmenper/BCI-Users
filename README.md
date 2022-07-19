# BCI Users Challenge

***Este proyecto fue desarrollado utilizando IntelliJ IDEA.***

Este repositorio realiza las siguientes tareas:
- Sign-up de usuario
- Login de usuario

El stack tecnológico es el siguiente:
- Java 8
- Spring boot 2.7.1
- Jackson
- Hibernate H2
- JWT
- Criptografía AES
- Gradle

Características específicas de Java 8 que fueron utilizadas:
- SHA-224 Message Digests (SHA-256)
- Date/Time API (LocalDateTime)

**Prerequisitos**
- JDK 8

**Instalación**
- Clonar el projecto desde GitHub.

**Construcción**
- No es necesario construir el proyecto para su ejecución.
- Ejecutar un 'gradle clean build' o un 'gradlew clean build' desde el simbolo del sistema.
- Esto se hace para asegurar la existencia de las dependencias y generar el jar.

**Ejecución**
- Desde el simbolo del sistema
- Localizar la carpeta 'carpeta-del-proyecto\build\libs'
- Desde esa capeta, ejecutar 'java -jar bci-users-1.0.0-SNAPSHOT.jar'
- Esta aplicación ejecuta en el puerto 8000.

Pruebas
- Para las pruebas de aceptación se puede utilizar la colección de ***Postman*** que se provee en la carpeta 'postman' del proyecto.
