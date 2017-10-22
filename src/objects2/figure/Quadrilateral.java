package objects2.figure;

import objects1.Point;

/**
 * Created by evami on 21.10.17.
 */
public class Quadrilateral extends Shape {
    protected Point a;
    protected Point b;
    protected Point c;
    protected Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double sq(){
        double p;
        p = this.perimeter() / 2;
        return Math.sqrt(p * (p - this.a.length(this.b)) * (p - this.b.length(this.c))
                    * (p - this.c.length(this.d)) * (p - this.d.length(this.a)));
        }

    @Override
    public double perimeter(){
        return (this.a.length(this.b) + this.b.length(this.c) + this.c.length(this.d) + this.d.length(a));
    }

}
