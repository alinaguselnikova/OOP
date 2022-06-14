package nsu.computerScience.pizzaOrderingSystem.pizzeria.worker;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.model.DeliveryAcknowlegement;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.model.Pizza;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Deliveryman extends Worker<Pizza, DeliveryAcknowlegement> {
    private int deliveryTime;
    private int trunkSize;

    @Override
    public int maxOrdersAtTime() {
        return trunkSize;
    }

    @Override
    public List<DeliveryAcknowlegement> process(List<Pizza> items) throws InterruptedException {
        int timeInField = 0;
        if (items.size() < 1 || items.size() > trunkSize) throw new IllegalArgumentException();
        var result = new ArrayList<DeliveryAcknowlegement>(items.size());

        log("Got %d pizzas".formatted(items.size()));
        for (var pizza : items) {
            Thread.sleep(deliveryTime * 1000L);
            timeInField += deliveryTime;
            log("Delivered %s to %s".formatted(pizza.getName(), pizza.getAddress()));
            result.add(new DeliveryAcknowlegement(timeInField));
        }
        Thread.sleep(deliveryTime * 1000L);
        log("returned to pizzeria");
        return result;
    }
}
