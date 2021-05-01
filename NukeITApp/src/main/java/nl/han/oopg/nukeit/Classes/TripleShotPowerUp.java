package nl.han.oopg.nukeit.Classes;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.oopg.nukeit.Enums.FireMode;
import nl.han.oopg.nukeit.AbstractClasses.PowerUp;


public class TripleShotPowerUp extends PowerUp {

    public TripleShotPowerUp(NukeITWorld world, int size) {
        super(new Sprite("NukeITApp/src/main/java/nl/han/oopg/nukeit/data/PowerUps/triple shot.png"), FireMode.TRIPLE, world);
        setWidth(size);
        setHeight(size);
    }


}
