# Historial Clínico Veterinario

Aplicación web para la gestión del historial clínico de una veterinaria, desarrollada como prueba técnica.

## Descripción

La solución permite administrar la información principal de una veterinaria mediante módulos CRUD para:

- Usuarios
- Mascotas
- Colaboradores
- Historias Clínicas
- Detalles de Historia Clínica

La arquitectura está separada en:

- **Backend**: API REST desarrollada en Spring Boot
- **Frontend**: aplicación web desarrollada en Angular 11
- **Base de datos**: PostgreSQL

---

## Tecnologías utilizadas

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Swagger / OpenAPI
- JUnit 5
- Mockito

### Frontend
- Angular 11
- TypeScript
- Bootstrap
- Reactive Forms
- HttpClient

### Base de datos
- PostgreSQL

---

## Estructura del proyecto

```text
backend/   -> API REST en Spring Boot
frontend/  -> Aplicación web Angular 11
database/  -> Scripts SQL de base de datos y datos de prueba
docs/      -> Evidencias, capturas y documentación complementaria