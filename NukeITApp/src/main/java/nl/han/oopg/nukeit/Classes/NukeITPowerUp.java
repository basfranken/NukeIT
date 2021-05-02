package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.oopg.nukeit.AbstractClasses.Enemy;
import nl.han.oopg.nukeit.AbstractClasses.PowerUp;

import java.util.List;

public class NukeITPowerUp extends PowerUp {

    private final NukeITWorld world;

    public NukeITPowerUp(NukeITWorld world, int size) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/PowerUps/NukeIT.png"), null, world);
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

                for(Alien i : Enemy.aliens) {
                    i.stopShooting();
                }

                world.deleteAllGameObjectsOfType(Asteroid.class);
                world.deleteAllGameObjectsOfType(Alien.class);
                world.deleteGameObject(this);
                world.updateScore(2000);
                //todo image of nuke overlay screen
            }
        }
    }

}