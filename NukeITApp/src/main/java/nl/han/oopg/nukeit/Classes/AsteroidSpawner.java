package nl.han.oopg.nukeit.Classes;

import nl.han.oopg.nukeit.Interfaces.Spawner;
import processing.core.PGraphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class AsteroidSpawner extends Spawner {

    private NukeITWorld world;
    private Random random;
    private TimerTask task;

    public AsteroidSpawner(NukeITWorld world, long milliSecondsPerSpawn) {
        super(new Timer());
        this.world = world;
        random = new Random();
        setTask();
        getTimer().schedule(task, 0, milliSecondsPerSpawn);
    }

    private void setTask() {
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
                int asteroidspeed     = world.getRandomInRange(asteroidMinSpeed, asteroidMaxSpeed);

                world.addGameObject(new Asteroid(world, asteroidX, asteroidY, asteroidspeed, asteroidSize));
            }
        };
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics pGraphics) {

    }

}
