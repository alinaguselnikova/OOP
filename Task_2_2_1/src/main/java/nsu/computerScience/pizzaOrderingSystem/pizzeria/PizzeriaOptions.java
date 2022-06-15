package nsu.computerScience.pizzaOrderingSystem.pizzeria;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.worker.Baker;
import nsu.computerScience.pizzaOrderingSystem.pizzeria.worker.Deliveryman;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PizzeriaOptions {
    private Integer orderQueueSize;
    private Integer storageSize;
    private Integer ackQueueSize;
    private Baker[] bakers;
    private Deliveryman[] deliverymen;

    public Integer getOrderQueueSize() {
        return orderQueueSize;
    }

    public Integer getStorageSize() {
        return storageSize;
    }

    public Integer getAckQueueSize() {
        return ackQueueSize;
    }

    public Baker[] getBakers() {
        return bakers;
    }

    public Deliveryman[] getDeliverymen() {
        return deliverymen;
    }
}
