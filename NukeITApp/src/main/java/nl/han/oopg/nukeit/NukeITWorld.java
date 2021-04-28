package nl.han.oopg.nukeit;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.TextObject;
import processing.core.PImage;
import nl.han.ica.oopg.view.View;

public class NukeITWorld extends GameEngine {

    public Ship ship;
    private TextObject      livesText;
    private TextObject      scoreText;
    private int             score;
    private int             lives = 3;


    public static void main(String[] args) {
        NukeITWorld app = new NukeITWorld();
        app.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWith = 1280;
        int worldHeight = 900;

        createView(worldWith, worldHeight);

        createObjects();

        createDashboards();

        createAsteroidSpawner();
    }

    private void createView(int worldWith, int worldHeight){
        View view = new View(worldWith, worldHeight);
        PImage backgroundImg = loadImage("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/background.jpg");
        view.setBackground(backgroundImg);
        setView(view);
        size(worldWith, worldHeight);
        createPowerUpSpawner();
    }

    private void createObjects() {
        ship = new Ship(this);
        addGameObject(ship, 500, 899);
    }

    private void createDashboards() {

        int dashboardWidth  = 500;
        int dashboardHeight = 200;

        Dashboard scoreDashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        Dashboard livesDashboard = new Dashboard(0, 50, dashboardWidth, dashboardHeight);

        scoreText = new TextObject("SCORE : " + score , 45);
        livesText = new TextObject("LIVES : " + lives, 45);

        scoreText.setForeColor(255, 0, 0, 255);
        livesText.setForeColor(255, 0, 0, 255);

        scoreDashboard.addGameObject(scoreText);
        livesDashboard.addGameObject(livesText);

        addDashboard(scoreDashboard);
        addDashboard(livesDashboard);
    }

    public void createAsteroidSpawner() {
        AsteroidSpawner asteroidSpawner = new AsteroidSpawner(this, 2);
        addGameObject(asteroidSpawner);
    }

    public void createPowerUpSpawner() {
        PowerUpSpawner powerUpSpawner = new PowerUpSpawner(this, 10);
        addGameObject(powerUpSpawner);
    }

    public void subtractLife() {
        lives --;
        livesText.setText("LIVES: " + lives);
    }

    public void addLife() {
        lives ++;
        livesText.setText("LIVES: " + lives);
    }


    public void updateScore(int scoreToAdd) {
        score = score + scoreToAdd;
        scoreText.setText("SCORE : " + score);
    }



    @Override
    public void update() {
    }


}

