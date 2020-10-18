import bagel.Font;
import bagel.Image;

public class Ship {
    private static final String THREE_LIFE_FILE = "res/image/3spacebunnies.png";
    private static final String TWO_LIFE_FILE = "res/image/2spacebunnies.png";
    private static final String ONE_LIFE_FILE = "res/image/1spacebunnies.png";
    private static final String ZERO_LIFE_FILE = "res/image/0spacebunnies.png";
    private static final int THREE_LIFE= 3;
    private static final int TWO_LIFE = 2;
    private static final int ONE_LIFE = 1;
    private int condition;
    private Image image;
    private int x;
    private int y;

    /* constructor to initialize value */
    public Ship() {
        x = 0;
        y = 348;
        image = new Image(THREE_LIFE_FILE);
        condition = THREE_LIFE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Boolean checkSurrounding(Ship ship, Actor asteroid, int range) {
        if ((ship.getY() >= asteroid.getY()-range) && (ship.getY() <= asteroid.getY()+range)
        && (ship.getX() >= asteroid.getX()-range) && (ship.getX() <= asteroid.getX()+range)) {
            return true;
        } else {
            return false;
        }
    }

    /* when crash bunny ejected one out */
    public void crash() {
        condition = condition-1;
        if (condition == TWO_LIFE) {
            image = new Image(TWO_LIFE_FILE);
        } else if (condition == ONE_LIFE) {
            image = new Image(ONE_LIFE_FILE);
        } else {
            image = new Image(ZERO_LIFE_FILE);
        }
    }

    public int getCondition() {
        return condition;
    }

    public void shift() {
        x = x + RocketBunny.PER_PIXEL;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void render() {
        image.draw(x,y);
    }
}
