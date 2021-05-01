package nl.han.oopg.nukeit.Interfaces;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public abstract class Spawner extends GameObject implements IAlarmListener {

    private Alarm alarm;

    public Spawner(Alarm alarm){
        this.alarm = alarm;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics pGraphics) {

    }

    public abstract void startAlarm();

    public void stopSpawning() {
        alarm.stop();
    }

    public Alarm getAlarm() {
        return alarm;
    }
}
