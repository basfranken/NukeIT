package nl.han.oopg.nukeit.Interfaces;

import nl.han.ica.oopg.alarm.IAlarmListener;

public interface Spawner extends IAlarmListener {

    void startAlarm();
    void triggerAlarm(String alarmName);
    void stopSpawning();
}
