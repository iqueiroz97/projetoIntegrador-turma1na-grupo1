public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();

        do {
            utils.iniciaJogo();
        } while (!utils.getEncerraGame());
    }
}