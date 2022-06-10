package nsu.computerScience.pizzaOrderingSystem.pizzeria.worker;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.model.DeliveryAcknowlegement;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.model.Pizza;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Deliveryman extends Worker<Pizza, DeliveryAcknowlegement> {
    int deliveryTime;
    int trunkSize;

    @Override
    public int maxOrdersAtTime() {
        return trunkSize;
    }

    @Override
    public List<DeliveryAcknowlegement> process(List<Pizza> items) throws InterruptedException {
        if (items.size() < 1 || items.size() > trunkSize) throw new IllegalArgumentException();
        log("Got %d pizzas".formatted(items.size()));
        for (var pizza : items) {
            Thread.sleep(deliveryTime * 1000L);
            log("Delivered %s to %s".formatted(pizza.name, pizza.address));
        }
        Thread.sleep(deliveryTime * 1000L);
        log("returned to pizzeria");
        return items.stream().map(it -> new DeliveryAcknowlegement()).toList();
    }
}
