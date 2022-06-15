package nsu.computerScience.pizzaOrderingSystem;

import nsu.computerScience.pizzaOrderingSystem.pizzeria.Pizzeria;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        var configStream = Main.class.getClassLoader().getResourceAsStream("config.json");
        var pizzeria = Pizzeria.fromConfigFile(configStream);

        var scanner = new Scanner(System.in);

        System.out.println("Enter min Time between Orders :");
        var minTime = scanner.nextInt();
        System.out.println("Enter max Time between Orders:");
        var maxTime = scanner.nextInt();
        System.out.println("Enter amount:");
        var count = scanner.nextInt();

        var rng = new Random();


        pizzeria.start();

        for (int i = 0; i < count; i++) {
            var delayTime = rng.nextInt(maxTime + 1 - minTime) + minTime;
            Thread.sleep(delayTime * 1000L);
            pizzeria.createOrder("Order#%d".formatted(i), "Pirogova, 1");
        }

        System.out.println("Stooped generating orders");
        pizzeria.stop();
    }
}
