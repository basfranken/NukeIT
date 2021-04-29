package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.oopg.nukeit.Interfaces.Spawner;
import processing.core.PGraphics;

import java.util.Random;

public class AlienSpawner extends GameObject implements Spawner {

    private NukeITWorld world;
    private float spawnsPerSecond;
    private Random random;

    public AlienSpawner(NukeITWorld world, float spawnsPerSecond) {
        this.spawnsPerSecond = spawnsPerSecond;
        this.world = world;
        random = new Random();
        startAlarm();
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics pGraphics) {

    }

    @Override
    public void startAlarm() {
        Alarm alarm = new Alarm("New alien", 1 / spawnsPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }

    @Override
    public void triggerAlarm(String alarmName) {
        int alienWidth           = 200;
        int alienHeight          = 160;

        int AlienX               = random.nextInt(world.getWidth());
        int ALienY               = 20;

        int alienMinSpeed     = 2;
        int alienMaxSpeed     = 4;
        int alienSpeed        = world.getRandomInRange(alienMinSpeed, alienMaxSpeed);

        int alienMinLives  = 2;
        int alienMaxLives  = 10;

        int alienLives     = world.getRandomInRange(alienMinLives, alienMaxLives);

        Alien alien = new Alien(world, AlienX, ALienY, alienWidth, alienHeight, alienSpeed, alienLives);
        world.addGameObject(alien);

        startAlarm();
    }
}
