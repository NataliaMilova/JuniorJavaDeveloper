package patterns.factory.car1;

/**
 * Created by evami on 21.11.17.
 */
public class CarFactory {

    public static Car createCar(String region){
        switch (region){
            case "JP":
                return new Toyota();
            case "DE":
                return new Bmw();
            case "RU":
                return new Vaz();
        }
        System.out.println("Unknown region");
        return null;
    }
}
