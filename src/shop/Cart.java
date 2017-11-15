package shop;

import java.util.HashMap;

/**
 * Created by evami on 09.11.17.
 */
public class Cart {
    private HashMap<Product, Integer> products;
    private int sum;

    public Cart() {
        this.products = new HashMap<Product, Integer>();
    }

    public void addProduct(Product product, int quantity){
        if (this.products.get(product) != null)
            this.products.put(product, this.products.get(product) + quantity);
        else
            this.products.put(product, quantity);
        this.sum += product.getPrice() * quantity;
    }

    public void removeProduct(Product product, int quantity){
        if (this.products.get(product) != null)
            if (this.products.get(product) > quantity)
                this.products.put(product, this.products.get(product) - quantity);
            else
                this.products.remove(product);
        this.sum -= product.getPrice() * quantity;
    }

    public HashMap<Product, Integer> getProducts(){
        return this.products;
    }

    public int getSum(){
        return this.sum;
    }

    public void clearCart(){
        this.products.clear();
        this.sum = 0;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                ", sum=" + sum +
                '}';
    }
}
