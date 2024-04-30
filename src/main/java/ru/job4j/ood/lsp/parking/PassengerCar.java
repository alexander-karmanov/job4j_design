package ru.job4j.ood.lsp.parking;

public class PassengerCar extends Auto {
    private int size;

    public PassengerCar() {
        this.size = 1;
    }

    @Override
    public int getSize() {
        return size;
    }
}
