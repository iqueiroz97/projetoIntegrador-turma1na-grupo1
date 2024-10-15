import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int ENCERRA_GAME = 5;

        Utils utils = new Utils();
        Scanner entradaJogador = new Scanner(System.in);

        int opcaoSelecionada;

        utils.banner();

        do {

            utils.mostraMenu();

            System.out.print("\nSelecione uma opção: ");
            opcaoSelecionada = entradaJogador.nextInt();

            utils.opcoesMenu(opcaoSelecionada);
        } while (opcaoSelecionada != ENCERRA_GAME);

        entradaJogador.close();
    }
}
