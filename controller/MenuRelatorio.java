package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import model.ConexaoBanco;

public class MenuRelatorio {

    public static void menuRelatorio(Scanner teclado) {
        char opcaoRelatorios;
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        do {
            System.out.println("Menu de Relatórios\n" +
                    "1. Listar todos os funcionários cadastrados\n" +
                    "2. Mostrar a soma do salário de todos os funcionários\n" +
                    "3. Voltar ao Menu Principal"
            );
            opcaoRelatorios = teclado.next().charAt(0);
            switch (opcaoRelatorios) {
                case '1':
                    System.out.println("Opção 1: Listar todos os funcionários cadastrados");
                    listarFuncionarios(conexaoBanco);
                    break;
                case '2':
                    System.out.println("Opção 2: Mostrar a soma do salário de todos os funcionários");
                    mostrarSomaSalarioFuncionarios(conexaoBanco);
                    break;
                case '3':
                    conexaoBanco.close(); 
                    MenuPrincipal.menuPrincipal();
                default:
                    System.out.println("Opção inválida. Tente novamente");
            }
        } while (opcaoRelatorios != '3');
    }

    private static void listarFuncionarios(ConexaoBanco conexaoBanco) {
        try (Connection conexao = conexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement("SELECT funcionario_id, nome, cargo, salario FROM Funcionario");
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("ID | Nome | Cargo | Salário");
            while (resultSet.next()) {
                int funcionarioId = resultSet.getInt("funcionario_id");
                String nome = resultSet.getString("nome");
                String cargo = resultSet.getString("cargo");
                double salario = resultSet.getDouble("salario");
                System.out.println(funcionarioId + " | " + nome + " | " + cargo + " | " + salario);
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