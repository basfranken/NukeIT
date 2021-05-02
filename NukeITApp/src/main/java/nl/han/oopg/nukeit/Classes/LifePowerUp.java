package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.oopg.nukeit.AbstractClasses.PowerUp;

import java.util.List;

public class LifePowerUp extends PowerUp {

    private final NukeITWorld world;

    public LifePowerUp(NukeITWorld world, int size) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/PowerUps/add life.png"), null, world);
        this.world = world;
        setWidth(size);
        setHeight(size);
    }

    @Override
    public void update() {

    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedWith) {
        for (GameObject obj : collidedWith) {
            if (obj instanceof Ship) {

                world.addLife();
                world.deleteGameObject(this);
            }
        }
    }
}