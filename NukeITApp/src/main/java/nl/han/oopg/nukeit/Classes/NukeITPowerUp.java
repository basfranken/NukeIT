package nl.han.oopg.nukeit.Classes;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.oopg.nukeit.AbstractClasses.Enemy;
import nl.han.oopg.nukeit.AbstractClasses.Spawner;

import java.util.List;

public class NukeITPowerUp extends SpriteObject implements ICollidableWithGameObjects {

    private final NukeITWorld world;

    public NukeITPowerUp(NukeITWorld world, int size) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/PowerUps/NukeIT.png"));
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
                world.deleteAllGameObjectsOfType(Asteroid.class);
                world.deleteAllGameObjectsOfType(Alien.class);
                world.deleteGameObject(this);
                world.updateScore(2000);
                //todo image of nuke overlay screen
            }
        }
    }

}