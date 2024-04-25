package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractStore implements Store {

    private final Map<String, List<Food>> storage = new HashMap<>();

    @Override
    public abstract boolean distribute(Food food);

    @Override
    public List<Food> getFood(String name) {
        return storage.get(name);
    }

    public List<Food> getExistingOrCreateFoodList(Food food) {
        String foodName = food.getName();
        List<Food> foodList = storage.computeIfAbsent(foodName, k -> new ArrayList<>());
        return foodList;
    }
}
