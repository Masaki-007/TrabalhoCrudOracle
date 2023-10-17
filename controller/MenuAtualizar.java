package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conexion.ConexaoBanco;

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
                        break;
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
        teclado.nextLine();
        
        System.out.print("Novo nome: ");
        String novoNome = teclado.next();
        teclado.nextLine();

        System.out.print("Nova idade: ");
        int novaIdade = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Novo Telefone: ");
        String novoTelefone = teclado.next();
        teclado.nextLine();

        System.out.println("Novo Cargo: ");
        String novoCargo = teclado.next();
        teclado.nextLine();

        System.out.println("Novo setor: ");
        String novoSetor = teclado.next();
        teclado.nextLine();
        
        System.out.println("Novo salário: ");
        double novoSalario = teclado.nextDouble();
        teclado.nextLine();


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
                exibirRegistroAtualizadoFuncionario(idFuncionario, conexao);
            } else {
                System.out.println("Nenhum funcionário foi atualizado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o funcionário: " + e.getMessage());
        }
    }

    private static void exibirRegistroAtualizadoFuncionario(int idFuncionario, Connection conexao) {
        try (PreparedStatement consultaStatement = conexao.prepareStatement("SELECT * FROM Funcionario WHERE funcionario_id = ?")) {
            consultaStatement.setInt(1, idFuncionario);
            ResultSet resultado = consultaStatement.executeQuery();
            if (resultado.next()) {
                System.out.println("Registro Atualizado:");
                System.out.println("ID: " + resultado.getInt("funcionario_id"));
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("Idade: " + resultado.getString("idade"));
                System.out.println("Telefone: " + resultado.getString("telefone"));
                System.out.println("Cargo: " + resultado.getString("cargo"));
                System.out.println("Setor: " + resultado.getString("setor"));
                System.out.println("Salario: " + resultado.getString("salario"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o registro atualizado: " + e.getMessage());
        }
    }

    private static void atualizarEndereco(Scanner teclado, ConexaoBanco conexaoBanco) {
        System.out.println("Opção 2: Atualizar Endereços");

        System.out.print("Informe o ID do endereço a ser atualizado: ");
        int idEndereco = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Nova rua: ");
        String novaRua = teclado.next();
        teclado.nextLine();

        System.out.print("Novo número da residência: ");
        String novoNumeroResidencia = teclado.next();
        teclado.nextLine();

        System.out.print("Novo Bairro: ");
        String novoBairro = teclado.next();
        teclado.nextLine();

        System.out.print("Novo CEP: ");
        String novoCep = teclado.next();
        teclado.nextLine();

        System.out.print("Nova Cidade: ");
        String novaCidade = teclado.next();
        teclado.nextLine();


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
                exibirRegistroAtualizadoEndereco(idEndereco, conexao);
            } else {
                System.out.println("Nenhum endereço foi atualizado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o endereço: " + e.getMessage());
        }
    }

    private static void exibirRegistroAtualizadoEndereco(int idEndereco, Connection conexao) {
        try (PreparedStatement consultaStatement = conexao.prepareStatement("SELECT * FROM Endereco WHERE endereco_id = ?")) {
            consultaStatement.setInt(1, idEndereco);
            ResultSet resultado = consultaStatement.executeQuery();
            if (resultado.next()) {
                System.out.println("Registro Atualizado:");
                System.out.println("ID: " + resultado.getInt("endereco_id"));
                System.out.println("Rua: " + resultado.getString("rua"));
                System.out.println("Bairro: " + resultado.getString("bairro"));
                System.out.println("Cep: " + resultado.getString("cep"));
                System.out.println("Cidade: " + resultado.getString("cidade"));
                System.out.println("Endereco: " + resultado.getString("endereco"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o registro atualizado: " + e.getMessage());
        }
    }
}
