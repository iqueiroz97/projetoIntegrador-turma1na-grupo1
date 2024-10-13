import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entradaJogador = new Scanner(System.in);
        Utils utils = new Utils();

        int opcaoSelecionada;

        utils.banner();
        utils.terrorNoEspacoMorse();
        System.out.println();



        do {
            utils.mostraMenu();

            opcaoSelecionada = entradaJogador.nextInt();
        } while (opcaoSelecionada != 4);
    }
}