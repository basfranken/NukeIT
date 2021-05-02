package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.oopg.nukeit.AbstractClasses.Enemy;
import nl.han.oopg.nukeit.Enums.GameState;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Alien extends Enemy {

    private final NukeITWorld world;
    private int lives;
    private final float fireRate;
    private Timer timer;
    private TimerTask task;

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

    }

    private void startShooting() {
        setTask();
        timer = new Timer();
        timer.schedule(task, 0, (long) fireRate);


    }

    private void setTask() {
        task = new TimerTask() {

            @Override
            public void run() {
                if (world.gameState == GameState.GAME) {
                    world.addGameObject(new AlienBullet(world, (int) (getX() + getWidth() / 2), (int) getY(), 8));
                    world.getAlienShoot().cue(0);
                    world.getAlienShoot().play();
                }

            }
        };


    }



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
