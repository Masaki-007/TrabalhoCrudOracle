package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import model.ConexaoBanco;

public class MenuAtualizar {
    public static void menuAtualizar(Scanner teclado) {
        char opcaoAtualizar;
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        try {
            do {
                System.out.println("Menu de Atualização\n" +
                        "1. Atualizar Funcionários\n" +
                        "2. Atualizar Endereços\n" +
                        "3. Voltar ao Menu Principal"
                );
                opcaoAtualizar = teclado.next().charAt(0);
                switch (opcaoAtualizar) {
                    case '1':
                        atualizarFuncionario(teclado, conexaoBanco);
                        break;
                    case '2':
                        atualizarEndereco(teclado, conexaoBanco);
                        break;
                    case '3':
                        MenuPrincipal.menuPrincipal();
                    default:
                        System.out.println("Opção inválida. Tente novamente");
                }
            } while (opcaoAtualizar != '3');
        } finally {
            conexaoBanco.close(); 
        }
    }

    private static void atualizarFuncionario(Scanner teclado, ConexaoBanco conexaoBanco) {
        System.out.println("Opção 1: Atualizar Funcionários");

        
        System.out.print("Informe o ID do funcionário a ser atualizado: ");
        int idFuncionario = teclado.nextInt();

        
        System.out.print("Novo nome: ");
        String novoNome = teclado.next();
        System.out.print("Nova idade: ");
        int novaIdade = teclado.nextInt();
        System.out.println("Novo Telefone: ");
        String novoTelefone = teclado.next();
        System.out.println("Novo Cargo: ");
        String novoCargo = teclado.next();
        System.out.println("Novo setor: ");
        String novoSetor = teclado.next();
        System.out.println("Novo salário: ");
        double novoSalario = teclado.nextDouble();
        try (Connection conexao = conexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement(
                     "UPDATE Funcionario SET nome = ?, idade = ?, telefone = ?, cargo = ?, setor = ?, salario = ? WHERE funcionario_id = ?")) {
            statement.setString(1, novoNome);
            statement.setInt(2, novaIdade);
            statement.setString(3, novoTelefone);
            statement.setString(4, novoCargo);
            statement.setString(5, novoSetor);
            statement.setDouble(6, novoSalario);
            statement.setInt(7, idFuncionario);
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Funcionário atualizado com sucesso!");
            } else {
                System.out.println("Nenhum funcionário foi atualizado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o funcionário: " + e.getMessage());
        }
    }

    private static void atualizarEndereco(Scanner teclado, ConexaoBanco conexaoBanco) {
        System.out.println("Opção 2: Atualizar Endereços");

        
        System.out.print("Informe o ID do endereço a ser atualizado: ");
        int idEndereco = teclado.nextInt();
        
        System.out.print("Nova rua: ");
        String novaRua = teclado.next();
        System.out.print("Novo número da residência: ");
        String novoNumeroResidencia = teclado.next();
        System.out.print("Novo Bairro: ");
        String novoBairro = teclado.next();
        System.out.print("Novo CEP: ");
        String novoCep = teclado.next();
        System.out.print("Nova Cidade: ");
        String novaCidade = teclado.next();
        try (Connection conexao = conexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement(
                     "UPDATE Endereco SET rua = ?, numero_residencia = ?, bairro = ?, cep = ?, cidade = ? WHERE endereco_id = ?")) {
            statement.setString(1, novaRua);
            statement.setString(2, novoNumeroResidencia);
            statement.setString(3, novoBairro);
            statement.setString(4, novoCep);
            statement.setString(5, novaCidade);
            statement.setInt(6, idEndereco);
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Endereço atualizado com sucesso!");
            } else {
                System.out.println("Nenhum endereço foi atualizado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o endereço: " + e.getMessage());
        }
    }
}