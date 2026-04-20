CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    tipo_documento VARCHAR(255) NOT NULL,
    documento_identificacion VARCHAR(255) NOT NULL UNIQUE,
    estado VARCHAR(255) NOT NULL,
    sexo VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS mascotas (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    raza VARCHAR(255) NOT NULL,
    vacuna VARCHAR(255) NOT NULL,
    sexo VARCHAR(255) NOT NULL,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_mascota_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
);

CREATE TABLE IF NOT EXISTS colaboradores (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    cargo VARCHAR(255) NOT NULL,
    especialidad VARCHAR(255) NOT NULL,
    tipo_documento VARCHAR(255) NOT NULL,
    documento_identificacion VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS historias_clinicas (
    id BIGSERIAL PRIMARY KEY,
    fecha_creacion DATE NOT NULL,
    mascota_id BIGINT NOT NULL,
    CONSTRAINT fk_historia_mascota
        FOREIGN KEY (mascota_id)
        REFERENCES mascotas(id)
);

CREATE TABLE IF NOT EXISTS detalles_historia_clinica (
    id BIGSERIAL PRIMARY KEY,
    temperatura NUMERIC(5,2) NOT NULL,
    peso NUMERIC(8,2) NOT NULL,
    frecuencia_cardiaca NUMERIC(8,2) NOT NULL,
    frecuencia_respiratoria NUMERIC(8,2) NOT NULL,
    fecha_hora TIMESTAMP NOT NULL,
    alimentacion VARCHAR(255) NOT NULL,
    hidratacion VARCHAR(255) NOT NULL,
    observacion VARCHAR(500) NOT NULL,
    historia_clinica_id BIGINT NOT NULL,
    colaborador_id BIGINT NOT NULL,
    CONSTRAINT fk_detalle_historia
        FOREIGN KEY (historia_clinica_id)
        REFERENCES historias_clinicas(id),
    CONSTRAINT fk_detalle_colaborador
        FOREIGN KEY (colaborador_id)
        REFERENCES colaboradores(id)
);