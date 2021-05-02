package nl.han.oopg.nukeit.Classes;

import nl.han.oopg.nukeit.Enums.PowerUp;
import nl.han.oopg.nukeit.AbstractClasses.Spawner;
import processing.core.PGraphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/*** This class represents the Spawner for power ups
 * @author Bas Franken 616774, René Monté 665957
 * @version 1.0
 */
public class PowerUpSpawner extends Spawner {

    private final NukeITWorld world;
    private final Random random;
    private TimerTask task;

    public PowerUpSpawner(NukeITWorld world, long milliSecondsPerSpawn) {
        super(new Timer());
        this.world = world;
        random = new Random();
        setTask();
        getTimer().schedule(task, 0, milliSecondsPerSpawn);
    }

    @Override
    public void setTask() {
        task = new TimerTask() {

            @Override
            public void run() {

                int powerUpSize         = nl.han.oopg.nukeit.AbstractClasses.PowerUp.size;

                int powerUpminX         = powerUpSize;
                int powerUpMaxX         = world.width - powerUpSize;
                int powerUpX            = world.getRandomInRange(powerUpminX, powerUpMaxX);

                int powerUpinY          = powerUpSize;
                int powerUpMaxY         = world.width - powerUpSize;
                int powerUpY            = random.nextInt(powerUpMaxY - powerUpinY) + powerUpinY;

                switch (pickRandomPowerUp()){
                    case RAPID:
                        world.addGameObject(new RapidFirePowerUp(world, powerUpSize), powerUpX, powerUpY);
                        break;
                    case NORMAL:
                        world.addGameObject(new NormalFirePowerUp(world, powerUpSize), powerUpX, powerUpY);
                        break;
                    case TRIPLE:
                        world.addGameObject(new TripleShotPowerUp(world, powerUpSize), powerUpX, powerUpY);
                        break;
                    case LIFE:
                        world.addGameObject(new LifePowerUp(world, powerUpSize), powerUpX, powerUpY);
                        break;
                    case NUKEIT:
                        world.addGameObject(new NukeITPowerUp(world, powerUpSize), powerUpX, powerUpY);
                }
            }
        };
    }

    private PowerUp pickRandomPowerUp() {
        int index = new Random().nextInt(PowerUp.values().length);
        return PowerUp.values()[index];
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics pGraphics) {

    }
}
