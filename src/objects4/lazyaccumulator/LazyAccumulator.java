package objects4.lazyaccumulator;

import objects4.accumulator.accumulator.Operation;
import objects4.arraylist.LinkedList;
import objects4.arraylist.List;

/**
 * Created by evami on 26.10.17.
 */
public class LazyAccumulator {

    private int value;
    private List list;

    public static class Node{
        public Operation op;
        public int a;

        public Node(int a, Operation operation) {
            this.op = operation;
            this.a = a;
        }
    }

    public LazyAccumulator(int value, List list) {
        this.list = list;
        this.value = value;
    }

    public void add(int a, Operation operation){
        this.list.add(new Node(a, operation));
    }

    public int calculate(){
        for (Object i: this.list){
            this.value = ((Node)i).op.doOperation(this.value, ((Node)i).a);
        }
        return this.value;
    }

    public static void main(String[] args) {
        List list = new LinkedList();
        LazyAccumulator acc = new LazyAccumulator(0,list);
        acc.add(1, new Operation() {
            @Override
            public int doOperation(int a, int b) {
                return a + b;
            }
        });
        acc.add(3, new Operation() {
            @Override
            public int doOperation(int a, int b) {
                return a - b;
            }
        });
        int result = acc.calculate();
        System.out.println(result);
    }
}
