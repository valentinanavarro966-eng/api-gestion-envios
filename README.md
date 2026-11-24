# API de Gestión de Envíos Logísticos

> **Proyecto académico.** Desarrollado como examen parcial de la materia Programación III de la Tecnicatura Universitaria en Programación (UTN – Facultad Regional Mar del Plata), 2026. La consigna fue planteada por la cátedra; el diseño y el código son de mi autoría.

API REST desarrollada con **Java 21 y Spring Boot** para administrar los envíos de una empresa de logística: clientes, recursos logísticos (vehículos o medios de transporte con capacidad y stock) y envíos.

## Tecnologías

- Java 21 · Spring Boot (Spring Web, Spring Data JPA)
- Hibernate · MySQL
- Lombok · Maven

## Qué hace

- Alta, modificación, baja y consulta de recursos logísticos, con filtro por tipo de carga.
- Registro de envíos con **validaciones de negocio**:
  - el cliente y el recurso logístico tienen que existir;
  - tiene que haber recursos disponibles (y al crear el envío se descuenta uno);
  - la fecha de envío no puede ser anterior a hoy;
  - el peso no puede superar la capacidad máxima del recurso.
- Cambio de estado de un envío a finalizado.
- Consulta de los envíos de un cliente.

## Arquitectura

Organizada en capas:

```
Controller  →  Service  →  Repository  →  MySQL
```

- **Controllers**: exponen los endpoints REST.
- **Services**: concentran la lógica y las reglas de negocio.
- **Repositories**: acceso a datos con Spring Data JPA.
- **DTOs** para recibir datos, **enum** para los estados del envío y una **excepción propia** (`EnvioInvalidoException`) para los errores de negocio.

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/clientes` | Lista los clientes |
| GET | `/recursosLogisticos` | Lista los recursos logísticos |
| POST | `/recursosLogisticos/nuevoRecursoLogistico` | Crea un recurso |
| PUT | `/recursosLogisticos/modificarRecursoLogistico/{id}` | Modifica un recurso |
| DELETE | `/recursosLogisticos/borrarRecursoLogistico/{id}` | Elimina un recurso |
| GET | `/recursosLogisticos/tipoCarga/{tipoCarga}` | Recursos por tipo de carga |
| POST | `/envios/nuevoEnvio` | Crea un envío (con validaciones) |
| PUT | `/envios/modificarEstadoAfinalizado/{id}` | Marca un envío como finalizado |
| GET | `/envios/cliente/{idCliente}` | Envíos de un cliente |

## Cómo correrlo

1. Tener Java 21 y MySQL instalados, y crear la base de datos:
   ```sql
   CREATE DATABASE empresa_logistica;
   ```
2. Configurar la contraseña de MySQL como variable de entorno:
   ```bash
   export DB_PASSWORD=tu_contraseña      # Windows (PowerShell): $env:DB_PASSWORD="tu_contraseña"
   ```
   Opcionalmente también `DB_USER` (por defecto `root`) y `DB_URL`.
3. Levantar la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```
4. La API queda en `http://localhost:8080`. Las tablas se crean solas al iniciar.

## Autora

**Valentina Navarro** · [LinkedIn](https://linkedin.com/in/valentina-navarro-56561639a)
