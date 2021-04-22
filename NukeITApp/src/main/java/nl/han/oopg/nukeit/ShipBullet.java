package nl.han.oopg.nukeit;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;


public class ShipBullet extends SpriteObject {

    private NukeITWorld world;
    final int width     = 40;
    final int height    = 70;

    public ShipBullet(NukeITWorld world, int startX, int startY, int speed) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/laserBullet.png"));
        this.world = world;
        this.setWidth(width);
        this.setHeight(height);
        setX(startX);
        setY(startY);
        setySpeed(speed);
    }


    @Override
    public void update() {
        if (getY() <= 0) {
            world.deleteGameObject(this);
        }
    }
}
