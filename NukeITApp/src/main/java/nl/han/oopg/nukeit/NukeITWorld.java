package nl.han.oopg.nukeit;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;


public class NukeITWorld extends GameEngine {
    public static void main(String[] args) {
        NukeITWorld app = new NukeITWorld();
        app.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWith = 500;
        int worldHeight = 500;

        TextObject to;

        to = new TextObject("Hello World!", 40);
        to.setForeColor(255, 0, 0, 255);
        addGameObject(to, 100, 100);


        View view = new View(worldWith, worldHeight);
        setView(view);
        size(worldWith, worldHeight);

    }

    @Override
    public void update() {

    }

}

