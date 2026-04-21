# Arquitectura de la solución

La aplicación fue desarrollada bajo una arquitectura desacoplada:

## Backend
- Spring Boot
- Arquitectura por capas:
  - Controller
  - Service
  - Repository
- Persistencia con JPA/Hibernate
- Base de datos PostgreSQL (Neon)

## Frontend
- Angular 11
- Formularios reactivos
- Consumo de API REST mediante HttpClient

## Despliegue
- Frontend: Vercel
- Backend: Render
- Base de datos: Neon

## Flujo general
1. El frontend consume la API REST
2. El backend gestiona la lógica de negocio
3. Los datos se almacenan en PostgreSQL

## Consideraciones
- Uso de variables de entorno para configuración
- Separación de ambientes (dev / prod)
- Validaciones en frontend y backend