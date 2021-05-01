package nl.han.oopg.nukeit.AbstractClasses;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public abstract class Enemy extends SpriteObject implements ICollidableWithGameObjects {

    public Enemy(Sprite sprite) {
        super(sprite);
    }

}
