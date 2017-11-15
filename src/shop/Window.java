package shop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by evami on 12.11.17.
 */
public class Window {
    public HashMap<Character, Menu> menu;

    public Window(HashMap<Character, Menu> menu){
        if (menu == null)
            this.menu = new HashMap<>();
        else
            this.menu = menu;
    }

}
