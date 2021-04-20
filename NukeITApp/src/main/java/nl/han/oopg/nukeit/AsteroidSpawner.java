package nl.han.oopg.nukeit;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.sound.Sound;

import java.util.Random;


public class AsteroidSpawner extends Spawner {

    public AsteroidSpawner(NukeITWorld world, Sound spawnSound, float spawnsPerSecond) {
        super(world, spawnSound, spawnsPerSecond);
    }

    @Override
    public void startAlarm() {
        Alarm alarm = new Alarm("New asteroid", 1 / spawnsPerSecond);
        alarm.addTarget(this);
        alarm.start();

    }

    @Override
    public void triggerAlarm(String alarmName) {
        int asteroidSize = random.nextInt(30) + 30;


    }
}
