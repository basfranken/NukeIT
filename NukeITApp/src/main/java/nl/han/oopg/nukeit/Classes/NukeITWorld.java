package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.oopg.nukeit.Enums.GameState;
import nl.han.oopg.nukeit.Interfaces.Spawner;
import processing.core.PImage;
import nl.han.ica.oopg.view.View;

import java.util.ArrayList;
import java.util.Random;

public class NukeITWorld extends GameEngine {

    public  Ship                ship;
    private TextObject          livesText;
    private TextObject          scoreText;
    private int                 score;
    private int                 lives = 3;
    private GameState           gameState = GameState.GAME;
    private ArrayList<Spawner>  spawners;

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

        createSpawners(3f, 0.4f, 0.1f);

    }

    private void createView(int worldWith, int worldHeight){
        View view = new View(worldWith, worldHeight);
        PImage backgroundImg = loadImage("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/background.jpg");
        view.setBackground(backgroundImg);
        setView(view);
        size(worldWith, worldHeight);
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


    public void createSpawners(float asteroidsPerSecond, float aliensPerSecond, float powerUpsPerSecond) {
        spawners = new ArrayList<>();

        addGameObject(new AsteroidSpawner(this, asteroidsPerSecond));
        addGameObject(new AlienSpawner(this, aliensPerSecond));
        addGameObject(new PowerUpSpawner(this, powerUpsPerSecond));

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

    public int getRandomInRange(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private void stopGame() {
        deleteAllGameOBjects();
        deleteAllDashboards();

        for(Spawner i : spawners) {
           i.stopSpawning();
        }

        gameState = GameState.END;
    }

    @Override
    public void update() {
        if (lives <= 0) {
            stopGame();
        }
    }


}

