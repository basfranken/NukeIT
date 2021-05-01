package nl.han.oopg.nukeit.AbstractClasses;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.oopg.nukeit.Classes.NukeITWorld;


public abstract class Bullet extends SpriteObject implements ICollidableWithGameObjects {

    private final NukeITWorld world;

    public Bullet(Sprite sprite, NukeITWorld world) {
        super(sprite);
        this.world = world;
    }

    @Override
    public void update() {
        if (getY() <= 0 || getY() >= world.getHeight() || getX() <= 0 || getX() >= world.getWidth()) {
            world.deleteGameObject(this);
        }
    }

}
