package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.oopg.nukeit.Enums.PowerUp;
import nl.han.oopg.nukeit.Interfaces.Spawner;
import processing.core.PGraphics;

import java.util.Random;

public class PowerUpSpawner extends GameObject implements Spawner {

    private NukeITWorld world;
    private Random random;
    private float spawnsPerSecond;
    private Alarm alarm;

    public PowerUpSpawner(NukeITWorld world, float spawnsPerSecond) {
        this.spawnsPerSecond = spawnsPerSecond;
        this.world = world;
        random = new Random();
        startAlarm();
    }

    @Override
    public void triggerAlarm(String s) {

        int powerUpSize         = 60;
        int powerUpinX          = powerUpSize;
        int powerUpMaxX         = world.width - powerUpSize;
        int powerUpX            = random.nextInt(powerUpMaxX - powerUpinX) + powerUpinX;

        int powerUpinY          = powerUpSize;
        int powerUpMaxY         = world.width - powerUpSize;
        int powerUpY            = random.nextInt(powerUpMaxY - powerUpinY) + powerUpinY;

        switch (pickRandomPowerUp()){
            case RAPID:
                world.addGameObject(new RapidFirePowerUp(world, powerUpSize), powerUpX, powerUpY);
                break;
            case NORMAL:
                world.addGameObject(new NormalFirePowerUp(world, powerUpSize), powerUpX, powerUpY);
                break;
            case TRIPLE:
                world.addGameObject(new TripleShotPowerUp(world, powerUpSize), powerUpX, powerUpY);
                break;
            case LIFE:
                world.addGameObject(new LifePowerUp(world, powerUpSize), powerUpX, powerUpY);
                break;
        }

        startAlarm();
    }

    @Override
    public void stopSpawning() {
        alarm.stop();
    }


    public void startAlarm() {
        alarm = new Alarm("New PowerUp", 1 / spawnsPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }


    private PowerUp pickRandomPowerUp() {
        int index = new Random().nextInt(PowerUp.values().length);
        return PowerUp.values()[index];
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics pGraphics) {

    }
}
