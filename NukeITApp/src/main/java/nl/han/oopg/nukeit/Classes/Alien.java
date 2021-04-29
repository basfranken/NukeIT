package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;

public class Alien extends SpriteObject implements ICollidableWithGameObjects, IAlarmListener {

    private NukeITWorld world;
    private int lives;
    private float fireRate;
    private Alarm alarm;

    public Alien(NukeITWorld world, int startX, int startY, int width, int height, int speed, int lives, float fireRate) {

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
        startAlarm();
    }

    private void shoot() {
        world.addGameObject(new AlienBullet(world, (int) getX(), (int) getY(), 8));
    }

    public void startAlarm() {
        alarm = new Alarm("New AlienBullet", 1 / fireRate);
        alarm.addTarget(this);
        alarm.start();
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
            alarm.stop();
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

    @Override
    public void triggerAlarm(String s) {
        shoot();
        startAlarm();
    }
}
