package nl.han.oopg.nukeit.Interfaces;

import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;

import java.util.List;


public interface Spawner extends IAlarmListener {

    void startAlarm();

    void triggerAlarm(String alarmName);

}
