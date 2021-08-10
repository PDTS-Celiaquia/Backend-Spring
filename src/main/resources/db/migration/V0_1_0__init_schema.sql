-- celiaquia.alimentos definition

-- Drop table

-- DROP TABLE celiaquia.alimentos;

CREATE TABLE "celiaquia"."alimentos"
(
    "id"                            int8         NOT NULL,
    "acidos_grasos_monoinsaturados" float8       NOT NULL,
    "acidos_grasos_poliinsaturados" float8       NOT NULL,
    "acidos_grasos_saturados"       float8       NOT NULL,
    "agua"                          float8       NOT NULL,
    "calcio"                        float8       NOT NULL,
    "carbohidrato_disponible"       float8       NOT NULL,
    "carbohidrato_total"            float8       NOT NULL,
    "ceniza"                        float8       NOT NULL,
    "clasificacion"                 varchar(255) NULL,
    "colesterol"                    float8       NOT NULL,
    "energia_kj"                    float8       NOT NULL,
    "es_accesible"                  bool         NULL,
    "fibra_dietetica"               float8       NOT NULL,
    "fosforo"                       float8       NOT NULL,
    "variedad"                      varchar(255) NULL,
    "grasa_total"                   float8       NOT NULL,
    "hierro"                        float8       NOT NULL,
    "imagen"                        varchar(255) NULL,
    "niacina"                       float8       NOT NULL,
    "nombre"                        varchar(255) NULL,
    "potasio"                       float8       NOT NULL,
    "proteina"                      float8       NOT NULL,
    "rivoflavina"                   float8       NOT NULL,
    "sodio"                         float8       NOT NULL,
    "tiamina"                       float8       NOT NULL,
    "vitamina_c"                    float8       NOT NULL,
    "zinc"                          float8       NOT NULL,
    "tipo"                          varchar(500) NULL,
    CONSTRAINT "alimentos_pkey" PRIMARY KEY ("id")
);


-- celiaquia.authorities definition

-- Drop table

-- DROP TABLE celiaquia.authorities;

CREATE TABLE "celiaquia"."authorities"
(
    "authority" varchar(255) NOT NULL,
    CONSTRAINT "authorities_pkey" PRIMARY KEY ("authority")
);


-- celiaquia.recetas definition

-- Drop table

-- DROP TABLE celiaquia.recetas;

CREATE TABLE "celiaquia"."recetas"
(
    "id"                               bigserial    NOT NULL,
    "acidos_grasos_monoinsaturados"    float8       NOT NULL,
    "acidos_grasos_poliinsaturados"    float8       NOT NULL,
    "acidos_grasos_saturados"          float8       NOT NULL,
    "agua"                             float8       NOT NULL,
    "calcio"                           float8       NOT NULL,
    "cantidad_platos"                  int4         NULL,
    "cantidad_alimentos_accesibles"    int4         NOT NULL,
    "cantidad_alimentos_no_accesibles" int4         NOT NULL,
    "carbohidrato_disponible"          float8       NOT NULL,
    "carbohidrato_total"               float8       NOT NULL,
    "ceniza"                           float8       NOT NULL,
    "colesterol"                       float8       NOT NULL,
    "descripcion"                      varchar(255) NULL,
    "energia_kj"                       float8       NOT NULL,
    "fibra_dietetica"                  float8       NOT NULL,
    "fosforo"                          float8       NOT NULL,
    "grasa_total"                      float8       NOT NULL,
    "hierro"                           float8       NOT NULL,
    "instrucciones"                    varchar(255) NULL,
    "niacina"                          float8       NOT NULL,
    "nombre"                           varchar(255) NULL,
    "potasio"                          float8       NOT NULL,
    "proteina"                         float8       NOT NULL,
    "rivoflavina"                      float8       NOT NULL,
    "sodio"                            float8       NOT NULL,
    "tiamina"                          float8       NOT NULL,
    "vitamina_c"                       float8       NOT NULL,
    "zinc"                             float8       NOT NULL,
    CONSTRAINT "recetas_pkey" PRIMARY KEY ("id")
);

-- celiaquia.unidades_de_medida definition

