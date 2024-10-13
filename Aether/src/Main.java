public class Main {
    public static void main(String[] args) {

        String playerName = "Igor";

        Utils utils = new Utils();

        utils.Banner1();
        utils.TerrorNoEspacoMorse();
        System.out.println("\n");

        utils.Banner2();
        utils.TerrorNoEspacoMorse();
        System.out.println("\n");

        utils.Banner3();
        utils.TerrorNoEspacoMorse();
        System.out.println("\n");

        utils.Banner4();
        utils.TerrorNoEspacoMorse();
        System.out.println("\n");

        utils.ShowMenu();

        System.out.println(playerName + " joga o dado. O resultado é: " + utils.ThrowsDice());
    }
}