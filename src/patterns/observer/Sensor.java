package patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evami on 21.11.17.
 */
public class Sensor {
    private List<Alarm> alarms = new ArrayList<>();
    private double temp;

    public void subscribe(Alarm newAlarm){
        this.alarms.add(newAlarm);
    }

    public void unsubscribe(Alarm alarm){
        this.alarms.remove(alarm);
    }

    public void changeTemp(){
        this.temp += 50;
        notifyAlarms(this.temp);
    }

    public void minusTemp(){
        this.temp -= 150;
        notifyAlarms(this.temp);
    }

    public double getTemp() {
        return temp;
    }

    private void notifyAlarms(double temp) {
        System.out.println(this.temp);
        for (Alarm alarm : this.alarms)
            alarm.alarm(this.temp);
    }

}
