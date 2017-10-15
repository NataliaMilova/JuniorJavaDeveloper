/**
 * Created by evami on 13.10.17.
 */
public class Task2_7 {
    public static void main(String[] args) {
        int n = (int)((Math.random( ) * 28801));
        int hours = n / 3600;
        System.out.println("Осталось " + n + " секунд");
        switch (hours){
            case 0:
                System.out.println("Осталось менее часа");
                break;
            case 1:
                System.out.println("Остался " + hours + " час");
                break;
            case 2:
            case 3:
            case 4:
                System.out.println("Осталось " + hours + " часа");
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                System.out.println("Осталось " + hours + " часов");
                break;
        }
    }
}
