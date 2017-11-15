package shop;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by evami on 12.11.17.
 */
public class Menu {
    private String name;
    private Action action;

    public Menu(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    public interface Action{
        void doAction();
    }

    public String getName(){
        return this.name;
    }

    public Action getAction(){
        return this.action;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAction(Action action){
        this.action = action;
    }

}
