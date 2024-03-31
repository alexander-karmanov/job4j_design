package ru.job4j.ood.srp.example3;

public interface FoodDelivery {
    void receiveOrder(Order order);
    double calculatePrice(double price, int quantity);
}
