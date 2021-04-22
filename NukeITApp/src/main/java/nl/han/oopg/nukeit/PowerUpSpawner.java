package nl.han.oopg.nukeit;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

import java.util.Random;

import static nl.han.oopg.nukeit.FireMode.TRIPLE;

public class PowerUpSpawner extends GameObject implements IAlarmListener {

    private NukeITWorld world;
    private Random random;
    private int powerUpsPerMinute;

    public PowerUpSpawner(NukeITWorld world, int powerUpsPerMinute) {
        this.powerUpsPerMinute = powerUpsPerMinute;
        this.world = world;
        random = new Random();
        startAlarm();
    }


    private void startAlarm() {
        Alarm alarm = new Alarm("New PowerUp", 60 / powerUpsPerMinute);
        alarm.addTarget(this);
        alarm.start();
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

        FireMode randomPowerUp = randomFireMode();


        if (randomPowerUp == FireMode.RAPID) {
            world.addGameObject(new RapidFirePowerUp(world, powerUpSize), powerUpX, powerUpY);
        }
        if (randomPowerUp == FireMode.TRIPLE) {
            world.addGameObject(new TripleShotPowerUp(world, powerUpSize), powerUpX, powerUpY);
        }
        if (randomPowerUp == FireMode.NORMAL) {
            world.addGameObject(new NormalFirePowerUp(world, powerUpSize), powerUpX, powerUpY);
        }

        startAlarm();
    }

    private FireMode randomFireMode() {
        int pick = new Random().nextInt(FireMode.values().length);
        return FireMode.values()[pick];
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics pGraphics) {

    }
}
