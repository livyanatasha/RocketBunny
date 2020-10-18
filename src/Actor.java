import bagel.Image;

public class Actor {
    private int x;
    private int y;
    private Image image;
    public final String type;

    /* constructor to initialize */
    public Actor(int x, int y, String fileName, String type) {
        this.x = x;
        this.y = y;
        image = new Image(fileName);
        this.type = type;
    }

    /* setter to change image */
    public void setImage(String fileName) {
        this.image = new Image(fileName);
    }

    public void shift() {
        x = x - RocketBunny.SPEED_PER_PIXEL;
    }
}
