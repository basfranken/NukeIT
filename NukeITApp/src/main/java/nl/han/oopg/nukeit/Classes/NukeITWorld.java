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


    private Sound               shipShoot;
    private Sound               alienShoot;

    public  MinusSign           minusSign;
    public  PlusSign            plusSign;
    public  Ship                ship;
    private TextObject          livesText;
    private TextObject          scoreText;
    private TextObject          difficultyText;
    private TextObject          startGameTexT;
    private TextObject          endGameText;
    private TextObject          returnText;
    private int                 score;
    private int                 lives = 3;
    private int                 difficulty = 1;
    private int                 maxLives = 5;
    private GameState           gameState = GameState.START;
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
        if (gameState == GameState.START){
            selectionScreen();
        }
        if (gameState == GameState.GAME) {

            //backGroundy();
            initializeSounds();
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

    /***
     * Creates and adds the dashboards for displaying the current score and lives.
     */
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

    /***
     * Creates spawners, and puts them in the arraylist spawners,
     * then iterates over this arraylist and adds all of the spawners to the NukeITWorld.
     * @param milliSecondsPerAsteroid How many milliseconds between every spawned asteroid.
     * @param milliSecondsPerAlien    How many milliseconds between every spawned Alien.
     * @param milliSecondsPerPowerUp  How many milliseconds between every spawned PowerUp.
     */
    public void createSpawners(long milliSecondsPerAsteroid, long milliSecondsPerAlien, long milliSecondsPerPowerUp) {
        spawners = new ArrayList<>();

        spawners.add(new AsteroidSpawner(this, milliSecondsPerAsteroid));
        spawners.add(new AlienSpawner(this, milliSecondsPerAlien));
        spawners.add(new PowerUpSpawner(this, milliSecondsPerPowerUp));

        for(Spawner i : spawners) {
            addGameObject(i);
        }
    }

    /***
     * subtracts exactly 1 life from 'lives', unless lives is already 0 or lower.
     * Then updates the text displaying lives.
     */
    public void subtractLife() {
        if (lives >= 0) {
            lives--;
        } else {
            lives = 0;
        }
        updateLivesText();
    }

    /***
     * Adds 1 life to 'lives' unless 'lives' is greater than 'maxLives'.
     * Lives should not be negative, so if 'lives' is smaller than 0 it sets 'lives' to zero.
     * If 'lives' is greater than 'maxLives' it sets 'lives' equal to 'maxLives'
     */
    public void addLife() {
        if (lives <= maxLives) {
            lives++;
        }
        if (lives < 0) {
            lives = 0;
        } else if (lives > maxLives){
            lives = maxLives;
        }
        updateLivesText();
    }

    /***
     * Updates the text displaying current lives.
     */
    private void updateLivesText(){
        livesText.setText("LIVES: " + lives);
    }

    /***
     * Loads all sounds into variables.
     */
    private void initializeSounds() {
        shipShoot = new Sound(this, "NukeITApp/src/main/java/nl/han/oopg/nukeit/data/laser2.wav");
        alienShoot = new Sound(this, "NukeITApp/src/main/java/nl/han/oopg/nukeit/data/laser.wav");
    }

    /***
     * Adds given 'scoreToAdd' to current score.
     * Then updates displayed score.
     * if score is 0 or negative this method does nothing.
     * @param scoreToAdd score to add to current score.
     */
    public void updateScore(int scoreToAdd) {
        if (scoreToAdd <= 0) {
            return;
        }
        score = score + scoreToAdd;
        scoreText.setText("SCORE : " + score);
    }

    /***
     * Returns a random value between 2 integers.
     * @param min minimal value to be returned
     * @param max maximal value to be returned
     * @return random integer.
     */
    public int getRandomInRange(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /***
     * Deletes all gameObjects and Dashboards
     * and stops all spawners from spawning,
     * since deleting them doesn't stop them from spawning.
     * sets 'gameState to 'END'.
     */
    private void stopGame() {
        deleteAllGameOBjects();
        deleteAllDashboards();

        for(Spawner i : spawners) {
            i.stopSpawning();
        }

        gameState = GameState.END;
        scoreScreen();
    }

    /***
     * constantly checks if lives have run out. If they have it stops the game.
     */
    @Override
    public void update() {
        if (lives <= 0) {
            stopGame();

        }
    }

    /***
     * Updates difficulty and the text that displays it.
     * @param difficultyToAdd difficulty to add to current difficulty.
     */
    public void updateDifficulty(int difficultyToAdd) {
        difficulty = difficulty + difficultyToAdd;
        difficultyText.setText("Difficulty : " + difficulty);
    }

    /***
     * Check if location of the mouse is currently over a rectangle.
     * @param x         x posisition of rectangle to check.
     * @param y         y posisition of rectangle to check.
     * @param width     width of rectangle to check.
     * @param height    height of rectangle to check.
     * @return          true if mouse is over rectangle, false if not.
     */
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
                //lives = 1;
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

    public Sound getShipShoot() {
        return shipShoot;
    }

    public Sound getAlienShoot() {
        return alienShoot;
    }
}

