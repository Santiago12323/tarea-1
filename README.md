# Contador de Líneas de Código (LOC)

## Descripción

Este proyecto es una herramienta de línea de comandos escrita en Java que permite contar:
- Líneas físicas (`phy`): todas las líneas del archivo, incluyendo comentarios y líneas en blanco.
- Líneas de código (`loc`): excluye comentarios (`//`, `/* */`) y líneas en blanco.

También soporta:
- Uso de comodines (`*`)
- Búsqueda recursiva de archivos en directorios
- Archivos `.java` como entrada principal

## Tabla de Contenidos

- [Instalación](#instalación)
- [Uso](#uso)
- [Ejemplos](#ejemplos)
- [Pruebas](#pruebas)
- [Diseño](#diseño)
- [Productividad (LOC/h)](#productividad-loch)
- [Autores](#autores)
- [Licencia](#licencia)

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/countlines.git
   cd countlines
   ```


## Compila con Maven

mvn clean package

## Uso

Ejecuta el programa con el siguiente formato:
   ```bash
java -jar target/countlines.jar [phy|loc] archivo.java
```
### Parámetros

- `phy`: cuenta todas las líneas del archivo.
- `loc`: cuenta solo las líneas de código reales.

## Ejemplos

Contar todas las líneas de un archivo:

java -jar target/countlines.jar phy src/main/java/CountLines.java

Contar solo las líneas de código (sin comentarios ni líneas en blanco):

java -jar target/countlines.jar loc src/main/java/CountLines.java

Contar recursivamente todos los archivos `.java`:

java -jar target/countlines.jar loc src/**/*.java

## Pruebas

Las pruebas se ejecutan con JUnit:

   ```bash
mvn test
```
El reporte de pruebas se encuentra en el archivo `test_report.md`.

