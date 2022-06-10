package nsu.computerScience.pizzaOrderingSystem.pizzeria;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static java.lang.Math.min;

public class SynchronizedQueue<T> {
    private final int maxSize;
    private final Queue<T> orders;

    public SynchronizedQueue(int maxSize) {
        this.maxSize = maxSize;
        this.orders = new ArrayDeque<>(maxSize);
    }

    public synchronized void add(T element) throws InterruptedException {
        while (orders.size() >= maxSize) {
            wait();
        }
        orders.add(element);
        this.notifyAll();
    }

    synchronized boolean canRemove() {
        return !orders.isEmpty();
    }

    public synchronized T remove() throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        T result = orders.poll();
        notifyAll();
        return result;
    }

    public synchronized List<T> removeMany(int n) throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        int toTake = min(orders.size(), n);
        var result = new ArrayList<T>(toTake);
        for (int i = 0; i < toTake; i++) {
            result.add(orders.remove());
        }
        notifyAll();
        return result;
    }

}
