package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoBanco {
    private String host = "Localhost";
    private String database = "XE";
    private String user = "labdatabase";
    private String password = "labDatabase2022";
    private int port = 1521;
    private Connection conn = null;

    public ConexaoBanco() {
        conectar();
    }

    private void conectar() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + database;
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver JDBC do Oracle: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados Oracle: " + e.getMessage());
        }
    }

    public Connection getConexao() {
        return conn;
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }

    public int contarRegistros(String tabela) {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) FROM " + tabela;
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            result.next();
            count = result.getInt(1);
        } catch (SQLException e) {
            System.err.println("Erro ao contar registros na tabela " + tabela + ": " + e.getMessage());
        }
        return count;

    }
}


