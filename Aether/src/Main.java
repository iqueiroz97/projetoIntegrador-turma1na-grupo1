public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();

        utils.iniciaJogo();

        do {
            utils.mostraMenu();
        } while (utils.getEncerraGame() != 5);
    }
}