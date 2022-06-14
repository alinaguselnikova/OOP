package nsu.computerScience.pizzaOrderingSystem.pizzeria.worker;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.model.Order;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.model.Pizza;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Baker extends Worker<Order, Pizza> {
   private int cookTime;

    @Override
    public int maxOrdersAtTime() {
        return 1;
    }

    @Override
    public List<Pizza> process(List<Order> items) throws InterruptedException {
        if (items.size() != 1) throw new IllegalArgumentException("Baker can only process one order at a time");
        var order = items.get(0);
        log("started cooking order " + order.getName());
        Thread.sleep(cookTime * 1000L);
        log("finished cooking order " + order.getName());
        return List.of(new Pizza(order.getName(), order.getAddress()));
    }
}
