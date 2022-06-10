package nsu.computerScience.pizzaOrderingSystem.pizzeria;

import nsu.computerScience.pizzaOrderingSystem.pizzeria.worker.Worker;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerManager<T, R> {
    private final SynchronizedQueue<T> input;
    private final SynchronizedQueue<R> output;
    private final List<Worker<T, R>> workers;
    private final ExecutorService executor;


    public WorkerManager(SynchronizedQueue<T> input, SynchronizedQueue<R> output, Worker<T, R>[] workers) {
        this.input = input;
        this.output = output;
        this.workers = Arrays.asList(workers);
        executor = Executors.newFixedThreadPool(this.workers.size());
    }

    void start() {
        workers.forEach((worker) -> executor.submit(() -> {
            while (!Thread.interrupted()) {
                try {
                    var items = input.removeMany(worker.maxOrdersAtTime());
                    var results = worker.process(items);
                    for (var result : results) {
                        output.add(result);
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
        }));
    }


    void shutdown() {
        executor.shutdownNow();
    }

}
