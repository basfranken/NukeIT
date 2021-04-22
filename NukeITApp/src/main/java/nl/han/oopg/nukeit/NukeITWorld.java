package nl.han.oopg.nukeit;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.TextObject;
import processing.core.PImage;
import nl.han.ica.oopg.view.View;

public class NukeITWorld extends GameEngine {

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
        createObjects();
        createDashboard(200, 200);
        createAsteroidSpawner();

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
        Ship ship = new Ship(this);
        addGameObject(ship, 500, 650);
    }

    private void createDashboard(int dashboardWidth, int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        TextObject dashboardText = new TextObject("SCORE : ", 45);
        dashboardText.setForeColor(255, 0, 0, 255);
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }

    public void createAsteroidSpawner() {
        asteroidSpawner = new AsteroidSpawner(this, 1);
    }

    @Override
    public void update() {
    }

}

