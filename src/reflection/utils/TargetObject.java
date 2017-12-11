package reflection.utils;

import patterns.factory.car1.BmwX5;
import patterns.factory.car1.Car;

import java.util.*;


/**
 * Created by evami on 23.11.17.
 */
public class TargetObject {

    private Double d = null;
    TreeSet<String> set = new TreeSet<>();
    Map<String, String> map = new HashMap<>();
    List<Car> list = new ArrayList<>();
    Car[] arr;
    private Car car;
    private String name;
    private int i;
    String[] strings;

    public TargetObject(String name, int i, Car car) {
        this.name = name;
        this.i = i;
        this.car = car;
        this.arr = new Car[]{new BmwX5("BMW", "X5", 6.26,"3"), new BmwX5("BMW", "X5", 7.4,"9")};
        this.strings = new String[]{"1", "2"};
        list.add(this.car);
        map.put("1", "1");
        set.add("1");
        set.add("2");
    }

    public static void main(String[] args) throws IllegalAccessException {
        TargetObject to = new TargetObject("hello", 1, new BmwX5("BMW", "X5", 6.4,"3"));
        System.out.println(Utils.toString(to));
    }
}
