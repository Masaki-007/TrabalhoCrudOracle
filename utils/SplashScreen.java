package utils;
import conexion.ConexaoBanco;

public class SplashScreen {
    public static void showSplashScreen() {
        ConexaoBanco conexao = new ConexaoBanco();
        int countFuncionario = conexao.contarRegistros("funcionário");
        int countEndereco = conexao.contarRegistros("endereço");

        System.out.println("--------------------------------------------");
        System.out.println("#           SISTEMA DE RH                    #");
        System.out.println("#                                            #");
        System.out.println("#   REGISTROS:                               #");
        System.out.println("#   FUNCIONARIO: " + countFuncionario + "      #");
        System.out.println("#   ENDEREÇO: " + countEndereco + "            #");
        System.out.println("#                                            #");
        System.out.println("#   DESENVOLVIDO POR:                        #");
        System.out.println("#   JOÃO ANTONIO CARNEIRO VASCONCELOS        #");
        System.out.println("#   GABRIEL SALOMÃO PANCIONE                 #");
        System.out.println("#   MATHEUS COSTA RODRIGUES                  #");
        System.out.println("#                                            #");
        System.out.println("#   DISCIPLINA: BANCO DE DADOS               #");
        System.out.println("#   2023/2                                   #");
        System.out.println("#                                            #");
        System.out.println("#   PROFESSOR: HOWARD ROATTI                 #");
        System.out.println("----------------------------------------------");
    }
}
