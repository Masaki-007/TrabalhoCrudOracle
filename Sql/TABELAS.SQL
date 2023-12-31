DROP SEQUENCE funcionario_seq;
DROP SEQUENCE endereco_seq;
DROP TABLE Endereco;
DROP TABLE Funcionario;

CREATE SEQUENCE funcionario_seq;
CREATE SEQUENCE endereco_seq;

-- Tabela Funcionario
CREATE TABLE Funcionario (
    funcionario_id NUMBER DEFAULT funcionario_seq.nextval PRIMARY KEY,
    nome VARCHAR2(255) NOT NULL,
    sexo CHAR(1) NOT NULL,
    idade NUMBER NOT NULL,
    telefone VARCHAR2(20) NOT NULL,
    cpf CHAR(11) NOT NULL,
    cargo VARCHAR2(50) NOT NULL,
    setor VARCHAR2(50) NOT NULL,
    salario NUMBER(10, 2) NOT NULL
);

-- Tabela Endereco associada a Funcionario
CREATE TABLE Endereco (
    endereco_id NUMBER DEFAULT endereco_seq.nextval PRIMARY KEY,
    funcionario_id NUMBER NOT NULL,
    rua VARCHAR2(255) NOT NULL,
    numero_residencia VARCHAR2(10) NOT NULL,
    bairro VARCHAR2(50) NOT NULL,
    cep VARCHAR2(8) NOT NULL,
    cidade VARCHAR2(100) NOT NULL,
    FOREIGN KEY (funcionario_id) REFERENCES Funcionario(funcionario_id)
);
 
 