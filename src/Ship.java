import bagel.Image;

public class Ship {
    private static final String THREE_LIFE_FILE = "res/images/3Life.png";
    private static final String TWO_LIFE_FILE = "res/images/2Life.png";
    private static final String ONE_LIFE_FILE = "res/images/1Life.png";
    private static final int THREE_LIFE= 3;
    private static final int TWO_LIFE = 2;
    private static final int ONE_LIFE = 1;
    private int condition;
    private Image image;
    private int x;
    private int y;

    /* constructor to initialize value */
    public Ship(int x, int y) {
        this.x = x;
        this.y = y;
        image = new Image(THREE_LIFE_FILE);
        condition = THREE_LIFE;
    }

    /* when crash bunny ejected one out */
    public void crash() {
        condition = condition-1;
    }
}
