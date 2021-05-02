package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;


public class PlusSign extends SpriteObject{

    public PlusSign(){
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/minusN.png"));
        setWidth(100);
        setHeight(100);
    }

    @Override
    public void update() {

    }

}
