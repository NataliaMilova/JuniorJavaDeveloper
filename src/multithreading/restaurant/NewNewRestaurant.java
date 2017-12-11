package multithreading.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by evami on 02.12.17.
 */
public class NewNewRestaurant {

    private final BlockingDeque<AbstractTask> staffTasks = new LinkedBlockingDeque<>();
    private final List<Staff> staff = new ArrayList<>();

    private void createAndStartStaff(){
        int max = 20;
        for (int i = 0; i < max; ++i){
            Staff staff = new Staff();
            staff.setName("Staff " + i);
            this.staff.add(staff);
        }

        for (Staff staff: this.staff){
            staff.start();
        }
    }

    private void start(){
        Random rnd = new Random();
        createAndStartStaff();
        for (int i = 0; i < 200; ++i){
            Customer customer = new Customer();
            customer.setName("Customer" + i);
            customer.start();
            try {
                Thread.currentThread().sleep(rnd.nextInt(30));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new NewNewRestaurant().start();
    }

    private class Customer extends Thread{
        private volatile boolean isWait = true;
        private volatile boolean isHungry = true;

        public boolean isWait() {
            return isWait;
        }

        public void setWait(boolean wait) {
            isWait = wait;
        }

        public boolean isHungry() {
            return isHungry;
        }

        public void setHungry(boolean hungry) {
            isHungry = hungry;
        }

        @Override
        public void run() {
            int len = 0;
            staffTasks.addLast(new AcceptOrderTask(this));
            while (!isInterrupted()){
                try {
                    sleep(100);
                    if (isHungry){
                        len += 100;
                        if (len >= 700){
                            System.err.println("I am leave restaurant!!!!! " + this.getName());
                            isWait = false;
                            return;
                        }
                    }
                    else {
                        System.out.println("I have my order " + this.getName());
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Staff extends Thread{
        @Override
        public void run() {
            AbstractTask task;
            Random rnd = new Random();
            while (!isInterrupted()){
                task = null;
                try {
                    task = staffTasks.takeFirst();
                    if (task instanceof CookingOrderTask)
                        sleep(rnd.nextInt(700 - 100));
                    task.action();
                } catch (InterruptedException e) {
                    interrupt();
                    e.printStackTrace();
                }
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
            if (this.customer.isWait())
                staffTasks.addLast(new CookingOrderTask(this.customer));
        }
    }

    private class CookingOrderTask extends AbstractTask{

        public CookingOrderTask(Customer customer) {
            super(customer);
        }

        @Override
        void action() {
            if (this.customer.isWait())
                staffTasks.addLast(new DeliverOrderTask(this.customer));
        }
    }

    private class DeliverOrderTask extends AbstractTask{

        public DeliverOrderTask(Customer customer) {
            super(customer);
        }

        @Override
        void action() {
            if (this.customer.isWait())
                this.customer.setHungry(false);
        }
    }

}
