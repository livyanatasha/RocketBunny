import bagel.*;
import java.util.Random;

public class AsteroidLarge extends Actor{
    public static final String TYPE = "AsteroidLarge";

    public AsteroidLarge(int x, int y, String fileName) {
        super(x, y, fileName, TYPE);
    }

    public static String typeFileLarge() {
        Random rand = new Random();
        int numAsteroid = rand.nextInt(3);
        String fileName;
        switch (numAsteroid) {
            case (0):
                fileName = "res/image/asteroid_large1";
            case (1):
                fileName = "res/image/asteroid_large2";
            default:
                fileName = "res/image/asteroid_large3";
        }
        return fileName;
    }
}
