package objects2.figure;

import objects1.Point;

/**
 * Created by evami on 21.10.17.
 */
public class Circle extends Ellips {

    public Circle(Point o, double r){
        super(o, r, r);
    }

    @Override
    public double sq(){
        if (this.r1 == 0){
            System.out.println("Warning: degenerate circle");
            return 0;
        }
        return (Math.PI * this.r1 * this.r1);
    }

    @Override
    public double perimeter(){
        if (this.r1 == 0){
            System.out.println("Warning: degenerate circle");
            return 0;
        }
        return (2 * Math.PI * this.r1);
    }
}
