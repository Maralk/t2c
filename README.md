# Prueba técnica de Backend para T2C
_Se trata de hacer una API para una cadena de concesionarios. Van a usarlo con una SPA, por lo que requieren que siga REST._

## Comenzando 🚀
_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._


### Pre-requisitos 📋

#### Instalar una base de datos
_Lo primero que debemos hacer es descargar e instalar [Docker](https://docs.docker.com/get-docker/)._

_Una vez instalado, vamos a generar un contenedor con mysql y la base de datos t2c._
_Para ello, en el terminal, nos iremos al directorio donde está el archivo docker-compose.yaml, carpeta docker en la raiz del proyecto y ejecutar:_
```
docker-compose up
```
_(Ésto también iniciará el contenedor)_

_Para iniciar el contenedor en otras ocasiones sin volver a crearlo el comando es:_
```
docker container start mysql
```

#### JDK 8
_Descargar e instalar el JDK 8 desde [Oracle](https://www.oracle.com/es/java/technologies/javase/javase8-archive-downloads.html)._

#### IDE IntelliJ
_Descargar e instalar el IDE [IntelliJ](https://www.jetbrains.com/es-es/idea/download/)._

### Instalación 🔧
_Para arrancar el proyecto, desde el menú de IntelliJ, seleccionamos:_

_File -> Open..._

_Seleccionamos el archivo pom.xml y le damos a "open"_

_Después elegimos la opción "open as proyect"_

_Una vez cargado el proyecto, para lanzarlo, pulsaremos las teclas mayus+F10 o simplemente pulsando el botón Run 'Application'_

_Una vez realizados todos estos pasos el proyecto debe estár en ejecución_


## Ejecutando las pruebas ⚙️
_Para probar los endpoints de la API podemos hacer uso de [la documentación de la API con Swagger](http://localhost:8080/swagger-ui.html)_


## Construido con 🛠️
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - El framework usado
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [JPA](https://spring.io/projects/spring-data-jpa) - Persistencia de datos
* [Swagger](https://swagger.io/docs/) - Usado para generar la documentación de la API


## Autor ✒️
* **Eduardo Martínez Alcaide** - [Maralk](https://github.com/Maralk)
