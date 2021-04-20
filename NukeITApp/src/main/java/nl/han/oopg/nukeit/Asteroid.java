package nl.han.oopg.nukeit;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.sound.Sound;
import processing.core.PGraphics;
import processing.core.PImage;

import java.nio.file.Watchable;
import java.util.List;

public class Asteroid extends GameObject implements ICollidableWithGameObjects {

    private final Sound destroySound;
    private NukeITWorld world;
    private int size;
    private PImage asteroidImg;

    public Asteroid(Sound destroySound, NukeITWorld world, int size) {
        this.destroySound = destroySound;
        this.world = world;
        this.size = size;
        asteroidImg = world.loadImage("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/asteroid.jpg");
        asteroidImg.resize(size, size);
    }

    public Asteroid(int size, NukeITWorld world, Sound destroySound){
        this.size = size;
        this.destroySound = destroySound;
        this.world = world;
        setySpeed(-size / 10f);

        setHeight(size);
        setWidth(size);
    }


    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {

    }

    @Override
    public void update() {
        if (getY() <= world.height) {
            world.deleteGameObject(this);
        }
    } 

    @Override
    public void draw(PGraphics pGraphics) {

    }
}
