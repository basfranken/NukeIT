package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.oopg.nukeit.Enums.FireMode;
import nl.han.oopg.nukeit.AbstractClasses.PowerUp;

public class NormalFirePowerUp extends PowerUp {

    public NormalFirePowerUp(NukeITWorld world, int size) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/normal fire.png"), FireMode.NORMAL, world);
        setWidth(size);
        setHeight(size);
    }

}
