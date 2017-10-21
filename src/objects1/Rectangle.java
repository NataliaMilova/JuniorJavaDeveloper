package objects1;

/**
 * Created by evami on 20.10.17.
 */
public class Rectangle {
    Point a;
    Point b;
    Point c;
    Point d;

    public Rectangle(Point a, Point b, Point c, Point d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public boolean validate(){
        double abad  = (this.b.x - this.a.x) * (this.d.x - this.a.x)
                + (this.b.y - this.a.y) * (this.d.y - this.a.y);
        double bccd = (this.c.x - this.b.x) * (this.d.x - this.c.x)
                + (this.c.y - this.b.y) * (this.d.y - this.c.y);
        return ((abad == 0) && (bccd == 0));
    }

    public double perimetr(){
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
