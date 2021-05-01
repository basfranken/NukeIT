package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.oopg.nukeit.Interfaces.Spawner;
import processing.core.PGraphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class AsteroidSpawner extends GameObject {

    private NukeITWorld world;
    private Random random;
    private TimerTask task;
    private Timer timer;

    public AsteroidSpawner(NukeITWorld world, float spawnsPerSecond) {
        //super(new Alarm("New Asteroid", 1 / spawnsPerSecond));
        this.world = world;
        random = new Random();
        timer = new Timer();
        setTask();
        timer.schedule(task, 0, 1000);
        //startAlarm();
    }

    /*
    public void startAlarm() {
        getAlarm().addTarget(this);
        getAlarm().start();
    }
     */

    private void setTask() {
        task = new TimerTask() {

            @Override
            public void run() {
                int asteroidSize         = 100;

                int asteroidMinX         = asteroidSize;
                int asteroidMaxX         = world.width - asteroidSize;
                int asteroidX            = random.nextInt(asteroidMaxX - asteroidMinX) + asteroidMinX;

                int asteroidY         = 0;

                int asteroidMaxSpeed  = 7;
                int asteroidMinSpeed  = 2;
                int asteroidspeed     = random.nextInt(asteroidMaxSpeed-asteroidMinSpeed) + asteroidMinSpeed;

                world.addGameObject(new Asteroid(world, asteroidX, asteroidY, asteroidspeed, asteroidSize));
            }
        };
    }

    /*
    @Override
    public void triggerAlarm(String s) {

        int asteroidSize         = 100;

        int asteroidMinX         = asteroidSize;
        int asteroidMaxX         = world.width - asteroidSize;
        int asteroidX            = random.nextInt(asteroidMaxX - asteroidMinX) + asteroidMinX;

        int asteroidY         = 0;

        int asteroidMaxSpeed  = 7;
        int asteroidMinSpeed  = 2;
        int asteroidspeed     = random.nextInt(asteroidMaxSpeed-asteroidMinSpeed) + asteroidMinSpeed;

        world.addGameObject(new Asteroid(world, asteroidX, asteroidY, asteroidspeed, asteroidSize));
        startAlarm();
    }
     */

    /*
    @Override
    public void stopSpawning() {
        getAlarm().stop();
    }
     */


    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics pGraphics) {

    }

}
