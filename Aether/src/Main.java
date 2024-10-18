// Teste de commit

public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();

        utils.banner();

        do {
            utils.mostraMenu();
        } while (utils.getEncerraGame() != 5);
    }
}