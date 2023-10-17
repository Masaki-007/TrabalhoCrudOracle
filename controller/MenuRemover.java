package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import conexion.ConexaoBanco;

public class MenuRemover {
    public static void menuRemover(Scanner teclado) {
        char opcaoRemover;
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        try {
            do {
                System.out.println("Menu de Remoção\n" +
                        "1. Remover Entidade Funcionário\n" +
                        "2. Remover Entidade Endereço\n" +
                        "3. Voltar ao Menu Principal"
                );
                opcaoRemover = teclado.next().charAt(0);
                switch (opcaoRemover) {
                    case '1':
                        removerEntidade("Funcionário", conexaoBanco, teclado);
                        break;
                    case '2':
                        removerEntidade("Endereço", conexaoBanco, teclado);
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

    private static void removerEntidade(String entidade, ConexaoBanco conexaoBanco, Scanner teclado) {
        System.out.println("Opção: Remover Entidade " + entidade);
        System.out.print("Informe o ID da " + entidade + " que deseja remover: ");
        int id = teclado.nextInt();

        String sql = "";
        if (entidade.equals("Funcionário")) {
            sql = "DELETE FROM Funcionario WHERE funcionario_id = ?";
        } else if (entidade.equals("Endereço")) {
            sql = "DELETE FROM Endereco WHERE endereco_id = ?";
        }

        Connection conexao = conexaoBanco.getConexao();
        try {
            conexao.setAutoCommit(false); 

            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setInt(1, id);
                int linhasAfetadas = statement.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.print("Deseja realmente excluir a entidade " + entidade + "? (S/N): ");
                    String confirmacao = teclado.next();
                    if (confirmacao.equalsIgnoreCase("S")) {
                        conexao.commit(); 
                        System.out.println(entidade + " removido com sucesso!");
                    } else {
                        conexao.rollback();
                        System.out.println("A exclusão do " + entidade + " foi cancelada.");
                    }
                } else {
                    System.out.println(entidade + " com ID " + id + " não foi encontrado.");
                }
            } catch (SQLException e) {
                System.err.println("Erro ao remover a entidade " + entidade + ": " + e.getMessage());
                conexao.rollback();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao configurar a transação: " + e.getMessage());
        } finally {
            try {
                conexao.setAutoCommit(true); 
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
