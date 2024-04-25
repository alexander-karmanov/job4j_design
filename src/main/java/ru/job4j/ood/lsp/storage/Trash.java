package ru.job4j.ood.lsp.storage;

public class Trash extends AbstractStore {
    @Override
    public boolean distribute(Food food) {
        boolean rsl = false;
        double lifeTime = food.expiredTimeInPercents();
        if (lifeTime > 100) {
            getExistingOrCreateFoodList(food).add(food);
            rsl = true;
        }
        return rsl;
    }
}
