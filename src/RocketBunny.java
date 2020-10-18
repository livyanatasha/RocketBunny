import bagel.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class RocketBunny extends AbstractGame {
    public static final int SPEED_PER_PIXEL = 1;
    private final List<Actor> actorList = new ArrayList<>();

    public RocketBunny() {
        super(1024, 768, "Rockey Bunny");
        loadMap();
        intro();
    }

    public static void main(String[] args) {
        new RocketBunny().run();
    }

    public void loadMap() {
        String mapFile = "res/map/map.csv";

        /* try through the csv file */
        try (BufferedReader worldReader =
                     new BufferedReader(new FileReader("res/worlds/test.csv"))) {
            String row;

            /* going through every line in the csv file */
            while((row = worldReader.readLine()) != null) {
                String[] input = row.split(",");
                String name = input[0];
                int x = Integer.parseInt(input[1]);
                int y = Integer.parseInt(input[2]);

                /* insert the input to the list */
                Actor actorInput = null;
                String file = null;
                switch (name) {
                    case AsteroidLarge.TYPE:
                        file = AsteroidLarge.typeFileLarge();
                        actorInput = new AsteroidLarge(x,y, file);
                    case AsteroidSmall.TYPE:
                        file = AsteroidSmall.typeFileSmall();
                        actorInput = new AsteroidLarge(x,y, file);
                    case Background.TYPE:
                        actorInput = new Background();
                    case StartPlanet.TYPE:
                        actorInput = new StartPlanet();
                    case EndPlanet.TYPE:
                        actorInput = new EndPlanet();
                }
                actorList.add(actorInput);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

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

        /* render images */

    }
}
