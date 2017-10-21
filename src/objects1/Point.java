package objects1;

/**
 * Created by evami on 20.10.17.
 */
public class Point {
    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double length(Point point){
        return Math.sqrt(Math.pow((point.x - this.x), 2) + Math.pow(point.y - this.y, 2));
    }
}