-- Drop table

-- DROP TABLE celiaquia.unidades_de_medida;

CREATE TABLE "celiaquia"."unidades_de_medida"
(
    "id"     bigserial    NOT NULL,
    "nombre" varchar(255) NULL,
    CONSTRAINT "unidades_de_medida_pkey" PRIMARY KEY ("id")
);


-- celiaquia.ingredientes definition

-- Drop table

-- DROP TABLE celiaquia.ingredientes;

CREATE TABLE "celiaquia"."ingredientes"
(
    "id"                  bigserial NOT NULL,
    "cantidad"            float8    NOT NULL,
    "alimento_id"         int8      NULL,
    "unidad_de_medida_id" int8      NULL,
    CONSTRAINT "ingredientes_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "fkjbb0q6gva75yuy1vpi2i54317" FOREIGN KEY ("unidad_de_medida_id") REFERENCES "celiaquia"."unidades_de_medida" ("id"),
    CONSTRAINT "fkl6q27jhn4ahgup4gprdwlycqy" FOREIGN KEY ("alimento_id") REFERENCES "celiaquia"."alimentos" ("id")
);


-- celiaquia.recetas_ingredientes definition

-- Drop table

-- DROP TABLE celiaquia.recetas_ingredientes;

CREATE TABLE "celiaquia"."recetas_ingredientes"
(
    "receta_id"       int8 NOT NULL,
    "ingredientes_id" int8 NOT NULL,
    CONSTRAINT "recetas_ingredientes_pkey" PRIMARY KEY ("receta_id", "ingredientes_id"),
    CONSTRAINT "uk_nynldyvthik3fcxyl8svujnc7" UNIQUE ("ingredientes_id"),
    CONSTRAINT "fkri1tdg9ggtl0w1k3ljo5katpb" FOREIGN KEY ("ingredientes_id") REFERENCES "celiaquia"."ingredientes" ("id"),
    CONSTRAINT "fkt1aceirr9btp3yigo2hn0i9s8" FOREIGN KEY ("receta_id") REFERENCES "celiaquia"."recetas" ("id")
);


-- celiaquia.usuarios definition

-- Drop table

-- DROP TABLE celiaquia.usuarios;

CREATE TABLE "celiaquia"."usuarios"
(
    "id"         bigserial    NOT NULL,
    "apellido"   varchar(200) NULL,
    "email"      varchar(500) NULL,
    "fecha_alta" timestamp    NULL,
    "fecha_baja" timestamp    NULL,
    "nombre"     varchar(200) NULL,
    "password"   varchar(500) NULL,
    "authority"  varchar(255) NULL,
    CONSTRAINT "ukkfsp0s1tflm1cwlj8idhqsad0" UNIQUE ("email"),
    CONSTRAINT "usuarios_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "fkrp66hfs4c7pqwn8onhdufc3si" FOREIGN KEY ("authority") REFERENCES "celiaquia"."authorities" ("authority")
);


-- celiaquia.cuestionarios definition

-- Drop table

-- DROP TABLE celiaquia.cuestionarios;

CREATE TABLE "celiaquia"."cuestionarios"
(
    "id"             bigserial     NOT NULL,
    "carne"          bool          NULL,
    "celiaco"        bool          NULL,
    "diagnostico"    varchar(1200) NULL,
    "dieta_sintacc"  bool          NULL,
    "frecuenciatacc" int4          NULL,
    "frutas"         int4          NULL,
    "timestamp"      timestamp     NULL,
    "tratamiento"    bool          NULL,
    "verduras"       bool          NULL,
    "usuario_id"     int8          NULL,
    CONSTRAINT "cuestionarios_frecuenciatacc_check" CHECK ((("frecuenciatacc" >= 0) AND ("frecuenciatacc" <= 5))),
    CONSTRAINT "cuestionarios_frutas_check" CHECK (("frutas" >= 0)),
    CONSTRAINT "cuestionarios_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "fk8adkulaplnk5qtrftk7pvnojc" FOREIGN KEY ("usuario_id") REFERENCES "celiaquia"."usuarios" ("id")
);