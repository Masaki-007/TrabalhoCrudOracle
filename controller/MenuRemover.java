
package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import model.ConexaoBanco;

public class MenuRemover {
    public static void menuRemover(Scanner teclado) {
        char opcaoRemover;
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        try {
            do {
                System.out.println("Menu de Remoção\n" +
                        "1. Mostrar Entidade Funcionário\n" +
                        "2. Mostrar Entidade Endereço\n" +
                        "3. Voltar ao Menu Principal"
                );
                opcaoRemover = teclado.next().charAt(0);
                switch (opcaoRemover) {
                    case '1':
                        mostrarEntidade("Funcionário", conexaoBanco);
                        break;
                    case '2':
                        mostrarEntidade("Endereço", conexaoBanco);
                        break;
                    case '3':
                        return; // Retorna ao menu principal
                    default:
                        System.out.println("Opção inválida. Tente novamente");
                }
            } while (opcaoRemover != '3');
        } finally {
            conexaoBanco.close(); 
        }
    }

    private static void mostrarEntidade(String entidade, ConexaoBanco conexaoBanco) {
        System.out.println("Opção: Mostrar Entidade " + entidade);
        // Consultar o banco de dados para recuperar informações
        String sql = "";
        if (entidade.equals("Funcionário")) {
            sql = "SELECT funcionario_id, nome, sexo, idade, telefone, cpf, cargo, setor, salario FROM Funcionario";
        } else if (entidade.equals("Endereço")) {
            sql = "SELECT endereco_id, funcionario_id, rua, numero_residencia, bairro, cep, cidade FROM Endereco";
        }
        try (Connection conexao = conexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt(1));
                System.out.println("Nome: " + resultSet.getString(2));
                System.out.println("Sexo: " + resultSet.getString(3));
                System.out.println("Idade: " + resultSet.getInt(4));
                System.out.println("Telefone: " + resultSet.getString(5));
                System.out.println("CPF: " + resultSet.getString(6));
                System.out.println("Cargo: " + resultSet.getString(7));
                System.out.println("Setor: " + resultSet.getString(8));
                if (entidade.equals("Funcionário")) {
                    System.out.println("Salário: " + resultSet.getDouble(9));
                }
                System.out.println("----------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao mostrar a entidade " + entidade + ": " + e.getMessage());
        }
    }
}