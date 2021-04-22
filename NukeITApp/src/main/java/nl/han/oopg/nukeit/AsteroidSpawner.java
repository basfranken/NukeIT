package nl.han.oopg.nukeit;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.sound.Sound;

import java.util.Random;

public class AsteroidSpawner implements IAlarmListener {

    private float asteroidsPerSecond;
    private Random random;
    private NukeITWorld world;


    public AsteroidSpawner(NukeITWorld world, float asteroidsPerSecond) {
        this.asteroidsPerSecond = asteroidsPerSecond;
        this.world = world;
        random = new Random();
        startAlarm();
    }

    private void startAlarm() {
        Alarm alarm = new Alarm("New asteroid", 1 / asteroidsPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }


    @Override
    public void triggerAlarm(String alarmName) {

        int asteroidX = random.nextInt(world.width);
        int asteroidY = 0;
        int maxSpeed = 4;
        int minSpeed = 2;
        int speed     = random.nextInt(maxSpeed-minSpeed) + minSpeed;

        Asteroid asteroid = new Asteroid(world, asteroidX, asteroidY, speed, 100);
        world.addGameObject(asteroid);

        startAlarm();
    }
}
