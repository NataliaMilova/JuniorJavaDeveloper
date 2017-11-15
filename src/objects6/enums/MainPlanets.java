package objects6.enums;

/**
 * Created by evami on 02.11.17.
 */
public class MainPlanets {
    public static void main(String[] args) {
        Planets pl1 = Planets.EARTH;
        Planets pl2 = Planets.VENUS;
        Planets pluto = Planets.valueOf("PLUTO");// возвращает объект перечисления по его строковому представлению
        System.out.println(pluto);
        for (Planets planet: Planets.values()/*значения перечисления по порядку*/){
            System.out.println("weight of " + planet.name() + " " + planet.getWeight() + " кг");
            System.out.println("radius of " + planet.name() + " " + planet.getRadius() + " м");
            System.out.println("radius of orbit of " + planet.name() + " " + planet.getRadiusOfOrbit() + " м");
            System.out.println();
        }
        System.out.println(pl1.compareTo(pluto));//отнимает порядковый номер с чем сравниваем
        System.out.println(pl1.ordinal());// порядковый номер с 0
    }
}
