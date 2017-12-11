package patterns.observer;

/**
 * Created by evami on 21.11.17.
 */
public class Main {
    public static void main(String[] args) {
        Sensor sensor = new Sensor();
        sensor.subscribe(new GreenAlarm());
        sensor.subscribe(new YellowAlarm());
        sensor.subscribe(new RedAlarm());
        System.out.println(sensor.getTemp());
        for (int i = 0; i < 14; ++i){
            sensor.changeTemp();
            System.out.println();
        }
        sensor.minusTemp();
        sensor.changeTemp();
    }
}
