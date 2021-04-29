package nl.han.oopg.nukeit.Classes;


import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.oopg.nukeit.Enums.FireMode;

import java.util.List;

public class RapidFirePowerUp extends SpriteObject implements ICollidableWithGameObjects {

    private NukeITWorld world;

    public RapidFirePowerUp(NukeITWorld world, int size) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/rapid fire.png"));
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
                world.ship.changeWeapon(FireMode.RAPID);
                world.deleteGameObject(this);
            }
        }
    }
}
