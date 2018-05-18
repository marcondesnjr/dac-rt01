
CREATE TABLE public.banda
(
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    localdeorigem character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT banda_pkey PRIMARY KEY (nome)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.banda
    OWNER to postgres;

CREATE TABLE public.album
(
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    banda character varying(50) COLLATE pg_catalog."default",
    estilo character varying(50) COLLATE pg_catalog."default",
    anodelancamento date NOT NULL,
    CONSTRAINT album_pkey PRIMARY KEY (nome),
    CONSTRAINT album_banda_fkey FOREIGN KEY (banda)
        REFERENCES public.banda (nome) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.album
    OWNER to postgres;

CREATE TABLE public.integrante
(
    id SERIAL,
    nome character varying(50) COLLATE pg_catalog."default",
    banda character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT integrantes_pkey PRIMARY KEY (id),
    CONSTRAINT integrantes_banda_fkey FOREIGN KEY (banda)
        REFERENCES public.banda (nome) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.integrante
    OWNER to postgres;