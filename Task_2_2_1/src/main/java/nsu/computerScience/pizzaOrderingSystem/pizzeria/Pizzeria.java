package nsu.computerScience.pizzaOrderingSystem.pizzeria;

import com.fasterxml.jackson.databind.ObjectMapper;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.model.DeliveryAcknowlegement;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.model.Pizza;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.model.Order;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

public class Pizzeria {
    private final AtomicInteger activeOrders = new AtomicInteger(0);
    private final SynchronizedQueue<Order> orders;
    private final SynchronizedQueue<DeliveryAcknowlegement> ackQueue;
    private final WorkerManager<Order, Pizza> bakerManager;
    private final WorkerManager<Pizza, DeliveryAcknowlegement> deliveryManager;
    private final Thread pizzeriaThread;
    private boolean running;

    Pizzeria(PizzeriaOptions options) {
        orders = new SynchronizedQueue<>(options.getOrderQueueSize());
        SynchronizedQueue<Pizza> pizzaStorage = new SynchronizedQueue<>(options.getStorageSize());
        ackQueue = new SynchronizedQueue<>(options.getAckQueueSize());
        bakerManager = new WorkerManager<>(orders, pizzaStorage, options.getBakers());
        deliveryManager = new WorkerManager<>(pizzaStorage, ackQueue, options.getDeliverymen());

        pizzeriaThread = new Thread(this::run);
    }

    public static Pizzeria fromConfigFile(InputStream src) throws IOException {
        var objectMapper = new ObjectMapper();
        var config = objectMapper.readValue(src, PizzeriaOptions.class);
        return new Pizzeria(config);
    }


    public void createOrder(String name, String address) throws InterruptedException {
        if (!running) throw new IllegalStateException("Pizzeria is closed, cannot create an order");
        activeOrders.incrementAndGet();
        orders.add(new Order(name, address));
        System.out.printf("Accepted order %s%n", name);

    }

    public void stop() throws InterruptedException {
        running = false;
        pizzeriaThread.join();
    }

    public void start() {
        running = true;
        pizzeriaThread.start();
    }

    private void run() {
        bakerManager.start();
        deliveryManager.start();

        while (running || activeOrders.get() > 0) {
            try {
                ackQueue.remove();
                activeOrders.decrementAndGet();
            } catch (InterruptedException ignored) {
            }
        }

        bakerManager.shutdown();
        deliveryManager.shutdown();

    }
}
