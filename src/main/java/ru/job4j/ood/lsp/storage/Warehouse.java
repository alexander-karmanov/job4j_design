package ru.job4j.ood.lsp.storage;

public class Warehouse extends AbstractStore {
    @Override
    public boolean distribute(Food food) {
        boolean rsl = false;
        double lifeTime = food.expiredTimeInPercents();
        if (lifeTime >= 0 && lifeTime < 25) {
            getExistingOrCreateFoodList(food).add(food);
            rsl = true;
        }
        return rsl;
    }
}
