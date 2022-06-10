package nsu.computerScience.stackImplementation;


import java.util.*;


public class Stack<T> implements Iterable<T> {
     private T[] stackArray;
     private int size;
     private int last = 0;

     Stack(int size) {
         this.size = size;
         stackArray = (T[]) new Object[size];
     }

     Stack(){
         this(20);
     }

    public void push(T element) {
        if (this.last >= stackArray.length) {
            T[] stackPush = (T[]) new Object[size * 2];
            size *= 2;
            stackPush = Arrays.copyOf(stackArray, size);
            stackArray = stackPush;
        }
        stackArray[last++] = element;
    }

    public T pop() {
        if (size <= 0) {
            throw new IndexOutOfBoundsException();
        } else {
            last--;
            T element = stackArray[last];
            stackArray[last] = null;
            return element;
        }
    }

    public void pushStack(Stack<T> pushEx) {
        if (pushEx == null) {
            throw new IllegalArgumentException("Trying to push null stack.");
        }

        int len = pushEx.last;
        if (len <= 0) {
            return;
        }
        if (size + len >= stackArray.length) {
            size += pushEx.last + 1;
            T[] stackPush = (T[]) new Object[size];
            stackPush = Arrays.copyOf(stackArray, size);
            stackArray = stackPush;
        }
        System.arraycopy(pushEx.stackArray, 0, this.stackArray, last, len);
        last += len;


    }

    public Stack<T> popStack(int n) {
        if (n > last)
            throw new IndexOutOfBoundsException();
        Stack<T> fin = new Stack<>();
        T[] add = (T[]) new Object[this.last + 1];
        for (int i = 0; i < n; i++) {
            add[i] = this.pop();
        }
        for (int i = n - 1; i >= 0; i--) {
            fin.push(add[i]);
        }
        return fin;
    }

    public boolean isEmpty() {
        return last == 0;
    }

    public int count() {
        return stackArray.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }
    class StackIterator implements Iterator<T>{
        private int position =  0;
        @Override
        public boolean hasNext() {
            return !(position == last);
        }

        @Override
        public T next() throws NoSuchElementException {
            return stackArray[position++] ;
        }
    }
}
