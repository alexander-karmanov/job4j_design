package ru.job4j.ood.lsp.storage;

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
}
