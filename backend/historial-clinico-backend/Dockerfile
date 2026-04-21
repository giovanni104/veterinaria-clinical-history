# Usar imagen de Java
FROM eclipse-temurin:17-jdk-alpine

# Crear carpeta de trabajo
WORKDIR /app

# Copiar proyecto
COPY . .

# Construir el jar
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Ejecutar app
CMD ["java", "-jar", "target/*.jar"]