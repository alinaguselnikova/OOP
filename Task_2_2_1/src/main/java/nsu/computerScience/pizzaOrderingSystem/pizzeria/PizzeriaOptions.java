package nsu.computerScience.pizzaOrderingSystem.pizzeria;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.worker.Baker;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.worker.Deliveryman;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PizzeriaOptions {
    Integer orderQueueSize;
    Integer storageSize;
    Baker[] bakers;
    Deliveryman[] deliverymen;
}
