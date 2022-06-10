package nsu.computerScience.pizzaOrderingSystem.pizzeria.worker;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.Order;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.model.Pizza;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Baker extends Worker<Order, Pizza> {
    int cookTime;

    @Override
    public int maxOrdersAtTime() {
        return 1;
    }

    @Override
    public List<Pizza> process(List<Order> items) throws InterruptedException {
        if (items.size() != 1) throw new IllegalArgumentException("Baker can only process one order at a time");
        var order = items.get(0);
        log("started cooking order " + order.name);
        Thread.sleep(cookTime * 1000L);
        log("finished cooking order " + order.name);
        return List.of(new Pizza(order.name, order.address));
    }
}
