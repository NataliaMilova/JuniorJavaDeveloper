package objects1;

/**
 * Created by evami on 20.10.17.
 */
public class Circle {
    Point o;
    double r;

    public Circle(Point o, double r){
        //тут должно быть исключение на отрицательный радиус, но у меня лапки!
        this.o = o;
        this.r = r;
    }


    public double sq(){
        if (r == 0){
            System.out.println("Warning: degenerate circle");
            return 0;
        }
        return (Math.PI * Math.pow(this.r, 2));
    }

    public double length(){
        if (r == 0){
            System.out.println("Warning: degenerate circle");
            return 0;
        }
        return (2 * Math.PI * this.r);
    }
}
