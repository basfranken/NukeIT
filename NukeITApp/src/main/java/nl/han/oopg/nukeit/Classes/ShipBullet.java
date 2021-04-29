package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;


public class ShipBullet extends SpriteObject implements ICollidableWithGameObjects {

    private final NukeITWorld world;
    final int width     = 40;
    final int height    = 70;

    public ShipBullet(NukeITWorld world, int startX, int startY, int speed) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/laserBullet.png"));
        this.world = world;
        this.setWidth(width);
        this.setHeight(height);
        setX(startX);
        setY(startY);
        setySpeed(speed);
    }

    public ShipBullet(NukeITWorld world, int startX, int startY, int speed, int angle) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/laserBullet.png"));
        this.world = world;
        this.setWidth(width);
        this.setHeight(height);
        setX(startX);
        setY(startY);
        setySpeed(speed);
        setDirection(angle);
    }


    @Override
    public void update() {
        if (getY() <= 0 || getY() >= world.getHeight() || getX() <= 0 || getX() >= world.getWidth()) {
            world.deleteGameObject(this);
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedWith) {
        for (GameObject obj : collidedWith) {
            if (obj instanceof Asteroid) {  //TODO if object implements enemy interface
                world.deleteGameObject(this);
            }
            if (obj instanceof Alien) {     //TODO if object implements enemy interface
                world.deleteGameObject(this);
            }
        }
    }
}