package shop;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by evami on 09.11.17.
 */
public class Shop {
    private HashSet<User> users;
    private HashMap<Product, Integer> products;
    private HashSet<Order> orders;
    public Cart cart;
    public User nowUser;

    public Shop() {
        this.users = new HashSet<>();
        this.products = new HashMap<>();
        this.orders = new HashSet<>();
        this.cart = new Cart();
    }

    public Shop(HashSet<User> users, HashMap<Product, Integer> products){
        this.users = users;
        this.products = products;
        this.orders = new HashSet<>();
        this.cart = new Cart();
    }

    public void logoutUser(){
        this.nowUser = null;
    }

    public void registrationUser(String login, String password) {
        if (!login.equals("admin")) {
            User user = new User(login, password);
            if (users.contains(user))
                System.out.println("User is registered yet");
            else {
                users.add(user);
                System.out.println("User is registered");
            }
        }
    }

    public void authorizationUser(String login, String password) {
        User user = new User(login, password);
        if (users.contains(user))
            this.nowUser = user;
        else {
            System.out.println("User is not registered");
        }
    }

    public void adminAddUser(String login, String password) {
        if (this.nowUser.getLogin().equals("admin"))
            registrationUser(login, password);
        else
            System.out.println("User " + this.nowUser.getLogin() + " do not have access");
    }

    public void adminRemoveUser(User user) {
        if (this.nowUser.getLogin().equals("admin"))
            users.remove(user);
        else
            System.out.println("User " + this.nowUser.getLogin() + " do not have access");
    }

    public void addProduct(Product product, int quantity) {
        if (this.nowUser.getLogin().equals("admin"))
            if (this.products.get(product) != null)
                products.put(product, this.products.get(product) + quantity);
            else
                products.put(product, quantity);
        else
            System.out.println("User " + this.nowUser.getLogin() + " do not have access");
    }

    public void getProduct(Product product, int quantity) {
        if (this.products.get(product) != null)
            if (this.products.get(product) > quantity)
                this.products.put(product, this.products.get(product) - quantity);
    }

    public void removeProduct(Product product) {
        if (this.nowUser.getLogin().equals("admin"))
            if (this.products.get(product) != null)
                products.remove(product);
    }

    public void orderTransaction(Order order) {
        int quantity;
        boolean success = true;
        for (Product product : this.products.keySet()) {
            quantity = order.getProducts().getProducts().get(product);
            if (this.products.get(product) < quantity)
            {
                System.out.println("Shop has not " + quantity + " of" + product.getName());
                success = false;
            }
        }
        if (!success) {
            for (Product product : this.products.keySet()) {
                getProduct(product, order.getProducts().getProducts().get(product));
            }
            order.issueOrder();
        }
    }

    public void cancelOrderTransaction(Order order) {
        if (this.nowUser.getLogin().equals("admin")) {
            for (Product product : this.products.keySet()) {
                addProduct(product, order.getProducts().getProducts().get(product));
            }
            order.cancelOrder();
        }
    }

    public HashSet<Order> getOrders(){
        if (this.nowUser.getLogin().equals("admin"))
            return this.orders;
        System.out.println("User " + this.nowUser.getLogin() + " do not have access");
        return (HashSet<Order>) Collections.EMPTY_SET;
    }

    public HashMap<Product, Integer> getProducts(){
        return this.products;
    }

}

