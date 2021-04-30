
package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.oopg.nukeit.Interfaces.Spawner;
import processing.core.PGraphics;

import java.util.Random;

public class AsteroidSpawner extends GameObject implements Spawner, IAlarmListener {

    private NukeITWorld world;
    private Random random;
    private float spawnsPerSecond;
    private Alarm alarm;

    public AsteroidSpawner(NukeITWorld world, float spawnsPerSecond) {
        this.spawnsPerSecond = spawnsPerSecond;
        this.world = world;
        random = new Random();
        startAlarm();
    }

    public void startAlarm() {
        alarm = new Alarm("New asteroid", 1 / spawnsPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }


    @Override
    public void triggerAlarm(String alarmName) {

        int asteroidSize         = 100;

        int asteroidMinX         = asteroidSize;
        int asteroidMaxX         = world.width - asteroidSize;
        int asteroidX            = random.nextInt(asteroidMaxX - asteroidMinX) + asteroidMinX;

        int asteroidY         = 0;

        int asteroidMaxSpeed  = 7;
        int asteroidMinSpeed  = 2;
        int asteroidspeed     = random.nextInt(asteroidMaxSpeed-asteroidMinSpeed) + asteroidMinSpeed;

        Asteroid asteroid = new Asteroid(world, asteroidX, asteroidY, asteroidspeed, asteroidSize);
        world.addGameObject(asteroid);

        startAlarm();
    }

    @Override
    public void stopSpawning() {
        alarm.stop();
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics pGraphics) {

    }
}
