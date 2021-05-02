package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.oopg.nukeit.Enums.FireMode;

import java.util.List;

public class Ship extends SpriteObject implements ICollidableWithGameObjects {

    private final NukeITWorld world;
    FireMode fireMode = FireMode.NORMAL;

    public Ship(NukeITWorld world) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/ship purple.png"));
        this.world = world;
        this.setWidth(100);
        this.setHeight(125);
        setFriction(0.025f);
    }

    private void shoot() {
        switch (fireMode) {
            case NORMAL:
                world.addGameObject(new ShipBullet(world, (int) (getX() + getWidth() / 3), (int) (getY() - getHeight() / 2), -10));
                break;
            case RAPID:
                for (int i = 0; i < 3; ++i) {
                    world.addGameObject(new ShipBullet(world, (int) (getX() + getWidth() / 3), (int) (getY() - getHeight() / 2), -10 * (i + 1) / 2));
                }
                break;
            case TRIPLE:
                world.addGameObject(new ShipBullet(world, (int) (getX() + getWidth() / 3), (int) (getY() - getHeight() / 2), -10, 45));
                world.addGameObject(new ShipBullet(world, (int) (getX() + getWidth() / 3), (int) (getY() - getHeight() / 2), -10, 0));
                world.addGameObject(new ShipBullet(world, (int) (getX() + getWidth() / 3), (int) (getY() - getHeight() / 2), -10, 315));
                break;
        }
        world.getShipShoot().cue(0);
        world.getShipShoot().play();
    }

    public void changeWeapon (FireMode firemode) {
        this.fireMode = firemode;
    }

    @Override
    public void update() {
        if (getX() <= 0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY() <= 0) {
            setySpeed(0);
            setY(0);
        }
        if (getX() >= world.getWidth() - width) {
            setxSpeed(0);
            setX(world.getWidth() - width);
        }
        if (getY() >= world.getHeight() - height) {
            setySpeed(0);
            setY(world.getHeight() - height);
        }
    }

    @Override
    public void keyPressed(int keyCode, char key) {

        final int speed = 10;

        if (keyCode == world.LEFT) {
            setDirectionSpeed(270, speed);

        }
        if (keyCode == world.UP) {
            setDirectionSpeed(0, speed);
        }
        if (keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
        }
        if (keyCode == world.DOWN) {
            setDirectionSpeed(180, speed);
        }
        if (keyCode == ' ') {
            shoot();
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedWith) {
        for (GameObject obj : collidedWith) {
            if (obj instanceof AlienBullet) {
                world.subtractLife();
                break;
            }
            if (obj instanceof Asteroid) {
                world.subtractLife();
                break;
            }
        }
    }
}
