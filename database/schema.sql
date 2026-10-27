-- Ejecutar conectado a la base de datos empleados_db (por ejemplo, en el Query Tool de pgAdmin).
-- Crea las tablas y carga los catálogos iniciales.

CREATE TABLE tipo_documento (
    id     UUID         PRIMARY KEY,
    nombre VARCHAR(50)  NOT NULL UNIQUE
);

CREATE TABLE rol (
    id     UUID         PRIMARY KEY,
    nombre VARCHAR(50)  NOT NULL UNIQUE
);

CREATE TABLE empresa (
    id     UUID         PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nit    VARCHAR(20)  NOT NULL UNIQUE
);

CREATE TABLE usuario (
    id                UUID         PRIMARY KEY,
    nombre            VARCHAR(100) NOT NULL,
    documento         VARCHAR(20)  NOT NULL,
    tipo_documento_id UUID         NOT NULL REFERENCES tipo_documento (id),
    rol_id            UUID         NOT NULL REFERENCES rol (id),
    empresa_id        UUID         NOT NULL REFERENCES empresa (id),
    CONSTRAINT uk_usuario_documento UNIQUE (tipo_documento_id, documento)
);

CREATE INDEX idx_usuario_empresa ON usuario (empresa_id);

INSERT INTO tipo_documento (id, nombre) VALUES
    ('11111111-0000-0000-0000-000000000001', 'Cédula de ciudadanía'),
    ('11111111-0000-0000-0000-000000000002', 'Cédula de extranjería'),
    ('11111111-0000-0000-0000-000000000003', 'Pasaporte'),
    ('11111111-0000-0000-0000-000000000004', 'Tarjeta de identidad');

INSERT INTO rol (id, nombre) VALUES
    ('22222222-0000-0000-0000-000000000001', 'Administrador'),
    ('22222222-0000-0000-0000-000000000002', 'Gerente'),
    ('22222222-0000-0000-0000-000000000003', 'Operario');

INSERT INTO empresa (id, nombre, nit) VALUES
    ('33333333-0000-0000-0000-000000000001', 'Empresa Demo S.A.S.', '900123456-7');
