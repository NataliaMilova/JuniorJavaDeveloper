package objects1;

/**
 * Created by evami on 20.10.17.
 */
public class Triangle {
    Point a;
    Point b;
    Point c;

    public Triangle(Point a, Point b, Point c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean validate(){
        return (this.a.length(this.b) + this.b.length(this.c) < this.c.length(this.a)
                && (this.a.length(this.b) + this.a.length(this.c) < this.b.length(this.c))
                && (this.b.length(this.c) + this.c.length(this.a) < this.a.length(this.b)));
    }

    public double sq(){
        double p;
        if (this.validate()){
            p = this.perimeter() / 2;
            return Math.sqrt(p * (p - this.a.length(this.b)) * (p - this.b.length(this.c))
                    * (p - this.c.length(this.a)));
        }
        return 0;
    }

    public double perimeter(){
        if (!this.validate())
            System.out.println("Warning: degenerate triangle");
        return (this.a.length(this.b) + this.b.length(this.c) + this.c.length(this.a));
    }
}
