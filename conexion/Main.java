package conexion;

import controller.MenuPrincipal;
import utils.SplashScreen;

public class Main {
    public static void main(String[] args) {
        
        ConexaoBanco conexao = new ConexaoBanco();
        
        

        if (conexao.getConexao() != null) {
            System.out.println("Conexão bem-sucedida com o banco de dados Oracle.");
            SplashScreen.showSplashScreen();
            MenuPrincipal.menuPrincipal();


            conexao.close();
        } else {
            System.out.println("Falha na conexão com o banco de dados Oracle.");
        }
    }
}



