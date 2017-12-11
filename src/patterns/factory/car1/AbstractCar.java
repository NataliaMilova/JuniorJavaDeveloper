package patterns.factory.car1;

/**
 * Created by evami on 21.11.17.
 */
public abstract class AbstractCar implements Car{

    protected String name;
    protected String model;
    protected double fuelConsumption;

    public AbstractCar(){
    }

    public AbstractCar(String name, String model, double fuelConsumption) {
        this.name = name;
        this.model = model;
        this.fuelConsumption = fuelConsumption;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public String toString() {
        return "AbstractCar{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", fuelConsumption=" + fuelConsumption +
                '}';
    }

    @Override
    public double drive(double fuel) {
        return fuel / this.fuelConsumption / 100;
    }
}
