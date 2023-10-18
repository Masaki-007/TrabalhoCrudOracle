# Sistema de Gerenciamento de RH

Este é um sistema simples de gerenciamento de Recursos Humanos (RH) que permite a inserção, remoção, atualização e geração de relatórios para funcionários e endereços com a soma do salário de todos os funcionários inseridos. O projeto inclui um banco de dados Oracle e uma aplicação Java para interagir com o banco de dados.

## Pré-requisitos

- Sistema operacional Linux
- Java 8 ou superior
- Oracle Database

## Configuração do Banco de Dados

Certifique-se de que o Oracle Database esteja configurado e em execução. Precisa-se de baixar o drive JDBC para conexão do java com o oracle (Pasta lib no projeto com os arquvios ojdbc8.jar). Você pode usar SQL Developer para executar os scripts SQL fornecidos para criar as tabelas e sequências necessárias:

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
 
 
## Executando o Projeto 
Clone este repositório:

    https://github.com/Masaki-007/TrabalhoCrudOracle.git 

 Execute o aplicativo: 
 java Main 
 OBS: Se rodar na IDE do vscode, terá que baixar a extensão "pack for java"  para poder executar.

O aplicativo será iniciado e você verá o menu principal. Você pode interagir com o sistema para inserir, remover, atualizar funcionários e endereços, além de gerar relatórios.

- Como Usar

Siga as opções no menu principal para interagir com o sistema. Você pode inserir, remover, atualizar registros de funcionários e endereços, bem como gerar relatórios.

- Video Explicativo de como utilizar o programa:
    
    Link: https://www.youtube.com/watch?v=CM5tBexGzhY&t=59s


    
