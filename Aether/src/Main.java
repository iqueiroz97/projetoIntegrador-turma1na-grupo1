public class Main {
    public static void main(String[] args) {

        String playerName = "Igor";

        Utils dice = new Utils();

        System.out.println(playerName + " joga o dado. O resultado é: " + dice.ThrowsDice());
    }
}