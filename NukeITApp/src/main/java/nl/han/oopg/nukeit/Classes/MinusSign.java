package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;


import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.oopg.nukeit.Enums.FireMode;

import java.util.List;
public class MinusSign extends SpriteObject{

    final int width = 100;
    final int height = 100;
    private NukeITWorld world;

    public MinusSign(NukeITWorld world){
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/plusN.png"));
        this.world = world;
        this.setWidth(width);
        this.setHeight(height);
    }


    @Override
    public void update() {

    };

}
