package bank;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by evami on 11.12.17.
 */
public class Main {

    private static final BlockingDeque<Transaction> mailerTasksDeque = new LinkedBlockingDeque();

    public static void main(String[] args) {
        Random rnd = new Random();
        Bank bank = new Bank();
        ExecutorService poolMailers = Executors.newFixedThreadPool(10);
        ExecutorService poolTasks = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++)
            bank.addUser(new Bank.User("User " + i));
        for (int i = 0; i < 100; i++)
            bank.addAccount(new Bank.Account(i + 1000, rnd.nextInt(bank.getUsers().size())));

        for (int i = 0; i < 20; i++) {
             poolTasks.submit(new Runnable() {
                @Override
                public void run() {

                    Bank.Account src = bank.getAccount(rnd.nextInt(bank.getAccounts().size()));

                    Bank.Account dest = bank.getAccount(rnd.nextInt(bank.getAccounts().size()));

                    int amount = rnd.nextInt(2000);

                    TxResult result = bank.transferMoney(src, dest, amount);

                    mailerTasksDeque.add(new Transaction(src,dest, amount, result));

                }
            });
        }
        poolTasks.shutdown();
    }

    private static class Mailer extends Thread {
        @Override
        public void run() {{
                try {
                    Transaction trans = mailerTasksDeque.takeFirst();
                    System.out.println();
                    if (trans.result == TxResult.SUCCESS){
                        String message = "Списание с " + trans.src.getId() + " на сумму " + trans.amount + "\n"
                                + "Баланс = " + trans.src.getBalance() + "\n"
                                + "Зачисление на " + trans.dest.getId() + " " + trans.amount;
                        System.out.println(message);
                    }
                    else if (trans.result == TxResult.NOT_ENOUGH){
                        System.out.println("Недостаточно средств на " + trans.src.getId());
                    }
                    else if (trans.result == TxResult.SAME_ACCOUNT){
                        System.out.println("Перевод на тот же аккаунт невозможен " + trans.src.getId() + " " + trans.dest.getId());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Transaction{
        private Bank.Account src;
        private Bank.Account dest;
        private TxResult result;
        private int amount;

        public Transaction(Bank.Account src, Bank.Account dest, int amount, TxResult result) {
            this.src = src;
            this.dest = dest;
            this.result = result;
            this.amount = amount;
        }
    }
}
