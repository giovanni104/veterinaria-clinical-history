# 🐾 Historial Clínico Veterinario

Aplicación web para la gestión del historial clínico de una veterinaria, desarrollada como prueba técnica.

---

## 📌 Descripción

El sistema permite administrar la información clínica mediante módulos CRUD para:

- Usuarios  
- Mascotas  
- Colaboradores  
- Historias Clínicas  
- Detalles de Historia Clínica  

Incluye relaciones entre entidades y validaciones que garantizan la integridad de la información.

---

## 🏗️ Arquitectura

Solución desacoplada:

- **Backend (Spring Boot)** → API REST con arquitectura por capas  
- **Frontend (Angular 11)** → Aplicación SPA  
- **Base de datos** → PostgreSQL (Neon)

---

## 🚀 Tecnologías

**Backend**
- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Swagger / OpenAPI
- JUnit 5 / Mockito

**Frontend**
- Angular 11
- TypeScript
- Bootstrap
- Reactive Forms

---

## 🌐 Demo en producción

Frontend:  
https://veterinaria-clinical-history.vercel.app

Backend (Swagger):  
https://veterinaria-clinical-history.onrender.com/swagger-ui/index.html

---

## 🔗 Modelo de datos

- Usuario → Mascotas  
- Mascota → Historias Clínicas  
- Historia Clínica → Detalles Clínicos  
- Colaborador → Detalles Clínicos  

---

## 📁 Estructura del proyecto

```text
backend/    → API REST Spring Boot
frontend/   → Aplicación Angular
database/   → Scripts SQL
docs/       → Documentación y evidencias
README.md