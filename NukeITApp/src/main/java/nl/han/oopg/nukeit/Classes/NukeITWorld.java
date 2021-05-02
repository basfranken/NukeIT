

package nl.han.oopg.nukeit.Classes;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.oopg.nukeit.Enums.GameState;
import nl.han.oopg.nukeit.AbstractClasses.Spawner;
import processing.core.PImage;
import nl.han.ica.oopg.view.View;

import java.util.ArrayList;
import java.util.Random;


public class NukeITWorld extends GameEngine {


    private Sound               jump;
    public  MinusSign           minusSign;
    public  PlusSign            plusSign;
    public  Ship                ship;
    private TextObject          livesText;
    private TextObject          scoreText;
    private TextObject          difficultyText;
    private TextObject          startGameTexT;
    private int                 score;
    private int                 lives = 1;
    private GameState           gameState = GameState.START;
    private ArrayList<Spawner>  spawners;
    private int                 difficulty = 1;
    private TextObject          endGameText;
    private TextObject          returnText;



    public static void main(String[] args) {
        NukeITWorld app = new NukeITWorld();
        app.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWith = 1280;
        int worldHeight = 900;

        createView(worldWith, worldHeight);
        if (gameState == GameState.START){
            selectionScreen();
        }
        if (gameState == GameState.GAME) {

            //backGroundy();
            createObjects();
            createDashboards();

            createSpawners(1000 - (50 * difficulty), 4000 - (100 * difficulty), 3000 - (100 * difficulty));
        }

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
        addGameObject(ship, getWidth() / 2 - ship.getWidth() / 2, getHeight() - ship.getHeight()*2);

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


    public void createSpawners(long milliSecondsPerAsteroid, long milliSecondsPerAlien, long milliSecondsPerPowerUp) {
        spawners = new ArrayList<>();

        spawners.add(new AsteroidSpawner(this, milliSecondsPerAsteroid));
        spawners.add(new AlienSpawner(this, milliSecondsPerAlien));
        spawners.add(new PowerUpSpawner(this, milliSecondsPerPowerUp));

        for(Spawner i : spawners) {
            addGameObject(i);
        }
    }

    public void subtractLife() {
        if (lives >= 0) {
            lives--;
        } else {
            lives = 0;
        }
        livesText.setText("LIVES: " + lives);
    }

    public void addLife() {
        lives++;
        livesText.setText("LIVES: " + lives);
    }


    public void soundBite(){
        jump = new Sound(this, "NukeITApp/src/main/java/nl/han/oopg/nukeit/data/asteroidSpawnSound.mp3");
        jump.cue(0);
        jump.play();
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
        scoreScreen();
    }

    @Override
    public void update() {
        if (lives <= 0) {
            stopGame();

        }
    }
    public void updateDifficulty(int diffiultyToAdd) {
        difficulty = difficulty + diffiultyToAdd;
        difficultyText.setText("Difficulty : " + difficulty);
    }

    boolean overRect(int x, int y, int width, int height)  {
        return mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;
    }

    @Override
    public void mousePressed() {


        if (gameState == GameState.START) {
            if(overRect(50, 50, 100, 100)){
                print(difficulty);
                if (difficulty <= -5){
                    difficulty = -5;
                }
                else{
                    updateDifficulty(-1);

                }
            }
            if(overRect(getView().getWorldWidth()-200, 50, 100, 100)){
                print(difficulty);
                if (difficulty >= 5){
                    difficulty = 5;
                }
                else{
                    updateDifficulty(1);

                }
            }
            if(overRect(0, 300, getView().getWorldWidth(), getView().getWorldHeight())){
                deleteGameObject(plusSign);
                deleteGameObject(minusSign);
                deleteGameObject(difficultyText);
                deleteGameObject(startGameTexT);
                gameState = GameState.GAME;
                lives = 1;
                setupGame();
            }
        }
        if (gameState == GameState.END) {


            if(overRect(0, 0, getView().getWorldWidth(),  getView().getWorldHeight())){

                deleteGameObject(endGameText);
                deleteGameObject(returnText);
                gameState = GameState.START;
                lives = 1;
                score = 0;
                selectionScreen();


            }
        }
    }

    public void scoreScreen(){
        endGameText = new TextObject("uw score is: " + score, 40);
        endGameText.setForeColor(255, 0, 0, 255);
        addGameObject(endGameText, getView().getWorldWidth()/2-200, 50);

        returnText = new TextObject("Click here to go back to startscreen" , 50);
        returnText.setForeColor(255, 0, 0, 255);
        addGameObject(returnText, getView().getWorldWidth()/2-400, 500);
    }
    public void selectionScreen() {
        plusSign = new PlusSign(this);
        addGameObject(plusSign, 50, 50);

        minusSign = new MinusSign(this);
        addGameObject(minusSign, getView().getWorldWidth() - 200, 50);

        startGameTexT = new TextObject("Click here to start", 60);
        startGameTexT.setForeColor(255, 0, 0, 255);
        addGameObject(startGameTexT, getView().getWorldWidth() / 2 - 300, getView().getWorldHeight()/2);


        difficultyText = new TextObject("Difficulty level: " + difficulty, 45);
        difficultyText.setForeColor(255, 0, 0, 255);
        addGameObject(difficultyText, getView().getWorldWidth() / 2 -150, 75);

    }


}

