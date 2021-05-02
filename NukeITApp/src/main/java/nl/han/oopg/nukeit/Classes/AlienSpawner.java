package nl.han.oopg.nukeit.Classes;

import nl.han.oopg.nukeit.AbstractClasses.Spawner;
import processing.core.PGraphics;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AlienSpawner extends Spawner {

    private NukeITWorld world;
    private Random random;
    private TimerTask task;

    /***
     * Constructor
     * @param world GameEngine.
     * @param milliSecondsPerSpawn Milliseconds between spawns.
     */
    public AlienSpawner(NukeITWorld world, long milliSecondsPerSpawn) {
        super(new Timer());
        this.world = world;
        random = new Random();
        setTask();
        getTimer().schedule(task, 0, milliSecondsPerSpawn);
    }

    @Override
    public void setTask() {
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

                Alien alien = new Alien(world, AlienX, ALienY, alienWidth, alienHeight, alienSpeed, alienLives, 1300);
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

}
