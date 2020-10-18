import bagel.Image;

public class Ship {
    private int x;
    private int y;
    private static final Image THREE_LIFE_FILE = new Image("res/images/3Life.png");
    private static final Image TWO_LIFE_FILE = new Image("res/images/2Life.png");
    private static final Image ONE_LIFE_FILE = new Image("res/images/1Life.png");
    private static final int THREE_LIFE= 3;
    private static final int TWO_LIFE = 2;
    private static final int ONE_LIFE = 1;
    private int condition;
    private Image image;

    /* constructor to initialize value */
    public Ship(int x, int y) {
        this.x = x;
        this.y = y;
        image = THREE_LIFE_FILE;
        condition = THREE_LIFE;
    }

    /* when crash bunny ejected one out */
    public void crash() {
        condition = condition-1;
    }
}
