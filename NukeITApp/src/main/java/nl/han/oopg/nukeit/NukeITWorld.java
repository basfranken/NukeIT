package nl.han.oopg.nukeit;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.TextObject;
import processing.core.PImage;
import nl.han.ica.oopg.view.View;

public class NukeITWorld extends GameEngine {

    private AsteroidSpawner asteroidSpawner;
    private TextObject scoreBoardText;
    private int             score;
    private int             lives = 3;


    public static void main(String[] args) {
        NukeITWorld app = new NukeITWorld();
        app.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWith = 1280;
        int worldHeight = 1024;
        createView(worldWith, worldHeight);
        createObjects();
        createDashboard(500, 200);
        createAsteroidSpawner();

    }

    private void createView(int worldWith, int worldHeight){
        View view = new View(worldWith, worldHeight);
        PImage backgroundImg = loadImage("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/background.jpg");
        view.setBackground(backgroundImg);
        setView(view);
        size(worldWith, worldHeight);
    }

    private void createObjects() {
        Ship ship = new Ship(this);
        addGameObject(ship, 500, 650);
    }

    private void createDashboard(int dashboardWidth, int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        scoreBoardText = new TextObject("SCORE : " + score + "\nLIVES: " + lives, 45);
        scoreBoardText.setForeColor(255, 0, 0, 255);
        dashboard.addGameObject(scoreBoardText);
        addDashboard(dashboard);
    }

    public void createAsteroidSpawner() {
        asteroidSpawner = new AsteroidSpawner(this, 1);
    }

    public void subtractLife() {
        lives --;
        scoreBoardText.setText("SCORE : " + score + "\n LIVES: " + lives);
    }

    public void updateScore(int scoreToAdd) {
        score = score + scoreToAdd;
        scoreBoardText.setText("SCORE : " + score + "\n LIVES: " + lives);
    }

    @Override
    public void update() {
    }


}

