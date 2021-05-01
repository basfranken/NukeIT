package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.oopg.nukeit.Interfaces.Spawner;
import processing.core.PGraphics;

import java.util.Random;

public class AlienSpawner extends Spawner {

    private NukeITWorld world;
    private Random random;

    public AlienSpawner(NukeITWorld world, float spawnsPerSecond) {
        super(new Alarm("New Alien", 1 / spawnsPerSecond));
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
        getAlarm().addTarget(this);
        getAlarm().start();
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

        int alienMinLives  = 8;
        int alienMaxLives  = 20;

        int alienLives     = world.getRandomInRange(alienMinLives, alienMaxLives);

        Alien alien = new Alien(world, AlienX, ALienY, alienWidth, alienHeight, alienSpeed, alienLives, 2f);
        world.addGameObject(alien);

        startAlarm();
    }

    @Override
    public void stopSpawning() {
        getAlarm().stop();
    }
}
