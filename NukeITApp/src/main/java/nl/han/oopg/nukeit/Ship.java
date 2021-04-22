package nl.han.oopg.nukeit;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.oopg.nukeit.NukeITWorld;

public class Ship extends SpriteObject {

    final int width = 100;
    final int height = 125;
    private NukeITWorld world;

    public Ship(NukeITWorld world) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/ship purple.png"));
        this.world = world;
        this.setWidth(width);
        this.setHeight(height);
        setFriction(0.015f);
    }

    private void shoot() {

        int y = (int) getY();
        world.addGameObject(new ShipBullet(world, (int) (getX() + getWidth() / 3), (int) (getY() - getHeight() / 2), -10));
    }


    @Override
    public void update() {
        if (getX() <= 0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY() <= 0) {
            setySpeed(0);
            setY(0);
        }
        if (getX() >= world.width - width) {
            setxSpeed(0);
            setX(world.width - width);
        }
        if (getY() >= world.height - height) {
            setySpeed(0);
            setY(world.height - height);
        }
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 5;
        if (keyCode == world.LEFT) {
            setDirectionSpeed(270, speed);
        }
        if (keyCode == world.UP) {
            setDirectionSpeed(0, speed);
        }
        if (keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
        }
        if (keyCode == world.DOWN) {
            setDirectionSpeed(180, speed);
        }
        if (key == ' ') {
            shoot();
            //TODO spatiebalk schietactie
        }
    }

}
