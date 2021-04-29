package nl.han.oopg.nukeit.Interfaces;

import nl.han.ica.oopg.alarm.IAlarmListener;

public abstract class Spawner implements IAlarmListener {

    void startAlarm();
    void triggerAlarm(String alarmName);
    void stopSpawning();
}
