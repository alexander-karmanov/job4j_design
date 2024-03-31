package ru.job4j.ood.srp.example3;

import ru.job4j.ood.srp.example2.User;

import java.util.List;

public class SimpleFoodDelivery implements FoodDelivery {

    private List<Order> orders;

    @Override
    public void receiveOrder(Order order) {
        orders.add(order);
    }

    @Override
    public double calculatePrice(double price, int quantity) {
        return price * quantity;
    }
}
