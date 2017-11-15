package shop;

import java.util.HashSet;

/**
 * Created by evami on 09.11.17.
 */
public class User {
    private String login;
    private int password;
    private double account;
    private HashSet<Order> orders;

    public User(String login, String password) {
        this.login = login;
        this.password = password.hashCode();
        this.orders = new HashSet<>();
    }

    public void replenishAccount (double sum){
        if (sum > 0)
            this.account += sum;
        else
            System.out.println("Wrong sum for replenish account");
    }

    public void writeOffAccount (double sum){
        if (sum > 0)
            this.account -= sum;
        else
            System.out.println("Wrong sum for write off account");
    }

    public String getLogin(){
        return this.login;
    }

    public double getAccount(){
        return this.account;
    }

    public void setLogin(String newLogin, String password){
        if (!newLogin.equals("admin"))
            if (!this.login.equals("admin"))
                if (password.hashCode() == this.password)
                    this.login = newLogin;
                else
                    System.out.println("Wrong old password");
            else
                System.out.println("Admin can not change login");
    }

    public void setPassword(String oldPassword, String newPassword){
        if (oldPassword.hashCode() == this.password)
            this.password = newPassword.hashCode();
        else
            System.out.println("Wrong old password");
    }

    public HashSet<Order> getOrders(){
        return this.orders;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        User user = (User) object;

        if (password != user.password) return false;
        return login != null ? login.equals(user.login) : user.login == null;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + password;
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", account=" + account +
                '}';
    }
}
