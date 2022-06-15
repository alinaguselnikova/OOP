package nsu.computerScience.pizzaOrderingSystem.pizzeria.model;

public class Pizza {
    private final String name;
    private final String address;

    public Pizza(String name, String address) {
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
