package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                        "1. Remover Entidade Funcionário ou Endereço\n" +
                        "2. Voltar ao Menu Principal"                     
                );
                opcaoRemover = teclado.next().charAt(0);
                switch (opcaoRemover) {
                    case '1':
                        removerEntidade("Funcionário", conexaoBanco, teclado);
                        break;
                    case '2':
                        return; 
                    default:
                        System.out.println("Opção inválida. Tente novamente");
                }
            } while (opcaoRemover != '2');
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

            if (entidade.equals("Funcionário")) {
                // Verifica se existem endereços associados a este funcionário
                if (possuiEnderecosAssociados(id, conexaoBanco)) {
                    System.out.println("Este funcionário possui endereços associados:");
                    listarEnderecosAssociados(id, conexaoBanco);

                    System.out.print("Deseja remover também os endereços associados a este funcionário? (S/N): ");
                    String confirmacao = teclado.next();
                    if (confirmacao.equalsIgnoreCase("S")) {
                        // Exclui os endereços associados antes de remover o funcionário
                        excluirEnderecosAssociados(id, conexaoBanco);
                    }
                }
            }

            if (sql.equals("DELETE FROM Funcionario WHERE funcionario_id = ?")) {
                System.out.print("Deseja realmente excluir a entidade " + entidade + "? (S/N): ");
                String confirmacao = teclado.next();
                if (confirmacao.equalsIgnoreCase("S")) {
                    try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                        statement.setInt(1, id);
                        int linhasAfetadas = statement.executeUpdate();
                        if (linhasAfetadas > 0) {
                            conexao.commit();
                            System.out.println(entidade + " removido com sucesso!");
                        } else {
                            System.out.println(entidade + " com ID " + id + " não foi encontrado.");
                        }
                    } catch (SQLException e) {
                        System.err.println("Erro ao remover a entidade " + entidade + ": " + e.getMessage());
                        conexao.rollback();
                    }
                } else {
                    System.out.println("A exclusão do " + entidade + " foi cancelada.");
                }
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

    private static boolean possuiEnderecosAssociados(int funcionarioId, ConexaoBanco conexaoBanco) {
        try {
            String sql = "SELECT COUNT(*) FROM Endereco WHERE funcionario_id = ?";
            PreparedStatement statement = conexaoBanco.getConexao().prepareStatement(sql);
            statement.setInt(1, funcionarioId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar endereços associados: " + e.getMessage());
        }
        return false;
    }

    private static void listarEnderecosAssociados(int funcionarioId, ConexaoBanco conexaoBanco) {
        try {
            String sql = "SELECT endereco_id, rua FROM Endereco WHERE funcionario_id = ?";
            PreparedStatement statement = conexaoBanco.getConexao().prepareStatement(sql);
            statement.setInt(1, funcionarioId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int enderecoId = resultSet.getInt("endereco_id");
                String rua = resultSet.getString("rua");
                System.out.println("Endereço ID: " + enderecoId + ", Rua: " + rua);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar endereços associados: " + e.getMessage());
        }
    }

    private static void excluirEnderecosAssociados(int funcionarioId, ConexaoBanco conexaoBanco) {
        try {
            String sql = "DELETE FROM Endereco WHERE funcionario_id = ?";
            PreparedStatement statement = conexaoBanco.getConexao().prepareStatement(sql);
            statement.setInt(1, funcionarioId);
            statement.executeUpdate();
            System.out.println("Endereços associados excluídos com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir endereços associados: " + e.getMessage());
        }
    }
}
