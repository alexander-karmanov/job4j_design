package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {

    private static final String INDENTATION = "    ";
    private static final String SEPARATOR = "\\.";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo itemInfo : menu) {
            System.out.println(INDENTATION.repeat(itemInfo.getNumber().split(SEPARATOR).length - 1)
                    + itemInfo.getNumber() + itemInfo.getName());
        }
    }
}
