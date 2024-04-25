package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.storage.*;

import java.time.LocalDate;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;


public class ControlQualityTest {
    @Test
    public void whenDistributeToWarehouse() {
        Warehouse warehouse = new Warehouse();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, new Shop(), new Trash()));
        LocalDate now = LocalDate.now();
        Food milk = new Milk("milk", now.minusDays(1), now.plusDays(10), 120);
        controlQuality.distribute(milk);
        assertThat(warehouse.getFood(milk.getName()).get(0)).isEqualTo(milk);
    }

    @Test
    public void whenDistributeToShop() {
        Shop shop = new Shop();
        ControlQuality controlQuality = new ControlQuality(List.of(new Warehouse(), shop, new Trash()));
        LocalDate now = LocalDate.now();
        Food butter = new Butter("butter", now.minusDays(10), now.plusDays(10), 90);
        controlQuality.distribute(butter);
        assertThat(shop.getFood(butter.getName()).get(0)).isEqualTo(butter);
    }

    @Test
    public void whenDistributeToShopAndMakeDiscount() {
        Shop shop = new Shop();
        ControlQuality controlQuality = new ControlQuality(List.of(new Warehouse(), shop, new Trash()));
        LocalDate now = LocalDate.now();
        Food juice = new Juice("juice", now.minusDays(90), now.plusDays(10), 100);
        controlQuality.distribute(juice);
        assertEquals(20, (int) shop.getFood(juice.getName()).get(0).getDiscount());
    }

    @Test
    public void distributeInTrash() {
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(new Warehouse(), new Shop(), trash));
        LocalDate now = LocalDate.now();
        Food bread = new Bread("bread", now.minusDays(10), now.minusDays(1), 60);
        controlQuality.distribute(bread);
        assertThat(trash.getFood(bread.getName()).get(0)).isEqualTo(bread);
    }
}
