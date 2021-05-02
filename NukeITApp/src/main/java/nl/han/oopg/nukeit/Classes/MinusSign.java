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


    public MinusSign(){
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/plusN.png"));
        setWidth(100);
        setHeight(100);
    }

    @Override
    public void update() {

    }

}
