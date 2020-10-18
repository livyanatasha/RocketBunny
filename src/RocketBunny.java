import bagel.*;

public class RocketBunny extends AbstractGame {
    private final Image background = new Image("res/images/background.png");
    private final int SPEED_PER_PIXEL = 1;

    public RocketBunny() {
        loadMap();
        intro();
    }

    public static void main(String[] args) {
        new RocketBunny().run();
    }

    public void loadMap() {

    }

    public void intro() {

    }

    public void finale() {

    }

    @Override
    protected void update(Input input) {

        /* check input and move accordingly */
        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        } else if (input.wasPressed(Keys.UP)) {

        } else if (input.wasPressed(Keys.DOWN)) {

        }
    }
}
