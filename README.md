# ForoHub - Backend API

ForoHub es una API REST robusta diseñada para gestionar los tópicos de un foro de discusión. Este proyecto forma parte del desafío propuesto en la formación de Alura y Oracle Next Education, enfocado en demostrar habilidades avanzadas en el desarrollo backend, seguridad y persistencia de datos.

## 🛠️ Stack Tecnológico

Este proyecto destaca por utilizar versiones de vanguardia en el ecosistema Java:

* **Lenguaje:** Java 25
* **Framework:** Spring Boot 4.0.3
* **Seguridad:** Spring Security con soporte para **JWT (JSON Web Tokens)**
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** PostgreSQL (Compatible con versión 9.3.4)
* **Migraciones:** Flyway
* **Gestión de Dependencias:** Maven



## 📂 Estructura del Proyecto

El código sigue una organización modular bajo el paquete `com.foro_hub`:

* **`controller`**: Manejo de peticiones HTTP y definición de endpoints (`TopicoController`, `AutenticacionController`).
* **`domain`**:
    * `topico`: Contiene la entidad `Topico`, su repositorio y los DTOs para entrada/salida de datos.
    * `usuario`: Gestión de perfiles de usuario y autenticación.
* **`infra`**:
    * `security`: Implementación del `SecurityFilter`, `TokenService` y configuración del `SecurityFilterChain`.
    * `errors`: Tratamiento de excepciones y respuestas HTTP.
* **`resources/db/migration`**: Scripts SQL gestionados por Flyway para la evolución del esquema de la base de datos.

## 🚀 Funcionalidades Principales

1.  **Seguridad Stateless:** Autenticación de usuarios mediante JWT. Solo usuarios logueados pueden crear, editar o eliminar tópicos.
2.  **CRUD Completo de Tópicos:**
    * Registro de tópicos vinculados a autores y cursos específicos.
    * Consulta de tópicos con información detallada.
    * Actualización de mensajes y títulos.
    * Eliminación física de registros en la base de datos.
3.  **Validación de Datos:** Reglas de negocio aplicadas mediante `jakarta.validation` para asegurar la integridad de la información.

## ⚙️ Configuración del Entorno

Para ejecutar el proyecto localmente, asegúrate de tener instalado el **JDK 25** y configurar las siguientes variables en tu archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost/foro_hub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contrasena
api.security.token.secret=${JWT_SECRET:tu_secreto_personalizado}
