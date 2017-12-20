package bank;

import java.util.*;

/**
 * Created by evami on 11.12.17.
 */
public class Bank {
    private static int accountId = 0;
    private static int userId = 0;
    private HashMap<Integer, User> users = new HashMap<>();
    private HashMap<Integer, Account> accounts = new HashMap<>();

    public static class Account{
        private int id;
        private int balance;
        private int userId;

        public Account(int balance, int userId) {
            this.id = Bank.accountId++;
            this.balance = balance;
            this.userId = userId;
        }

        public int getId() {
            return id;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getUserId() {
            return userId;
        }
    }

    public static class User{
        private int id;
        private String name;

        public User(String name) {
            this.id = Bank.userId++;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public TxResult transferMoney(Account src, Account dest, int amount){
        if (src.id == dest.id)
            return TxResult.SAME_ACCOUNT;
        else
            if (src.balance < amount)
                return TxResult.NOT_ENOUGH;
            else{
                if (src.id < dest.id)
                    synchronized (src){
                        synchronized (dest){
                            src.balance -= amount;
                            dest.balance += amount;
                        }
                    }
                else
                    synchronized (dest){
                        synchronized (src){
                            src.balance -= amount;
                            dest.balance += amount;
                        }
                    }
                //transaction(src, dest, amount);
                return TxResult.SUCCESS;
            }

    }

    /*private synchronized void transaction (Account src, Account dest, int amount){
        src.balance -= amount;
        dest.balance += amount;
    }*/

    public void addUser (User user){
        users.put(user.id, user);
    }

    public void addAccount (Account account){
        accounts.put(account.id, account);
    }

    public HashMap<Integer, User> getUsers() {
        return users;
    }

    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }

    public User getUser(int id){
        return users.get(id);
    }

    public Account getAccount(int id){
        return accounts.get(id);
    }
}
