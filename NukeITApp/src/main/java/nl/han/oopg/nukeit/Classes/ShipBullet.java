package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.oopg.nukeit.AbstractClasses.Bullet;
import nl.han.oopg.nukeit.AbstractClasses.Enemy;

import java.util.List;


public class ShipBullet extends Bullet {

    private final NukeITWorld world;
    private final int width;
    private final int height;

    public ShipBullet(NukeITWorld world, int startX, int startY, int speed) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/laserBullet.png"), world);
        this.world = world;
        width = 40;
        height = 70;
        this.setWidth(width);
        this.setHeight(height);
        setX(startX);
        setY(startY);
        setySpeed(speed);
    }

    public ShipBullet(NukeITWorld world, int startX, int startY, int speed, int angle) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/laserBullet.png"), world);
        this.world = world;
        width = 40;
        height = 70;
        this.setWidth(width);
        this.setHeight(height);
        setX(startX);
        setY(startY);
        setySpeed(speed);
        setDirection(angle);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedWith) {
        for (GameObject obj : collidedWith) {
            if (obj instanceof Enemy) {
                world.deleteGameObject(this);
            }
        }
    }
}
