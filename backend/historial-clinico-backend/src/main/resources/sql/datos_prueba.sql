INSERT INTO usuarios (nombre, apellido, correo, tipo_documento, documento_identificacion, estado, sexo)
VALUES
('Carlos', 'Ramirez', 'carlos@email.com', 'CC', '12345678', 'ACTIVO', 'Masculino'),
('Laura', 'Gomez', 'laura@email.com', 'CC', '87654321', 'ACTIVO', 'Femenino')
ON CONFLICT (documento_identificacion) DO NOTHING;

INSERT INTO colaboradores (nombre, apellido, cargo, especialidad, tipo_documento, documento_identificacion)
VALUES
('Ana', 'Gomez', 'Veterinaria', 'Medicina general', 'CC', '11111111'),
('Luis', 'Perez', 'Auxiliar', 'Apoyo clínico', 'CC', '22222222')
ON CONFLICT (documento_identificacion) DO NOTHING;