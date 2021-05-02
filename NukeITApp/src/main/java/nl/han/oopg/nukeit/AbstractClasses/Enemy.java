package nl.han.oopg.nukeit.AbstractClasses;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.oopg.nukeit.Classes.Alien;

import java.util.ArrayList;

/*** This is the main class inherited by all Enemy objects.
 * @author Bas Franken 616774, René Monté 665957
 * @version 1.0
 */
public abstract class Enemy extends SpriteObject implements ICollidableWithGameObjects {

    public static ArrayList<Alien> aliens = new ArrayList<>();

    public Enemy(Sprite sprite) {
        super(sprite);
    }

    public ArrayList<Alien> getAliens() {
        return aliens;
    }
}
