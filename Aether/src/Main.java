import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();
        Scanner entradaJogador = new Scanner(System.in);

        utils.banner();

        do {
            utils.mostraMenu();

            System.out.print("\nSelecione uma opção: ");
            utils.setOpcaoSelecionada(entradaJogador.nextInt());

            utils.opcoesMenu(utils.getOpcaoSelecionada());
        } while (utils.getOpcaoSelecionada() != utils.getEncerraGame());

        entradaJogador.close();
    }
}
