package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.oopg.nukeit.AbstractClasses.Bullet;

import java.util.List;

public class AlienBullet extends Bullet {

    private final NukeITWorld world;

    public AlienBullet(NukeITWorld world, int startX, int startY, int speed) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/enemyBullet.png"), world);
        this.world = world;

        this.setWidth(10);
        this.setHeight(40);

        setX(startX);
        setY(startY);
        setySpeed(speed);
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
