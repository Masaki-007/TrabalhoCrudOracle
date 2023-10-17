DROP SEQUENCE funcionario_seq;

DROP SEQUENCE endereco_seq;
-- Script para remover a tabela Endereco, se existir
DROP TABLE Endereco;

-- Script para remover a tabela Funcionario, se existir
DROP TABLE Funcionario;

CREATE SEQUENCE funcionario_seq;

CREATE SEQUENCE endereco_seq;

-- Tabela Funcionario
CREATE TABLE Funcionario (
    funcionario_id NUMERIC DEFAULT funcionario_seq.nextval PRIMARY KEY,
    nome VARCHAR(255),
    sexo CHAR(1),
    idade INT,
    telefone VARCHAR(20),
    cpf CHAR(11),
    cargo VARCHAR(50),
    setor VARCHAR(50),
    salario DECIMAL(10, 2)
);

-- Tabela Endereco associada a Funcionario
CREATE TABLE Endereco (
    endereco_id NUMERIC DEFAULT endereco_seq.nextval PRIMARY KEY,
    funcionario_id INT,
    rua VARCHAR(255),
    numero_residencia VARCHAR(10),
    bairro VARCHAR(50),
    cep VARCHAR(8), 
    cidade VARCHAR(100),
    FOREIGN KEY (funcionario_id) REFERENCES Funcionario(funcionario_id)
);


