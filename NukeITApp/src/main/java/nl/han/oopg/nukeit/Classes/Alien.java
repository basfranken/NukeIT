package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;

public class Alien extends SpriteObject implements ICollidableWithGameObjects {

    private NukeITWorld world;
    private int lives;

    public Alien(NukeITWorld world, int startX, int startY, int width, int height, int speed, int lives) {

        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/Alien.png"));

        this.world = world;
        this.setHeight(height);
        this.setWidth(width);
        this.lives = lives;

        setX(startX);
        setY(startY);
        setySpeed(speed);
        setDirection(90);
    }

    @Override
    public void update() {
        if (getX() >= world.getWidth() - getWidth()) {
            setDirection(270);
        }

        if (getX() <= 0 ) {
            setDirection(90);
        }

        if (lives <= 0) {
            world.deleteGameObject(this);
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedWith) {
        for (GameObject obj : collidedWith) {
            if (obj instanceof ShipBullet) {
                world.updateScore(100);
                lives --;
                break;
            }
        }
    }
}
