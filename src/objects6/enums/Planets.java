package objects6.enums;

/**
 * Created by evami on 02.11.17.
 */
public enum Planets {
    MERCURY(0.32868E+24, 2.439E+6, 0),
    VENUS(4.81068E+24, 6.052E+6, 0),
    EARTH(5.97600E+24, 6.378E+6, 0),
    MARS(0.63345E+24, 3.488E+6, 0),
    JUPITER(1876.64328E+24, 71.300E+6, 0),
    SATURN(561.80376E+24, 60.100E+6, 0),
    URANUS(86.05440E+24, 26.500E+6, 0),
    NEPTUNE(101.59200E+24, 24.750E+6, 0),
    PLUTO(0.01195E+24, 2.000E+6, 0);

    private double weight;
    private double radius;
    private double radiusOfOrbit;// здесь должны быть большие числа, но у меня лапки

    Planets(double weight, double radius, double radiusOfOrbit) {
        this.weight = weight;
        this.radius = radius;
        this.radiusOfOrbit = radiusOfOrbit;
    }

    public double getWeight(){
        return this.weight;
    }

    public double getRadius(){
        return this.radius;
    }

    public double getRadiusOfOrbit(){
        return this.radiusOfOrbit;
    }

}
