import java.util.Random;

public class Utils {

    public int ThrowsDice() {

        int selectecSide;

        Random throwsDice = new Random();
        selectecSide = throwsDice.nextInt(1, 7);

        return selectecSide;
    }
}
