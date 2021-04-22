package nl.han.oopg.nukeit;

import nl.han.ica.oopg.engine.GameEngine;
import processing.core.PImage;
import nl.han.ica.oopg.view.View;

public class NukeITWorld extends GameEngine {
    private Ship ship;

    public static void main(String[] args) {
        NukeITWorld app = new NukeITWorld();
        app.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWith = 1200;
        int worldHeight = 800;
        createView(worldWith, worldHeight);
        createObjects();
    }

    private void createView(int worldWith, int worldHeight){
        View view = new View(worldWith, worldHeight);
        PImage backgroundImg = loadImage("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/background.jpg");
        backgroundImg.resize(worldWith, worldHeight);
        view.setBackground(backgroundImg);
        setView(view);
        size(worldWith, worldHeight);
    }

    private void createObjects() {
        ship = new Ship(this);
        addGameObject(ship, 500, 650);
    }

    @Override
    public void update() {
    }

}

