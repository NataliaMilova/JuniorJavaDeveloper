package objects3.arraylist;
import java.util.Arrays;

/**
 * Created by evami on 24.10.17.
 */
public class ArrayList implements List, Stack, Queue {
    private Object[] objects;
    private int size;

    public ArrayList(){
        this.objects = new Object[10];
    }

    public ArrayList(int size){
        this.objects = new Object[size];
    }

    @Override
    public void add(Object object, int index){
        Object[] tmp;
        if (index > this.size)
            System.out.println("Index error");
        else{
            if (this.size + 1 <= this.objects.length){
                System.arraycopy(this.objects, index, this.objects,index + 1, this.size - index);
                this.objects[index] = object;
            }
            else{
                if (this.objects.length == this.size){
                    tmp = new Object[this.objects.length + 10];
                    System.arraycopy(this.objects, 0, tmp,0, this.objects.length);
                    this.objects = tmp;
                }
                else{
                    tmp = new Object[this.objects.length + 10];
                    System.arraycopy(this.objects, 0, tmp,0, index - 1);
                    System.arraycopy(this.objects, index, tmp,index+1, this.size - index);
                    this.objects = tmp;
                }
                this.objects[index] = object;
            }
            this.size++;
        }
    }

    @Override
    public void add(Object object){
        this.add(object, this.size);
    }

    @Override
    public Object getValue(int index){
        if (index >= this.size)
            System.out.println("Index is not in range");
        return this.objects[index];
    }

    @Override
    public int sizeOf(){
        return this.size;
    }

    @Override
    public Object remove(int index){
        Object tmp = this.objects[index];
        this.objects[index] = null;
        System.arraycopy(this.objects, index + 1, this.objects,index, this.size - index);
        this.size--;
        return tmp;
    }

    @Override
    public void push(Object object){
        this.add(object, 0);
    }

    @Override
    public Object pop(){
        if (this.size == 0)
            System.out.println("Stack is empty");
        return remove(0);
    }

    @Override
    public Object peek(){
        if (this.size == 0)
            System.out.println("Stack is empty");
        return this.objects[0];
    }

    @Override
    public Object poll(){
        if (this.size == 0)
            System.out.println("Queue is empty");
        return remove(0);
    }

}
