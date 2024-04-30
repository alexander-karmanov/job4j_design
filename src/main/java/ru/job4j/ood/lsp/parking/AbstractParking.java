package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class AbstractParking implements Parking {
    private int passengerCarPlaces;
    private int truckPlaces;

    private final List<Auto> parkPlace = new ArrayList<>();

    public AbstractParking(int passengerCarPlaces, int truckPlaces) {
        this.passengerCarPlaces = passengerCarPlaces;
        this.truckPlaces = truckPlaces;
    }

    @Override
    public List<Auto> getParkPlace() {
        return parkPlace;
    }

    @Override
    public boolean park(Auto auto) {
        if (auto.getSize() == 1 && passengerCarPlaces > 0) {
            getParkPlace().add(auto);
            passengerCarPlaces--;
            return true;
        }
        if (auto.getSize() > 1) {
            if (truckPlaces > 0) {
                getParkPlace().add(auto);
                truckPlaces--;
                return true;
            } else if (auto.getSize() <= passengerCarPlaces) {
                getParkPlace().add(auto);
                passengerCarPlaces -= auto.getSize();
                return true;
            }
        }
        return false;
    }
}
