public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();

        utils.banner();
        System.out.println("hellow");    

        do {
            utils.mostraMenu();
        } while (utils.getEncerraGame() != 5);
    }
}