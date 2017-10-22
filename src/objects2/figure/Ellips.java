package objects2.figure;

import objects1.Point;

/**
 * Created by evami on 21.10.17.
 */
public class Ellips extends Shape {
    protected Point o;
    protected double r1;
    protected double r2;

    public Ellips(Point j, double r1, double r2){
        this.o = o;
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public double sq(){
        if (this.r1 == 0 || this.r2 == 0){
            System.out.println("Warning: degenerate ellips");
            return 0;
        }
        return (Math.PI * this.r1 * this.r2);
    }

    @Override
    public double perimeter(){
        if (this.r1 == 0 || this.r2 == 0){
            System.out.println("Warning: degenerate ellips");
            return 0;
        }
        return (4 * (Math.PI * this.r1 * this.r2 + Math.pow(r1 - r2, 2)) / (this.r1 + this.r2));
    }
}
