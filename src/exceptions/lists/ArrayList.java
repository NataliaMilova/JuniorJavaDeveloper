package exceptions.lists;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by evami on 24.10.17.
 */
public class ArrayList<T> implements List<T>, Stack<T>, Queue<T> {
    private T[] objects;
    private int size;
    private int modCount;

    private ArrayList(T[] objects, int size){
        this.objects = (T[]) new Object[size];
        System.arraycopy(objects, 0, this.objects, 0, objects.length);
        this.size = size;
    }

    public ArrayList(){
        this.objects = (T[]) new Object[3];
    }

    public ArrayList(int size){
        this.objects = (T[]) new Object[size];
    }

    private class ArrayIterator<T> implements Iterator{
        private int modCount;
        private int ptr;

        public ArrayIterator() {
            this.ptr = 0;
            this.modCount = ArrayList.this.modCount;
        }

        @Override
        public boolean hasNext(){
            return (this.ptr < ArrayList.this.size);
        }

        @Override
        public T next(){
            if (ArrayList.this.modCount != this.modCount)
                throw new ConcurrentModificationException();
            T tmp = (T) ArrayList.this.objects[ptr];
            this.ptr++;
            return tmp;
        }
    }

    @Override
    public ArrayList clone() {
        return new ArrayList<T>(this.objects, this.size);
    }

    @Override
    public Iterator iterator(){
        return new ArrayIterator<T>();
    }

    @Override
    public void add(T object, int index){
        T[] tmp;
        if (index > this.size)
            System.out.println("Index error");
        else{
            if (this.size + 1 <= this.objects.length)
                System.arraycopy(this.objects, index, this.objects,index + 1, this.size - index);
            else{
                tmp = (T[]) new Object[this.objects.length + 2];
                System.arraycopy(this.objects, 0, tmp,0, index);
                System.arraycopy(this.objects, index, tmp,index + 1, this.size - index);
                this.objects = tmp;
            }
            this.objects[index] = object;
            this.size++;
            this.modCount++;
        }
    }

    @Override
    public void add(T object){
        this.add(object, this.size);
    }

    @Override
    public T getValue(int index){
        if (index >= this.size)
            throw new ListExceptions("ArrayList has not element in index: " + index);
        return this.objects[index];
    }

    @Override
    public int sizeOf(){
        return this.size;
    }

    @Override
    public T remove(int index){
        T tmp = this.objects[index];
        this.objects[index] = null;
        System.arraycopy(this.objects, index + 1, this.objects,index, this.size - index - 1);
        this.size--;
        this.modCount++;
        return tmp;
    }

    @Override
    public void push(T object){
        this.add(object, 0);
    }

    @Override
    public T pop(){
        if (this.size == 0){
            System.out.println("Stack is empty");
            return null;
        }
        return remove(0);
    }

    @Override
    public T peek(){
        if (this.size == 0)
            System.out.println("Stack is empty");
        return this.objects[0];
    }

    @Override
    public T poll(){
        if (this.size == 0){
            System.out.println("Queue is empty");
            return null;
        }
        return remove(0);
    }

}
