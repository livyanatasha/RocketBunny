import bagel.Image;

public class Actor {
    private int x;
    private int y;
    private Image image;
    public final String TYPE;

    /* constructor to initialize */
    public Actor(int x, int y, String fileName, String type) {
        this.x = x;
        this.y = y;
        image = new Image(fileName);
        this.TYPE = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void shift() {
        x -= RocketBunny.PER_PIXEL;
    }

    public void render() {
        image.draw(x, y);
    }
}
