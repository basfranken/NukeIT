package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.oopg.nukeit.Enums.PowerUp;
import nl.han.oopg.nukeit.Interfaces.Spawner;
import processing.core.PGraphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PowerUpSpawner extends GameObject {

    private NukeITWorld world;
    private Random random;
    private TimerTask task;
    private Timer timer;

    public PowerUpSpawner(NukeITWorld world, float spawnsPerSecond) {
        //super(new Alarm("New PowerUp", 1 / spawnsPerSecond));
        this.world = world;
        random = new Random();
        timer = new Timer();
        setTask();
        timer.schedule(task, 0, 1000);
        //startAlarm();
    }

    private void setTask() {
        task = new TimerTask() {

            @Override
            public void run() {
                int powerUpSize         = 60;
                int powerUpminX          = powerUpSize;
                int powerUpMaxX         = world.width - powerUpSize;
                int powerUpX            = world.getRandomInRange(powerUpminX, powerUpMaxX);

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
            }
        };
    }


    /*
    public void startAlarm() {
        getAlarm().addTarget(this);
        getAlarm().start();
    }

     */

    /*
    @Override
    public void triggerAlarm(String s) {
        int powerUpSize         = 60;
        int powerUpminX          = powerUpSize;
        int powerUpMaxX         = world.width - powerUpSize;
        int powerUpX            = world.getRandomInRange(powerUpminX, powerUpMaxX);

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
        //startAlarm();
        //getAlarm().addTarget(this);
    }

     */

    /*
    @Override
    public void stopSpawning() {
        getAlarm().stop();
    }

     */


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
