package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.AbstractParking;
import ru.job4j.ood.lsp.parking.PassengerCar;
import ru.job4j.ood.lsp.parking.Truck;
import static org.assertj.core.api.Assertions.*;

public class AbstractParkingTest {

    @Test
    public void whenPark2PassengerCarsAnd2TrucksThenNoMoreParking() {
        PassengerCar passCar1 = new PassengerCar();
        PassengerCar passCar2 = new PassengerCar();
        PassengerCar passCar3 = new PassengerCar();
        Truck truck1 = new Truck(2);
        Truck truck2 = new Truck(2);
        AbstractParking parking = new AbstractParking(2, 2);
        assertThat(parking.park(passCar1)).isTrue();
        assertThat(parking.park(passCar2)).isTrue();
        assertThat(parking.park(truck1)).isTrue();
        assertThat(parking.park(truck2)).isTrue();
        assertThat(parking.park(passCar3)).isFalse();
    }

    @Test
    public void whenPlaceFor1PassengerCarThenTruckImpossibleToPark() {
        PassengerCar passCar1 = new PassengerCar();
        Truck truck1 = new Truck(2);
        AbstractParking parking = new AbstractParking(1, 0);
        assertThat(parking.park(passCar1)).isTrue();
        assertThat(parking.park(truck1)).isFalse();
    }

    @Test
    public void when2PassengerCarPlacesEmptyThenCanParkOnly1TruckWithSize2() {
        PassengerCar passCar1 = new PassengerCar();
        Truck truck1 = new Truck(2);
        AbstractParking parking = new AbstractParking(2, 0);
        assertThat(parking.park(truck1)).isTrue();
        assertThat(parking.park(passCar1)).isFalse();
    }

    @Test
    public void when2PassengerCarPlacesEmptyThenTruckWithSizeMore2ImpossibleToPark() {
        AbstractParking parking = new AbstractParking(2, 0);
        Truck truck3 = new Truck(3);
        assertThat(parking.park(truck3)).isFalse();
    }

    @Test
    public void whenOnlyTruckPlacesThenPassengerCarImpossibleToPark() {
        PassengerCar passCar1 = new PassengerCar();
        AbstractParking parking = new AbstractParking(0, 2);
        assertThat(parking.park(passCar1)).isFalse();
    }
}
