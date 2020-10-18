import bagel.*;
import java.util.Random;

public class AsteroidLarge extends Actor{
    public static final String TYPE = "AsteroidLarge";

    public AsteroidLarge(int x, int y, String fileName) {
        super(x, y, fileName, TYPE);
    }

    public static String typeFileLarge() {
        Random rand = new Random();
        int numAsteroid = rand.nextInt(2);
        String fileName;
        switch (numAsteroid) {
            case (0):
                fileName = "res/image/asteroid_large1.png";
            default:
                fileName = "res/image/asteroid_large2.png";
        }
        return fileName;
    }
}
