package ru.job4j.ood.lsp.storage;

public class Shop extends AbstractStore {

    @Override
    public boolean distribute(Food food) {
        boolean rsl = false;
        double lifeTime = food.expiredTimeInPercents();
        if (lifeTime >= 25 && lifeTime < 100) {
            setDiscountAndPrice(food, lifeTime);
            getExistingOrCreateFoodList(food).add(food);
            rsl = true;
        }
        return rsl;
    }

    private void setDiscountAndPrice(Food food, double lifeTime) {
        if (lifeTime > 75) {
            food.setDiscount(20);
            food.setPrice(food.getPrice() * (100 - food.getDiscount()) / 100.00);
        }
    }
}
