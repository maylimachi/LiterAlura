# 📚 LiterAlura

Aplicación de consola desarrollada en **Java con Spring Boot** que permite buscar libros desde una API externa y almacenarlos en una base de datos **PostgreSQL**.  
El sistema también permite consultar información sobre libros y autores guardados.

## 🚀 Funcionalidades

El programa ofrece un menú interactivo con las siguientes opciones:

1️⃣ **Buscar libro por título**  
- Consulta la API de Gutendex.  
- Obtiene información del libro.  
- Guarda el libro y su autor en la base de datos.

2️⃣ **Listar libros registrados**  
- Muestra todos los libros guardados en la base de datos.

3️⃣ **Listar autores registrados**  
- Muestra todos los autores almacenados.

4️⃣ **Listar autores vivos en un determinado año**  
- Permite ingresar un año.
- Muestra los autores que estaban vivos en ese año.

5️⃣ **Cantidad de libros por idioma**  
- Permite consultar cuántos libros hay en un idioma específico.
- Ejemplo:
  - Español
  - Inglés

---

## 🧰 Tecnologías utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Jackson**
- **API Gutendex** (https://gutendex.com)

---

## 🗄️ Base de datos

El proyecto utiliza **PostgreSQL** para almacenar los datos de libros y autores.

## 📦 Estructura del proyecto
```

LiterAlura
│
├── LiterAluraApplication.java
├── ConsumoAPI.java
├── Conversor.java
│
├── modelo
│ ├── Libro.java
│ └── Autor.java
│
├── dto
│ ├── Datos.java
│ └── DatosLibro.java
│
├── repository
│ ├── LibroRepository.java
│ └── AutorRepository.java

```
---

## 🔎 Ejemplo de uso
```

1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Cantidad de libros por idioma
0 - Salir

```
---

## 📡 API utilizada

Este proyecto consume datos de la API pública:

**Gutendex API**

https://gutendex.com/books/

Ejemplo de búsqueda:


https://gutendex.com/books/?search=don+quijote


---

## 💾 Persistencia de datos

Los libros buscados se guardan en la base de datos junto con su autor.  
Los datos permanecen almacenados incluso después de cerrar la aplicación.

---

## 🎯 Objetivo del proyecto

Este proyecto fue desarrollado como práctica para:

- Consumo de APIs REST
- Manejo de JSON
- Persistencia de datos con JPA
- Relaciones entre entidades
- Uso de Streams en Java
- Creación de consultas derivadas en Spring Data JPA

---

## 👩‍💻 Autora

Proyecto desarrollado por **Mayra Limachi** como parte del aprendizaje de **Java y Spring Boot**.
