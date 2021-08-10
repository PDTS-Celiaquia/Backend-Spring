-- PASSWORD 123456
INSERT INTO "celiaquia"."usuarios" ("nombre", "apellido", "email", "password",
                        "authority", "fecha_alta", "fecha_baja")
VALUES ('ADMIN', 'ADMIN', 'admin@gmail.com',
        '$2a$10$FF5.V1ixfUl0guee3cdvcuoC8NUM0g1X6/XEuJVktyCmDY/NJdeLW',
        'ADMIN', CURRENT_DATE, NULL);