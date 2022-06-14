package nsu.computerScience.pizzaOrderingSystem.pizzeria.worker;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class Worker<T, R> {
    protected String name;

    protected void log(String message) {
        System.out.printf("[%s %s] %s%n", getClass().getSimpleName(), name, message);
    }

    public abstract int maxOrdersAtTime();

    public abstract List<R> process(List<T> items) throws InterruptedException;
}
