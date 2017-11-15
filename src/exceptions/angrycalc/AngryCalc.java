package exceptions.angrycalc;

import com.sun.xml.internal.ws.encoding.soap.SOAP12Constants;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by evami on 13.11.17.
 */
public class AngryCalc {
    private HashMap<String, Integer> args = new HashMap<>();

    public HashMap<String, Integer> getArgs(){
        return this.args;
    }

    private static void welcom(){
        System.out.println("For add variable enter <var>");
        System.out.println("For calculate enter <calc>");
        System.out.println("For calculate enter <exit>");
    }

    public void initArg(String line) throws AngryCalcExceptions{
        String[] args = line.split("\\s");
        if (args[0].matches("[^a-z-A-Z]+"))
            throw new AngryCalcExceptions("Variable must contains only letters [a - z - A - Z]: " + args[0]);
        if (!args[1].equals("="))
            throw new AngryCalcExceptions("String must be in special format: letter = int: " + line);
        try {
            this.args.put(args[0], Integer.parseInt(args[2]));
        }
        catch (NumberFormatException e){
            throw new AngryCalcExceptions("After space and = must be int: " + args[2], e);
        }
    }

    public Double calculate(String line) throws AngryCalcExceptions{
        String[] args = line.split("\\s");
        int x = 0;
        int y = 0;
        if (args[0].matches("[^a-z-A-Z]+"))
            try {
                x = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException e){
                throw new AngryCalcExceptions("Operand must be int: " + line, e);
            }
        if (args[2].matches("[^a-z-A-Z]+"))
            try {
                y = Integer.parseInt(args[2]);
            }
            catch (NumberFormatException e){
                throw new AngryCalcExceptions("Operand must be int: " + line, e);
            }
        if (x == 0 && this.args.get(args[0]) == null)
            throw new AngryCalcExceptions("Unknown operand: " + args[0]);
        if (y == 0 && this.args.get(args[2]) == null)
            throw new AngryCalcExceptions("Unknown operand: " + args[2]);
        if (x == 0) {
            x = this.args.get(args[0]);
            if (y != 0)
                switch (args[1]) {
                    case "+":
                        this.args.put(args[0], x + y);
                        return (double) (x + y);
                    case "-":
                        this.args.put(args[0], x - y);
                        return (double) (x - y);
                    case "*":
                        this.args.put(args[0], x * y);
                        return (double) (x * y);
                    case "/":
                        try {
                            this.args.put(args[0], x / y);
                            return (double) (x / y);
                        } catch (ArithmeticException e) {
                            throw new AngryCalcExceptions("Second operand must be different by zero" + line, e);
                        }
                }
            else {
                y = this.args.get(args[2]);
                switch (args[1]) {
                    case "+":
                        return (double) (x + y);
                    case "-":
                        return (double) (x - y);
                    case "*":
                        return (double) (x * y);
                    case "/":
                        try {
                            return (double) (x / y);
                        } catch (ArithmeticException e) {
                            throw new AngryCalcExceptions("Second operand must be different by zero" + line, e);
                        }
                }
            }
        }
        throw new AngryCalcExceptions("Operands must be connected with {+,-,*,/}:" + args[1]);
    }

    public static void main(String[] args) {
        System.out.println("Welcome");
        Scanner scanner = new Scanner(System.in);
        AngryCalc angryCalc = new AngryCalc();
        welcom();
        while (true) {
            String line = scanner.nextLine().trim();
            switch (line){
                case "help":
                    welcom();
                case "var":
                    System.out.println("Enter variable");
                    String var = scanner.nextLine().trim();
                    try {
                        angryCalc.initArg(var);
                    } catch (AngryCalcExceptions angryCalcExceptions) {
                        angryCalcExceptions.printStackTrace();
                    }
                    break;
                case "calc":
                    System.out.println("Enter calc expression");
                    String exp = scanner.nextLine().trim();
                    try {
                        System.out.println("Result: " + angryCalc.calculate(exp));
                    } catch (AngryCalcExceptions angryCalcExceptions) {
                        angryCalcExceptions.printStackTrace();
                    }
                    break;
                case "exit":
                    return;
            }
            System.out.println("Unknown command");
        }
    }
}
