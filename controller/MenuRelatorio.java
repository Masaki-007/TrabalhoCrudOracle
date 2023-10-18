package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conexion.ConexaoBanco;

public class MenuRelatorio {

    public static void menuRelatorio(Scanner teclado) {
        char opcaoRelatorios;
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        do {
            System.out.println("Menu de Relatórios\n" +
                    "1. Listar todos os funcionários cadastrados com endereço\n" +
                    "2. Mostrar a soma do salário de todos os funcionários\n" +
                    "3. Voltar ao Menu Principal"
            );
            opcaoRelatorios = teclado.next().charAt(0);
            switch (opcaoRelatorios) {
                case '1':
                    System.out.println("Opção 1: Listar todos os funcionários cadastrados com endereço");
                    listarFuncionariosComEndereco(conexaoBanco);
                    break;
                case '2':
                    System.out.println("Opção 2: Mostrar a soma do salário de todos os funcionários");
                    mostrarSomaSalarioFuncionarios(conexaoBanco);
                    break;
                case '3':
                    conexaoBanco.close();
                    MenuPrincipal.menuPrincipal();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");
            }
        } while (opcaoRelatorios != '3');
    }

    private static void listarFuncionariosComEndereco(ConexaoBanco conexaoBanco) {
        try (Connection conexao = conexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement(
                     "SELECT f.funcionario_id, f.nome, f.sexo, f.idade, f.telefone, f.cpf, f.cargo, f.setor, f.salario, e.rua, e.numero_residencia, e.bairro, e.cep, e.cidade " +
                             "FROM Funcionario f " +
                             "LEFT JOIN Endereco e ON f.funcionario_id = e.funcionario_id");
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("ID | Nome | Sexo | Idade | Telefone | CPF | Cargo | Setor | Salário | Rua | Número | Bairro | CEP | Cidade");
            while (resultSet.next()) {
                int funcionarioId = resultSet.getInt("funcionario_id");
                String nome = resultSet.getString("nome");
                char sexo = resultSet.getString("sexo").charAt(0);
                int idade = resultSet.getInt("idade");
                String telefone = resultSet.getString("telefone");
                String cpf = resultSet.getString("cpf");
                String cargo = resultSet.getString("cargo");
                String setor = resultSet.getString("setor");
                double salario = resultSet.getDouble("salario");
                String rua = resultSet.getString("rua");
                String numero = resultSet.getString("numero_residencia");
                String bairro = resultSet.getString("bairro");
                String cep = resultSet.getString("cep");
                String cidade = resultSet.getString("cidade");

                System.out.println(funcionarioId + " | " + nome + " | " + sexo + " | " + idade + " | " + telefone + " | " + cpf + " | " + cargo + " | " + setor + " | " + salario + " | " + rua + " | " + numero + " | " + bairro + " | " + cep + " | " + cidade);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
        }
    }

    private static void mostrarSomaSalarioFuncionarios(ConexaoBanco conexaoBanco) {
        try (Connection conexao = conexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement("SELECT SUM(salario) as somaSalario FROM Funcionario");
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                double somaSalario = resultSet.getDouble("somaSalario");
                System.out.println("Soma do salário de todos os funcionários: " + somaSalario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao calcular a soma do salário: " + e.getMessage());
        }
    }
}

