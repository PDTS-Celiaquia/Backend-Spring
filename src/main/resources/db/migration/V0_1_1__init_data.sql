START TRANSACTION;

INSERT INTO "celiaquia"."unidades_de_medida" ("id", "nombre")
VALUES (0, 'Gramo'),
       (1, 'Litro'),
       (2, 'Cucharada sopera'),
       (3, 'Cucharada de té'),
       (4, 'Taza'),
       (5, 'Unidad');

INSERT INTO "celiaquia"."authorities" ("authority")
VALUES ('ADMIN'),
       ('OPERARIO'),
       ('PACIENTE');

COMMIT;
