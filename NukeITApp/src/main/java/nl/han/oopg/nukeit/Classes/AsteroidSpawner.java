package nl.han.oopg.nukeit.Classes;

import nl.han.oopg.nukeit.Interfaces.Spawner;
import java.util.Timer;
import java.util.TimerTask;


public class AsteroidSpawner extends Spawner {

    private NukeITWorld world;
    private TimerTask task;

    public AsteroidSpawner(NukeITWorld world, long milliSecondsPerSpawn) {
        super(new Timer());
        this.world = world;
        setTask();
        getTimer().schedule(task, 0, milliSecondsPerSpawn);
    }

    @Override
    public void setTask() {
        task = new TimerTask() {

            @Override
            public void run() {
                int asteroidSize         = 100;

                int asteroidMinX         = asteroidSize;
                int asteroidMaxX         = world.width - asteroidSize;
                int asteroidX            = world.getRandomInRange(asteroidMinX, asteroidMaxX);

                int asteroidY         = 0;

                int asteroidMaxSpeed  = 7;
                int asteroidMinSpeed  = 2;
                int asteroidSpeed     = world.getRandomInRange(asteroidMinSpeed, asteroidMaxSpeed);

                world.addGameObject(new Asteroid(world, asteroidX, asteroidY, asteroidSpeed, asteroidSize));
            }
        };
    }
}
