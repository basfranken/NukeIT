package nl.han.oopg.nukeit.AbstractClasses;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

import java.util.Timer;

/*** This is the main class inherited by all Spawners.
 * @author Bas Franken 616774, René Monté 665957
 * @version 1.0
 */
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
