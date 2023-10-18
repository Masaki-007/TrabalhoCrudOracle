package controller;
import java.util.Scanner;


public class MenuPrincipal {
    public static void menuPrincipal() {
        Scanner teclado = new Scanner(System.in);
        
        char opcao;
        do {
    System.out.println("Menu Principal\n" +
    "1. INSERIR REGISTROS\n" +
    "2. REMOVER REGISTROS\n" +
    "3. ATUALIZAR REGISTROS\n" +
    "4. RELATORIOS\n"+
    "5. SAIR"
    );
    opcao = teclado.next().charAt(0);
    switch (opcao) {
        case '1':
            MenuInserir.menuInserir(teclado);
            break;
        case '2':
            MenuRemover.menuRemover(teclado);
            break;
        case '3':
            MenuAtualizar.menuAtualizar(teclado);
            break;
        case '4':
            MenuRelatorio.menuRelatorio(teclado);
            break;
        case '5':
            break;
        default:
            System.out.println("Opção inválida. Tente novamente");
    }
} while (opcao != '5');

    System.out.println("Fim do Programa!");
    }
}
