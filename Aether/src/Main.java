public class Main {
    public static void main(String[] args) throws InterruptedException {
        Utils utils = new Utils();

        do {
            utils.iniciaJogo();
        } while (!utils.getEncerraGame());
    }

}
