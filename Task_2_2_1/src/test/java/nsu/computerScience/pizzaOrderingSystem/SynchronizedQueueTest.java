package nsu.computerScience.pizzaOrderingSystem;

import nsu.computerScience.pizzaOrderingSystem.pizzeria.SynchronizedQueue;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SynchronizedQueueTest {
    @Test
    void testQueue() throws InterruptedException {
        var pool = Executors.newCachedThreadPool();
        var cs = new ExecutorCompletionService<Void>(pool);
        var queue = new SynchronizedQueue<Integer>(10000);
        boolean[] gotNumbers = new boolean[10000];
        for (int i = 0; i < 10; i++) {
            final int currentI = i;
            cs.submit(() -> {
                for (int j = currentI * 1000; j < (currentI + 1) * 1000; j++) {
                    try {
                        queue.add(j);
                    } catch (InterruptedException ignored) {
                    }
                }
                return null;
            });
            cs.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    synchronized (gotNumbers) {
                        try {
                            var num = queue.remove();
                            if (gotNumbers[num]) {
                                throw new AssertionFailedError("Got one number twice");
                            }
                            gotNumbers[num] = true;

                        } catch (InterruptedException e) {
                            throw new AssertionFailedError("Interrupted");
                        }
                    }
                }
                return null;
            });
        }
        for (int i = 0; i < 20; i++) {
            cs.take();
        }
        for (var b : gotNumbers) {
            assertTrue(b);
        }

    }

}