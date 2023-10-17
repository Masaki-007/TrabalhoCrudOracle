# Sistema de Gerenciamento de RH

Este é um sistema simples de gerenciamento de Recursos Humanos (RH) que permite a inserção, remoção, atualização e geração de relatórios para funcionários e endereços com a soma do salário de todos os funcionários inseridos. O projeto inclui um banco de dados Oracle e uma aplicação Java para interagir com o banco de dados.

## Pré-requisitos

- Sistema operacional Linux
- Java 8 ou superior
- Oracle Database

## Configuração do Banco de Dados

Certifique-se de que o Oracle Database esteja configurado e em execução. Você pode usar SQL Developer para executar os scripts SQL fornecidos para criar as tabelas e sequências necessárias:

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



## Executando o Projeto

    Clone este repositório:

git clone: https://github.com/Masaki-007/TrabalhoCrudOracle.git

    Execute o aplicativo:

java Main

O aplicativo será iniciado e você verá o menu principal. Você pode interagir com o sistema para inserir, remover, atualizar funcionários e endereços, além de gerar relatórios.

- Como Usar

Siga as opções no menu principal para interagir com o sistema. Você pode inserir, remover, atualizar registros de funcionários e endereços, bem como gerar relatórios.

- Video Explicativo de como utilizar o programa
    
    Link:


    
