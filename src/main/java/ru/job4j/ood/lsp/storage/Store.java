package ru.job4j.ood.lsp.storage;

import java.util.List;

public interface Store {
    boolean distribute(Food food);

    List<Food> getFood(String name);

}
