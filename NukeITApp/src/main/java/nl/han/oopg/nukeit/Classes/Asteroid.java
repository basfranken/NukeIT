package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.oopg.nukeit.AbstractClasses.Enemy;
import java.util.List;
import java.util.TimerTask;

public class Asteroid extends Enemy {

    private NukeITWorld world;

    public Asteroid(NukeITWorld world, int startX, int startY, int speed, int size) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/asteroid.png"));
        this.world = world;
        this.setHeight(size);
        this.setWidth(size);
        setX(startX);
        setY(startY);
        setySpeed(speed);
        //world.getAstroidSpawn().cue(0);
        //world.getAstroidSpawn().play();
    }



    @Override
    public void update() {
        if (getY() >= world.getHeight()) {
           world.deleteGameObject(this);
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedWith) {
        for (GameObject obj : collidedWith) {
            if (obj instanceof ShipBullet) {
                world.updateScore(50);
                world.deleteGameObject(this);
                break;
            }
            if (obj instanceof Ship) {
                world.deleteGameObject(this);
            }
        }
    }
}
