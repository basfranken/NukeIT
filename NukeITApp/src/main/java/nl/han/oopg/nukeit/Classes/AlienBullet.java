package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.oopg.nukeit.Interfaces.Bullet;

import java.util.List;

public class AlienBullet extends SpriteObject implements ICollidableWithGameObjects {

    private final NukeITWorld world;
    final int width     = 10;
    final int height    = 40;

    public AlienBullet(NukeITWorld world, int startX, int startY, int speed) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/enemyBullet.png"));
        this.world = world;
        this.setWidth(width);
        this.setHeight(height);
        setX(startX);
        setY(startY);
        setySpeed(speed);
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
            if (obj instanceof Ship) {
                world.deleteGameObject(this);
            }
        }
    }
}
