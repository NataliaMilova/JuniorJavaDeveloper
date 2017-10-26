package objects4.lazyaccumulator;

import objects4.accumulator.accumulator.Operation;
import objects4.arraylist.LinkedList;
import objects4.arraylist.Stack;

/**
 * Created by evami on 26.10.17.
 */
public class LazyAccum {
    private int value;
    private Stack stack;

    public static class Node{
        public Operation op;
        public int a;

        public Node(int a, Operation operation) {
            this.op = operation;
            this.a = a;
        }
    }

    public LazyAccum(int value, Stack stack) {
        this.stack = stack;
        this.value = value;
    }

    public void add(int a, Operation operation){
        this.stack.push(new LazyAccum.Node(a, operation));
    }

    public int calculate(){
        Node tmp;
        while (this.stack.sizeOf() != 0) {
            tmp = (Node)this.stack.pop();
            this.value = tmp.op.doOperation(this.value, tmp.a);
        }
        return this.value;
    }

    public static void main(String[] args) {
        Stack stack = new LinkedList();
        LazyAccum acc = new LazyAccum(2,stack);
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
        System.out.println(acc.calculate());
    }
}
