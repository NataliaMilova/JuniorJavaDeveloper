package patterns.factory.car1;

/**
 * Created by evami on 21.11.17.
 */
public class Bmw extends AbstractCar{

    public Bmw(String name, String model, double fuelConsumption) {
        super(name, model, fuelConsumption);
    }

    public Bmw() {
        super("BMW", "X5", 6.2);
    }
}
