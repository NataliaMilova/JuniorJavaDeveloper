package objects2.figure;

import objects1.Point;

/**
 * Created by evami on 21.10.17.
 */
public class Square extends Rectangle {

    public Square(Point a, Point b, Point c, Point d){
        super(a, b, c, d);
    }

    @Override
    public boolean validate(){
        return (super.validate() && this.a.length(b) == this.b.length(c));
    }

    @Override
    public double sq(){
        if (!this.validate()){
            System.out.println("Warning: not square");
            return -1;
        }
        return (Math.pow(this.a.length(b) ,2 ));
    }

    @Override
    public double perimeter(){
        if (!this.validate()){
            System.out.println("Warning: not square");
            return -1;
        }
        return (4 * this.a.length(b));
    }
}
