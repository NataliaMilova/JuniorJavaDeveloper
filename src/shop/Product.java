package shop;

/**
 * Created by evami on 09.11.17.
 */
public class Product {
    private String name;
    private String description;
    private double price;

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void setName (String name, User user){
        if (user.getLogin().equals("admin"))
            this.name = name;
        else
            System.out.println("User " + user.getLogin() + " do not have access");
    }

    public void setDescription(String description, User user){
        if (user.getLogin().equals("admin"))
            this.description = description;
        else
            System.out.println("User " + user.getLogin() + " do not have access");
    }

    public void setPrice(double price, User user){
        if (user.getLogin().equals("admin"))
            this.price = price;
        else
            System.out.println("User " + user.getLogin() + " do not have access");
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public double getPrice(){
        return this.price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Product product = (Product) object;

        if (Double.compare(product.price, price) != 0) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        return description != null ? description.equals(product.description) : product.description == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
