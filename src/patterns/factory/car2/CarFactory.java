package patterns.factory.car2;

import patterns.factory.car1.Bmw;
import patterns.factory.car1.Car;
import patterns.factory.car1.Vaz;

/**
 * Created by evami on 21.11.17.
 */
public abstract class CarFactory {
    private static JapanFactory JpFactory;
    private static GermanFactory DeFactory;
    private static RussianFactory RusFactory;

    public CarFactory() {
    }

    private static class JapanFactory extends CarFactory{
        public JapanFactory() {
        }

        @Override
        public Car createCar() {
            return new Bmw();
        }
    }

    private static class GermanFactory extends CarFactory{
        public GermanFactory() {
        }

        @Override
        public Car createCar() {
            return new Bmw();
        }
    }

    private static class RussianFactory extends CarFactory{
        public RussianFactory() {
        }

        @Override
        public Car createCar() {
            return new Vaz();
        }
    }

    public static CarFactory getFactory(String region){
        switch (region){
            case "JP":
                if (JpFactory == null)
                    JpFactory = new JapanFactory();
                return JpFactory;
            case "DE":
                if (DeFactory == null)
                    DeFactory = new GermanFactory();
                return DeFactory;
            case "RU":
                if (RusFactory == null)
                    RusFactory = new RussianFactory();
                return RusFactory;

        }
        return null;
    }

    public static Car create(String region){
        CarFactory factory = getFactory(region);
        if (factory == null){
            System.out.println("Unknown region");
            return null;
        }
        return factory.createCar();
    }

    abstract public Car createCar();
}
