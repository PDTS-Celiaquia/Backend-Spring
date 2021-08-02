INSERT INTO "unidades_de_medida" ("id", "nombre")
VALUES (0, 'Gramo'),
       (1, 'Litro'),
       (2, 'Cucharada sopera'),
       (3, 'Cucharada de té'),
       (4, 'Taza'),
       (5, 'Unidad');

INSERT INTO "authorities" ("authority")
VALUES ('ADMIN'),
       ('OPERARIO'),
       ('PACIENTE');

-- PASSWORD 123456
INSERT INTO "usuarios" ("nombre", "apellido", "email", "PASSWORD",
                        "authority", "fecha_alta", "fecha_baja")
VALUES ('ADMIN', 'ADMIN', 'admin@gmail.com',
        '$2a$10$FF5.V1ixfUl0guee3cdvcuoC8NUM0g1X6/XEuJVktyCmDY/NJdeLW',
        'ADMIN', CURRENT_DATE, NULL);