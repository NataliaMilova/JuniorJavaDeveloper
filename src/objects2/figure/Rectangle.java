package objects2.figure;

import objects1.Point;

/**
 * Created by evami on 21.10.17.
 */
public class Rectangle extends Quadrilateral {

    public Rectangle(Point a, Point b, Point c, Point d){
        super(a, b, c, d);
    }

    public boolean validate(){
        double abad  = (this.b.x - this.a.x) * (this.d.x - this.a.x)
                + (this.b.y - this.a.y) * (this.d.y - this.a.y);
        double bccd = (this.c.x - this.b.x) * (this.d.x - this.c.x)
                + (this.c.y - this.b.y) * (this.d.y - this.c.y);
        return ((abad == 0) && (bccd == 0));
    }

    public double perimeter(){
        if (!this.validate()){
            System.out.println("Warning: not rectangle");
            return -1;
        }
        return (2 * (this.a.length(b) + this.b.length(c)));
    }

    public double sq(){
        if (!this.validate()){
            System.out.println("Warning: not rectangle");
            return -1;
        }
        return (this.a.length(b) * this.b.length(c));
    }
}
