package nsu.computerScience.pizzaOrderingSystem.pizzeria.model;

public class Order {
    private final String name;
    private final String address;

    public Order(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
