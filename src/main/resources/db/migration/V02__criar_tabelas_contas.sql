CREATE TABLE contaempresa (
    id BIGINT NOT NULL,
    saldo NUMERIC(10, 2) NOT NULL,
    empresaid BIGINT NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_contaempresa
        FOREIGN KEY(empresaid)
            REFERENCES empresa(id)
);

CREATE SEQUENCE contaempresa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE contafuncionario (
    id BIGINT NOT NULL,
    saldo NUMERIC(10, 2) NOT NULL,
    funcionarioid BIGINT NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_contafuncionario
        FOREIGN KEY(funcionarioid)
            REFERENCES funcionario(id)
);

CREATE SEQUENCE contafuncionario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;