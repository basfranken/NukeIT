package nl.han.oopg.nukeit.AbstractClasses;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.oopg.nukeit.Classes.NukeITWorld;
import nl.han.oopg.nukeit.Classes.Ship;
import nl.han.oopg.nukeit.Enums.FireMode;

import java.util.List;

public abstract class PowerUp extends SpriteObject implements ICollidableWithGameObjects {

    private final FireMode fireMode;
    private final NukeITWorld world;

    public PowerUp(Sprite sprite, FireMode fireMode, NukeITWorld world) {
        super(sprite);
        this.fireMode = fireMode;
        this.world = world;
    }

    public FireMode getFireMode() {
        return fireMode;
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedWith) {
        for (GameObject obj : collidedWith) {
            if (obj instanceof Ship) {
                world.ship.changeWeapon(getFireMode());
                world.deleteGameObject(this);
            }
        }
    }


    @Override
    public void update() {

    }
}
