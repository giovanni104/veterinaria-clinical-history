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

Incluye relaciones entre entidades y validaciones para garantizar la integridad de la información.

---

## 🏗️ Arquitectura

Solución desacoplada:

- **Backend (Spring Boot)**: API REST con arquitectura por capas  
  (Controller → Service → Repository)
- **Frontend (Angular 11)**: SPA que consume la API

```text
backend/    → API REST
frontend/   → Aplicación Angular
database/   → Scripts SQL
docs/       → Evidencias (capturas)