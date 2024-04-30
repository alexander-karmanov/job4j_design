package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Parking {
    boolean park(Auto auto);

    List<Auto> getParkPlace();
}
