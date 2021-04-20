package nl.han.oopg.nukeit;

import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.sound.Sound;

import java.util.Random;

public abstract class Spawner extends NukeITWorld implements IAlarmListener {

    private         NukeITWorld world;
    private         Sound spawnSound;
    protected Random random;
    protected float spawnsPerSecond;

    public Spawner(NukeITWorld world, Sound spawnSound, float spawnsPerSecond) {
        this.spawnsPerSecond = spawnsPerSecond;
        this.world = world;
        this.spawnSound = spawnSound;
        this.random = new Random();
        startAlarm();
    }

    public abstract void startAlarm();

    public abstract void triggerAlarm(String alarmName);

}
