package patterns.factory.car1;

/**
 * Created by evami on 21.11.17.
 */
public class Toyota extends AbstractCar{

    public Toyota(String name, String model, double fuelConsumption) {
        super(name, model, fuelConsumption);
    }

    public Toyota(){
        super("Toyota", "Corolla", 5.6);
    }
}
