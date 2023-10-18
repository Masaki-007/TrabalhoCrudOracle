package controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import conexion.ConexaoBanco;

public class MenuInserir {
    public static void menuInserir(Scanner teclado) {
        char opcaoCadastro;
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        do {
            System.out.println("Menu de Cadastros\n" +
                    "1. Cadastrar Funcionários\n" +
                    "2. Cadastrar Endereços\n" +
                    "3. Voltar ao Menu Principal"
            );
            opcaoCadastro = teclado.next().charAt(0);
            switch (opcaoCadastro) {
                case '1':
                    cadastrarFuncionario(teclado, conexaoBanco);
                    break;
                case '2':
                    cadastrarEndereco(teclado, conexaoBanco);
                    break;
                case '3':
                    MenuPrincipal.menuPrincipal();
                    break;  
                default:
                    System.out.println("Opção inválida. Tente novamente");
            }
        } while (opcaoCadastro != '3');
    }

    private static void cadastrarFuncionario(Scanner teclado, ConexaoBanco conexaoBanco) {
        System.out.println("Opção 1: Cadastro de Funcionários");

        
        System.out.print("Nome: ");
        String nome = teclado.next();
        teclado.nextLine();

        System.out.print("Sexo (M/F): ");
        char sexo = teclado.next().charAt(0);
        teclado.nextLine();

        System.out.print("Idade: ");
        int idade = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Telefone: ");
        String telefone = teclado.next();
        teclado.nextLine();

        System.out.print("CPF: ");
        String cpf = teclado.next();
        teclado.nextLine();

        System.out.print("Cargo: ");
        String cargo = teclado.next();
        teclado.nextLine();

        System.out.print("Setor: ");
        String setor = teclado.next();
        teclado.nextLine();

        System.out.print("Salário: ");
        double salario = teclado.nextDouble();
        teclado.nextLine();

        
        try {
            String sql = "INSERT INTO Funcionario (nome, sexo, idade, telefone, cpf, cargo, setor, salario) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexaoBanco.getConexao().prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, String.valueOf(sexo));
            statement.setInt(3, idade);
            statement.setString(4, telefone);
            statement.setString(5, cpf);
            statement.setString(6, cargo);
            statement.setString(7, setor);
            statement.setDouble(8, salario);
            statement.executeUpdate();
            System.out.println("Funcionário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    private static void cadastrarEndereco(Scanner teclado, ConexaoBanco conexaoBanco) {
        System.out.println("Opção 2: Cadastro de Endereços");

        listarFuncionarios(conexaoBanco);

        System.out.print("Selecione o ID do funcionário ao qual deseja adicionar o endereço: ");
        int funcionarioId = teclado.nextInt();
        teclado.nextLine(); 

        System.out.print("Rua: ");
        String rua = teclado.next();
        teclado.nextLine();

        System.out.print("Número da Residência: ");
        String numeroResidencia = teclado.next();
        teclado.nextLine();

        System.out.print("Bairro: ");
        String bairro = teclado.next();
        teclado.nextLine();

        System.out.print("CEP: ");
        String cep = teclado.next();
        teclado.nextLine();

        System.out.print("Cidade: ");
        String cidade = teclado.next();
        teclado.nextLine();


        try {
            String sql = "INSERT INTO Endereco (funcionario_id, rua, numero_residencia, bairro, cep, cidade) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexaoBanco.getConexao().prepareStatement(sql);
            statement.setInt(1, funcionarioId);
            statement.setString(2, rua);
            statement.setString(3, numeroResidencia);
            statement.setString(4, bairro);
            statement.setString(5, cep);
            statement.setString(6, cidade);
            statement.executeUpdate();
            System.out.println("Endereço cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar endereço: " + e.getMessage());
        }
    }

    private static void listarFuncionarios(ConexaoBanco conexaoBanco) {
        System.out.println("Funcionários Disponíveis:");
        try {
            String sql = "SELECT funcionario_id, nome FROM Funcionario";
            PreparedStatement statement = conexaoBanco.getConexao().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int funcionarioId = resultSet.getInt("funcionario_id");
                String nome = resultSet.getString("nome");
                System.out.println(funcionarioId + ": " + nome);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
        }
    }
}
