package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> storages;

    public ControlQuality(List<Store> storages) {
        this.storages = storages;
    }

    public void distribute(Food food) {
        for (Store storage : storages) {
            if (storage.distribute(food)) {
                break;
            }
        }
    }

    public void resort() {
        List<Food> products = new ArrayList<>();
        for (Store store : storages) {
            for (Food food : products) {
                products.addAll(store.getFood(food.getName()));
            }
        }
        for (Food food : products) {
            distribute(food);
        }
    }
}
