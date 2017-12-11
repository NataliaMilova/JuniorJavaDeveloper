package patterns.factory.car1;

/**
 * Created by evami on 23.11.17.
 */
public class BmwX5 extends Bmw {
    private String age;

    public BmwX5(String name, String model, double fuelConsumption, String age) {
        super(name, model, fuelConsumption);
        this.age = age;
    }

    public BmwX5(String age) {
        super();
        this.age = age;
    }
}
