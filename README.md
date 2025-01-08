# Foro API

¡Bienvenido/a a **Foro API**! Este proyecto es un trabajo Back End diseñado para replicar el funcionamiento de un foro a nivel de servidor. La API REST fue creada utilizando **Spring Boot** y se centra en gestionar tópicos, permitiendo una interacción eficiente entre usuarios, preguntas y respuestas.

## 🎯 Objetivo

El objetivo principal es desarrollar una API REST funcional que gestione los tópicos del foro, segun los requerimientos inciales planteados en la herramienta de gestión de proyectos y tareas basada en tableros visuales - Trello.

## 🛠️ Funcionalidades

La API ofrece las siguientes operaciones CRUD:
1. **Crear un nuevo tópico:** Permite a los usuarios agregar un tópico nuevo con detalles relevantes.
2. **Mostrar todos los tópicos:** Lista todos los tópicos creados en el sistema.
3. **Mostrar un tópico específico:** Recupera los detalles de un único tópico utilizando su identificador.
4. **Actualizar un tópico:** Permite a los usuarios modificar un tópico existente.
5. **Eliminar un tópico:** Permite a los usuarios eliminar un tópico específico.

## 🗂️ Características técnicas

- **Framework principal:** Spring Boot.
- **Base de datos:** MySQL.
  - Utiliza migraciones para la gestión de la base de datos.
  - Las migraciones están configuradas como parte del proyecto.
- **Documentación de la API:**
  - Disponible en formato JSON en: `/v3/api-docs/`
  - Interfaz Swagger UI en: `/swagger-ui.html`

## 🔧 Instalación y configuración

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/arturexxx/bibliotecaAlura.git
   cd bibliotecaAlura
   
2. **Configurar la base de datos:**
   Asegúrate de tener MySQL instalado y en ejecución.
   Crea una base de datos para el proyecto.
   Configura las credenciales de acceso en el archivo application.properties

3. **Ejecutar las migraciones:**
   El proyecto incluye migraciones configuradas para crear las tablas necesarias en la base de datos.
   Estas se ejecutan automáticamente al iniciar la aplicación.
   
4. **Compilar y ejecutar el proyecto:**
   ./mvnw spring-boot:run

5. **Acceder a la API:**
   Swagger UI: http://localhost:8080/swagger-ui.html
   JSON Docs: http://localhost:8080/v3/api-docs/

6. 📚 **Endpoints principales**
   A continuación, se detallan los endpoints principales de la API:
   
| Método HTTP | Endpoint        | Parámetros                       | Descripción                                                                                     |
|-------------|-----------------|-----------------------------------|-------------------------------------------------------------------------------------------------|
| POST        | `/topicos`      | Ninguno                          | Crear un nuevo tópico.                                                                         |
| GET         | `/topicos`      | `curso` (opcional)               | Listar tópicos por curso.                                                                      |
|             |                 | `anio` (opcional)                | Listar tópicos por año.                                                                        |
|             |                 | `curso` y `anio` (opcionales)    | Listar tópicos filtrados por curso y año.                                                     |
|             |                 | Sin parámetros                   | Listar los primeros 10 tópicos ordenados por fecha de creación en orden ascendente (paginados).|
| GET         | `/topicos/{id}` | Ninguno                          | Mostrar un tópico específico.                                                                 |
| PUT         | `/topicos/{id}` | Ninguno                          | Actualizar un tópico.                                                                          |
| DELETE      | `/topicos/{id}` | Ninguno                          | Eliminar un tópico.                                                                            |

7. 🛡️ **Licencia**
    Este proyecto es codigo libre.

