START TRANSACTION;

CREATE TABLE "celiaquia"."tips"
(
    "id"     int8 NOT NULL,
    "titulo" varchar(255) NOT NULL,
    "descripcion"  varchar(255) NULL,
    "enlace"  varchar(255) NULL,
    CONSTRAINT "tips_pkey" PRIMARY KEY ("id")
);

COMMIT;