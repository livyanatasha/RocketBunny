import bagel.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class RocketBunny extends AbstractGame {
    public static final int PER_PIXEL = 64;
    private final List<Actor> actorList = new ArrayList<>();
    private Ship ship = new Ship();
    private int tickRate = 500;
    private int tick = 0;
    private Image background = new Image("res/image/galaxyPlanet.png");

    public RocketBunny() {
        super(1024, 768, "Rocket Bunny");
        loadMap();
        intro();
    }

    public static void main(String[] args) {
        new RocketBunny().run();
    }

    public void loadMap() {
        String mapFile = "res/map/spacebunnies.csv";

        /* try through the csv file */
        try (BufferedReader worldReader =
                     new BufferedReader(new FileReader(mapFile))) {
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
                        break;
                    case AsteroidSmall.TYPE:
                        file = AsteroidSmall.typeFileSmall();
                        actorInput = new AsteroidLarge(x,y, file);
                        break;
                    case Background.TYPE:
                        actorInput = new Background();
                        break;
                    case StartPlanet.TYPE:
                        actorInput = new StartPlanet();
                        break;
                    case EndPlanet.TYPE:
                        actorInput = new EndPlanet();
                        break;
                }
                actorList.add(actorInput);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public void intro() {
        for (int i=0; i<4; i++) {
            long timeBegin = currentTimeMillis();
            while (tickRate != (currentTimeMillis() - timeBegin));
            for (Actor actor : actorList) {
                actor.shift();
            }
            ship.shift();

            /* render images */
            for (Actor actor : actorList) {
                actor.render();
            }
            ship.render();
        }
    }

    public void finale() {

    }

    @Override
    protected void update(Input input) {
        /* check input and move accordingly */
        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        } else if (input.wasPressed(Keys.UP)) {
            ship.move(0, -PER_PIXEL);
        } else if (input.wasPressed(Keys.DOWN)) {
            ship.move(0, PER_PIXEL);
        }

        long timeBegin = currentTimeMillis();
        while (tickRate != (currentTimeMillis() - timeBegin));
        
        /* move actors */
        for (Actor actor : actorList) {
            actor.shift();
        }

        /* render images */
        for (Actor actor : actorList) {
            actor.render();
        }
        ship.render();

        tick++;
    }
}
