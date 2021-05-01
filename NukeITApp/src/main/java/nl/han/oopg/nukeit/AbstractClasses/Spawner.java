package nl.han.oopg.nukeit.AbstractClasses;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

import java.util.Timer;

public abstract class Spawner extends GameObject {

    private final Timer timer;

    public Spawner(Timer timer){
        this.timer = timer;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics pGraphics) {

    }

    public abstract void setTask();

    public Timer getTimer() {
        return timer;
    }

    public void stopSpawning() {
        timer.cancel();
    }

}
