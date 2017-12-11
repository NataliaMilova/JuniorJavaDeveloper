package patterns.observer;

/**
 * Created by evami on 21.11.17.
 */
public class RedAlarm implements Alarm {
    private Double state;

    @Override
    public void alarm(double temp) {
        if (temp >= 600) {
            if (this.state == null) {
                this.state = temp;
                System.out.println("Red alarm " + this.state + " degrees");
            }
        }
        else
            this.state = null;
    }
}
