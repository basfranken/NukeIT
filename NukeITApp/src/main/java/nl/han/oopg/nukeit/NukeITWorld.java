package nl.han.oopg.nukeit;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;
import processing.core.PImage;

import javax.imageio.ImageIO;
import java.awt.*;


public class NukeITWorld extends GameEngine {


    private Sound asteroidSpawnSound;
    private AsteroidSpawner asteroidSpawner;



    public static void main(String[] args) {
        NukeITWorld app = new NukeITWorld();
        app.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWith = 1200;
        int worldHeight = 800;

        createView(worldWith, worldHeight);

    }

    private void createView(int worldWith, int worldHeight){
        View view = new View(worldWith, worldHeight);
        PImage backgroundImg = loadImage("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/background.jpg");
        backgroundImg.resize(worldWith, worldHeight);
        view.setBackground(backgroundImg);
        setView(view);
        size(worldWith, worldHeight);
    }

    private void initializeSound() {
        asteroidSpawnSound = new Sound(this, "NukeITApp/src/main/java/nl/han/oopg/nukeit/data/astreroidSpawnSound.mp3");
    }



    private void createObjects() {

    }

    public void createAsteroidSpawner() {
        asteroidSpawner = new AsteroidSpawner(this, asteroidSpawnSound, 1);
    }


    @Override
    public void update() {

    }

}

