package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.oopg.nukeit.Interfaces.Spawner;
import processing.core.PGraphics;

import java.util.Random;

public class AsteroidSpawner extends Spawner {

    private NukeITWorld world;
    private Random random;

    public AsteroidSpawner(NukeITWorld world, float spawnsPerSecond) {
        super(new Alarm("New Asteroid", 1 / spawnsPerSecond));
        this.world = world;
        random = new Random();
        startAlarm();
    }

    public void startAlarm() {
        getAlarm().addTarget(this);
        //getAlarm().start();
    }


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

    @Override
    public void stopSpawning() {
        getAlarm().stop();
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics pGraphics) {

    }

}
