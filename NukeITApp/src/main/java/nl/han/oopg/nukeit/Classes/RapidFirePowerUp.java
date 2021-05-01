package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.oopg.nukeit.Enums.FireMode;
import nl.han.oopg.nukeit.Interfaces.PowerUp;


public class RapidFirePowerUp extends PowerUp {

    public RapidFirePowerUp(NukeITWorld world, int size) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/rapid fire.png"), FireMode.RAPID, world);
        setWidth(size);
        setHeight(size);
    }

}
