CREATE TABLE empresa (
    id BIGINT NOT NULL,
    razaosocial VARCHAR(100) NOT NULL,
    nomefantasia VARCHAR(100) NOT NULL,
    cnpj VARCHAR(15) NOT NULL,
    fundacao DATE NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    dataregistro DATE NOT NULL,
    PRIMARY KEY(id)
);

CREATE SEQUENCE empresa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE funcionario (
    id BIGINT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    datanasc DATE NOT NULL,
    sexo VARCHAR(12) NOT NULL,
    dataregistro DATE NOT NULL,
    empresaid BIGINT NOT NULL,
    dataadmissao DATE NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_empresa
        FOREIGN KEY(empresaid)
            REFERENCES empresa(id)
);

CREATE SEQUENCE funcionario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;