-- Inserir dados aleatórios na tabela Funcionario
INSERT INTO Funcionario (funcionario_id, nome, sexo, idade, telefone, cpf, cargo, setor, salario)
VALUES (1, 'João da Silva', 'M', 30, '123-456-7890', '12345678901', 'Gerente', 'Vendas', 5000.00);

INSERT INTO Funcionario (funcionario_id, nome, sexo, idade, telefone, cpf, cargo, setor, salario)
VALUES (2, 'Maria Oliveira', 'F', 25, '987-654-3210', '98765432102', 'Analista', 'Tecnologia', 4000.00);

INSERT INTO Funcionario (funcionario_id, nome, sexo, idade, telefone, cpf, cargo, setor, salario)
VALUES (3, 'Carlos Pereira', 'M', 28, '555-123-4567', '55512345603', 'Assistente', 'Administração', 3000.00);

INSERT INTO Funcionario (funcionario_id, nome, sexo, idade, telefone, cpf, cargo, setor, salario)
VALUES (4, 'Ana Souza', 'F', 35, '111-222-3333', '11122233304', 'Supervisor', 'Produção', 4500.00);

INSERT INTO Funcionario (funcionario_id, nome, sexo, idade, telefone, cpf, cargo, setor, salario)
VALUES (5, 'Lucas Costa', 'M', 22, '777-888-9999', '77788899905', 'Estagiário', 'Recursos Humanos', 2500.00);

-- Inserir dados aleatórios na tabela Endereco (um para cada funcionário)
INSERT INTO Endereco (endereco_id, funcionario_id, rua, numero_residencia, bairro, cep, cidade)
VALUES
    (1, 1, 'Avenida Principal', '123', 'Centro', '12345-678', 'Cidade A'),
    (2, 2, 'Rua da Amizade', '456', 'Bairro 1', '98765-432', 'Cidade B'),
    (3, 3, 'Avenida Comercial', '789', 'Bairro 2', '55555-555', 'Cidade C'),
    (4, 4, 'Rua da Produção', '101', 'Bairro 3', '11111-222', 'Cidade D'),
    (5, 5, 'Rua dos Estagiários', '555', 'Bairro 4', '77777-888', 'Cidade E');
