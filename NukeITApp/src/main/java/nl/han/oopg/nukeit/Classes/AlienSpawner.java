package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.oopg.nukeit.Interfaces.Spawner;
import processing.core.PGraphics;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AlienSpawner extends GameObject {

    private NukeITWorld world;
    private Random random;
    private TimerTask task;
    private Timer timer;

    public AlienSpawner(NukeITWorld world, float spawnsPerSecond) {
        //super(new Alarm("New Alien", 1 / spawnsPerSecond));
        this.world = world;
        random = new Random();
        timer = new Timer();
        setTask();
        timer.schedule(task, 0, 1000);
    }

    private void setTask() {
        task = new TimerTask() {

            @Override
            public void run() {
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
            }
        };
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics pGraphics) {

    }



    /*
    @Override
    public void stopSpawning() {
        //getAlarm().stop();
        timer.cancel();
    }
     */
}
