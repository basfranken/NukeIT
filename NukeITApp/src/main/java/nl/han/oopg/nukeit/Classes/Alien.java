package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.oopg.nukeit.AbstractClasses.Enemy;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Alien extends Enemy {

    private final NukeITWorld world;
    private int lives;
    private final float fireRate;
    private Timer timer;
    private TimerTask task;

    /***
     * Constructor
     * @param world  The GameEngine
     * @param startX starting X position of Alien.
     * @param startY starting Y position of Alien.
     * @param width
     * @param height
     * @param speed Alien's speed of travel
     * @param lives Amount of lives.
     * @param fireRate amount of milliseconds between fired shots.
     */
    public Alien(NukeITWorld world, int startX, int startY, int width, int height, int speed, int lives, long fireRate) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/Alien.png"));
        this.world = world;
        this.setHeight(height);
        this.setWidth(width);
        this.lives = lives;
        this.fireRate = fireRate;
        setX(startX);
        setY(startY);
        setySpeed(speed);
        setDirection(90);
        startShooting();
        getAliens().add(this);
    }

    /***
     * sets the task wich will be executed by the timer.
     */
    public void setTask() {
        task = new TimerTask() {

            @Override
            public void run() {
                world.addGameObject(new AlienBullet(world, (int) (getX() + getWidth() / 2), (int) getY(), 8));
                world.getAlienShoot().cue(0);
                world.getAlienShoot().play();
            }
        };
    }

    /***
     * Makes Alien start shooting via a timer.
     */
    private void startShooting() {
        setTask();
        timer = new Timer();
        timer.schedule(task, 0, (long) fireRate);
    }

    void stopShooting() {
        timer.cancel();
    }

    /***
     * constantly checks if Alien has reached the end of the screen.
     * If it has it's direction is reversed.
     * also checks if lives have run out, if so it deletes Alien and stops the timer for shooting.
     */
    @Override
    public void update() {
        if (getX() >= world.getWidth() - getWidth()) {
            setDirection(270);
        }

        if (getX() <= 0 ) {
            setDirection(90);
        }

        if (lives <= 0) {
            timer.cancel();
            world.deleteGameObject(this);
        }
    }

    /***
     * If ALien collides with a shipBullet, subtracts a life and adds score to world.
     * @param collidedWith
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedWith) {
        for (GameObject obj : collidedWith) {
            if (obj instanceof ShipBullet) {
                world.updateScore(100);
                lives --;
                break;
            }
        }
    }

}
