package shop;

import java.time.LocalDate;

/**
 * Created by evami on 09.11.17.
 */
public class Order {
    private Cart products;
    private User user;
    private LocalDate date;

    public Order(Cart products, User user) {
        this.products = products;
        this.user = user;
        this.date = LocalDate.now();
    }

    public void issueOrder(){
        if (this.user.getAccount() >= this.products.getSum()){
            this.user.getOrders().add(this);
            this.user.writeOffAccount(this.products.getSum());
        }
        else
            System.out.println("Insufficient funds");
    }

    public void cancelOrder(){
        this.user.getOrders().remove(this);
        this.user.replenishAccount(this.products.getSum());
    }

    public Cart getProducts(){
        return this.products;
    }

    public User getUser(){
        return this.user;
    }

    public LocalDate getDate(){
        return this.date;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Order order = (Order) object;

        if (products != null ? !products.equals(order.products) : order.products != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        return date != null ? date.equals(order.date) : order.date == null;
    }

    @Override
    public int hashCode() {
        int result = products != null ? products.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}
