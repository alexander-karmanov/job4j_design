package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    private static final String LS = System.lineSeparator();

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelectMenuItem() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Файл", STUB_ACTION);
        menu.add("Файл", "Параметры", STUB_ACTION);
        menu.add("Параметры", "Общие", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Общие", List.of(), STUB_ACTION, "1.1.1."))
                .isEqualTo(menu.select("Общие").get());
    }

    @Test
    public void whenMenuPrinter() {
        Menu menu = new SimpleMenu();
        Printer printer = new Printer();
        menu.add(Menu.ROOT, "Файл", STUB_ACTION);
        menu.add(Menu.ROOT, "Правка", STUB_ACTION);
        menu.add("Файл", "Параметры", STUB_ACTION);
        menu.add("Параметры", "Общие", STUB_ACTION);
        menu.add("Параметры", "Экран", STUB_ACTION);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        printer.print(menu);
        String expected = String.format("%s%s%s%s%s%s%s%s%s%s",
                "1.Файл", LS,
                "    1.1.Параметры", LS,
                "        1.1.1.Общие", LS,
                "        1.1.2.Экран", LS,
                "2.Правка", LS);
        assertThat(expected).isEqualTo(output.toString());
    }
}
