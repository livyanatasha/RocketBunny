import bagel.*;
import org.lwjgl.system.CallbackI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class RocketBunny extends AbstractGame {
    public static final int PER_PIXEL = 64;
    private final List<Actor> actorList = new ArrayList<>();
    private final Ship ship = new Ship();
    private final int tickRate = 500;
    private long lastUpdateMills = 0;
    private Boolean wasHit = false;
    private int tick = -1;
    private final Font word = new Font("res/8-BIT-WONDER.TTF", 50);

    public RocketBunny() {
        super(1024, 768, "Rocket Bunny");
        loadMap();
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
                String file;
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

    @Override
    protected void update(Input input) {
        Boolean status = false;

        /* check input and move accordingly */
        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        } else if (input.wasPressed(Keys.UP)) {
            ship.move(0, -PER_PIXEL);
        } else if (input.wasPressed(Keys.DOWN)) {
            ship.move(0, PER_PIXEL);
        }

        /* shift background and asteroid every 500 ms */
        long thisUpdateMills = currentTimeMillis();
        if (thisUpdateMills - lastUpdateMills > tickRate) {
            /* move actors */
            if (actorList.get(0).getX() >= -1728) {
                for (Actor actor : actorList) {
                    actor.shift();
                }
            }

            /* update the tick accordingly */
            if (tick < 13) {
                ship.shift("back");
                tick++;
            } else if (tick < 15) {
                ship.shift("front");
                tick++;
            } else if (tick > 19) {
                Window.close();
            }

            if (ship.getCondition() == 0 || (actorList.get(0).getX() <= -1728)) {
                tick++;
            }

            /* check if hit asteroid */
            if (ship.getCondition() != 0) {
                for (Actor actor : actorList) {
                    if (actor instanceof AsteroidLarge) {
                        status = ship.checkSurrounding(ship, actor, 64);
                    } else if (actor instanceof AsteroidSmall) {
                        status = ship.checkSurrounding(ship, actor, 32);
                    } else {
                        status = false;
                    }

                    if (status) {
                        break;
                    }
                }


                /* update the status */
                if (status) {
                    if (wasHit == false) {
                        ship.crash();
                        wasHit = true;
                    }
                } else {
                    if (wasHit) {
                        wasHit = false;
                    }
                }
            }
            lastUpdateMills = thisUpdateMills;
        }

        /* render images */
        for (Actor actor : actorList) {
            actor.render();
        }
        ship.render();

        /* add text */
        if (tick < 5) {
            word.drawString("Avoid the asteroid", 100, 348);
        } else if (actorList.get(0).getX() <= -1728){
            word.drawString("Made it", 400, 348);
        } else if (ship.getCondition() == 0) {
            word.drawString("NoBunny Left", 200, 348);
        }
    }
}
