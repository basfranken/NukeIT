package nl.han.oopg.nukeit;


import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;

public class TripleShotPowerUp extends SpriteObject implements ICollidableWithGameObjects {

    private NukeITWorld world;



    public TripleShotPowerUp(NukeITWorld world, int size) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/triple shot.png"));
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
                world.ship.changeWeapon(FireMode.TRIPLE);
                world.deleteGameObject(this);
            }
        }
    }
}
