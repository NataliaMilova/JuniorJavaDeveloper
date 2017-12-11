package patterns.factory.car1;

/**
 * Created by evami on 21.11.17.
 */
public class Vaz extends AbstractCar {

    public Vaz(String name, String model, double fuelConsumption) {
        super(name, model, fuelConsumption);
    }

    public Vaz() {
        super("ВАЗ", "2110", 7.2);
    }
}
