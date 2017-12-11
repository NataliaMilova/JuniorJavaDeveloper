package multithreading.restaurant;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by evami on 29.11.17.
 */
public class NewRestaurant {

    private ArrayList<Waiter> waiters = new ArrayList<>();
    private ArrayList<Cooker> cookers = new ArrayList<>();
    private final ArrayDeque<AbstractTask> waitersTasks = new ArrayDeque<>();
    private final ArrayDeque<AbstractTask> cookersTasks = new ArrayDeque<>();


    private void start(){
        createAndStartStaff();
        Random rnd = new Random();
        for (int i = 0; i < 200; ++i){
            Customer customer = new Customer(i);
            customer.setName("Customer" + customer.getNameCustomer());
            customer.start();
            customer.setWait(true);
            try {
                Thread.currentThread().sleep(rnd.nextInt(30));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createAndStartStaff(){// staff
        for (int i = 0; i < 15; ++i){
            Waiter waiter = new Waiter();
            waiter.setName("Waiter " + i);
            waiters.add(waiter);
        }

        for (int i = 0; i < 10; ++i){
            Cooker cooker = new Cooker();
            cooker.setName("Cooker " + i);
            cookers.add(cooker);
        }

        for (Waiter waiter: waiters){
            waiter.start();
        }

        for (Cooker cooker: cookers){
            cooker.start();
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to restaurant");
        new NewRestaurant().start();
    }

    private class Customer extends Thread{
        private int name;
        private boolean wait;
        private boolean isHungry = true;

        public int getNameCustomer() {
            return name;
        }

        public Customer(int name) {
            this.name = name;
        }

        public void setHungry(boolean hungry) {
            isHungry = hungry;
        }

        public boolean isWait() {
            return wait;
        }

        public void setWait(boolean wait) {
            this.wait = wait;
        }

        @Override
        public void run(){
            while (!isInterrupted()){
                int len = 0;
                synchronized (NewRestaurant.this.waitersTasks){
                    NewRestaurant.this.waitersTasks.addLast(new AcceptOrderTask(this));
                    NewRestaurant.this.waitersTasks.notify();
                }
                break;
            }
            int len = 0;
            while (!isInterrupted()) {
                try {
                    sleep(100);
                    if (isHungry){
                        len += 100;
                        if (len >= 700){
                            System.out.println("I leave restaurant2!!!!! " + this.getNameCustomer());
                            synchronized (this){
                                this.setWait(false);
                                return;
                            }
                        }
                    }
                    else {
                        System.out.println("I have got my order " + this.getNameCustomer());
                        return;
                    }
                } catch (InterruptedException e) {
                    interrupt();
                    e.printStackTrace();
                }
            }

        }
    }

    private class Waiter extends Thread{

        @Override
        public void run() {
            AbstractTask task = null;
            while (!isInterrupted()){
                synchronized (NewRestaurant.this.waitersTasks){
                    try {
                        NewRestaurant.this.waitersTasks.wait();
                        if (!NewRestaurant.this.waitersTasks.isEmpty())
                            task = NewRestaurant.this.waitersTasks.pollFirst();
                    } catch (InterruptedException e) {
                        interrupt();
                        e.printStackTrace();
                    }
                }
                if (task != null)
                    task.action();
            }
        }
    }

    private class Cooker extends Thread{

        @Override
        public void run() {
            Random rnd = new Random();
            AbstractTask task = null;
            while (!isInterrupted()){
                synchronized (NewRestaurant.this.cookersTasks){
                    try {
                        NewRestaurant.this.cookersTasks.wait();
                        if (!NewRestaurant.this.cookersTasks.isEmpty())
                            task = NewRestaurant.this.cookersTasks.pollFirst();
                    } catch (InterruptedException e) {
                        interrupt();
                        e.printStackTrace();
                    }
                }
                try {
                    sleep(rnd.nextInt(400 - 100));
                } catch (InterruptedException e) {
                    interrupt();
                    e.printStackTrace();
                }
                if (task != null)
                    task.action();
            }
        }
    }

    private abstract class AbstractTask{
        Customer customer;

        public AbstractTask(Customer customer) {
            this.customer = customer;
        }

        abstract void action();
    }

    private class AcceptOrderTask extends AbstractTask{

        public AcceptOrderTask(Customer customer) {
            super(customer);
        }

        @Override
        void action() {
            while (this.customer.isWait()) {
                synchronized (NewRestaurant.this.cookersTasks) {
                    NewRestaurant.this.cookersTasks.add(new ReadyTask(this.customer));
                    cookersTasks.notify();
                    break;
                }
            }
        }
    }

    private class ReadyTask extends AbstractTask{

        public ReadyTask(Customer customer) {
            super(customer);
        }

        @Override
        void action() {
            while (this.customer.isWait()) {
                synchronized (NewRestaurant.this.waitersTasks) {
                    NewRestaurant.this.waitersTasks.add(new DeliverOrderTask(this.customer));
                    waitersTasks.notify();
                    break;
                }
            }
        }
    }

    private class DeliverOrderTask extends AbstractTask{

        public DeliverOrderTask(Customer customer) {
            super(customer);
        }

        @Override
        void action() {
            if (customer.isWait()){
                synchronized (customer){
                    customer.setHungry(false);
                }
            }
        }
    }
}
