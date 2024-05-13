package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {

    private static final int ADD_ROOT_ITEM = 1;
    private static final int ADD_CHILD_ITEM = 2;
    private static final int SHOW_ITEMS = 3;
    private static final int EXIT = 4;
    private static final String TASK_MSG = "Введите основное задание:";
    private static final String TASK_NUMBER_MSG = "Введите имя задания для добавление в него подзадания:";
    private static final String INNER_TASK_MSG = "Введите вложенное задание:";
    private static final String CREATED_SAVE_MSG = "Задание создано и сохранено";
    private static final String CREATED_FAIL =
            "Не удалось добавить задание. Проверьте корректность названия родительского задания.";
    private static final String ALL_ITEMS_MSG = "Список всех заданий:";

    private static final String EXIT_MSG = "Работа завершена";
    private static final String LS = System.lineSeparator();
    private static final ActionDelegate ACTION_PRINTLN = System.out::println;
    private static final String START_MENU = """
            Меню. Введите номер пункта:
            1. Добавить задание в корень списка
            2. Добавить подзадание
            3. Вывести список всех заданий
            4. Завершить работу программы
            """;

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter menuPrinter = new Printer();
        Scanner scanner = new Scanner(System.in);
        String parentName;
        String childName;
        int userInput = -1;
        while (EXIT != userInput) {
            System.out.println(START_MENU);
            userInput = scanner.nextInt();
            if (ADD_ROOT_ITEM == userInput) {
                System.out.println(TASK_MSG);
                parentName = scanner.next();
                menu.add(Menu.ROOT, parentName, ACTION_PRINTLN);
                System.out.println(CREATED_SAVE_MSG);
                System.out.println(LS);
                continue;
            }

            if (ADD_CHILD_ITEM == userInput) {
                System.out.println(TASK_NUMBER_MSG);
                parentName = scanner.next();
                System.out.println(INNER_TASK_MSG);
                childName = scanner.next();
                boolean isAdded = menu.add(parentName, childName, ACTION_PRINTLN);
                if (!isAdded) {
                    System.out.println(CREATED_FAIL);
                    continue;
                }
                System.out.println(LS);
                System.out.println(CREATED_SAVE_MSG);
                continue;
            }

            if (SHOW_ITEMS == userInput) {
                System.out.println(ALL_ITEMS_MSG);
                menuPrinter.print(menu);
                System.out.println(LS);
            }
        }
        System.out.println(EXIT_MSG);
    }
}
