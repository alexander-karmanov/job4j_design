package ru.job4j.extra;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    TreeNode root;

    // Вставка элемента в дерево
    public void insert(Worker worker) {
        TreeNode newNode = new TreeNode(worker);
        if (root == null) {
            root = newNode;
            return;
        }
        TreeNode current = root;
        TreeNode parent;

        while (true) {
            parent = current;
            current = (worker.id < current.worker.id) ? current.left : current.right;
            if (current == null) {
                if (worker.id < parent.worker.id) {
                    parent.left = newNode;
                } else {
                    parent.right = newNode;
                }
                return;
            }
        }
    }

    // Метод для получения списка начальников
    public List<Worker> getManagers(Worker worker) {
        List<Worker> managersList = new ArrayList<>();
        Worker currentManager = worker.getManager();

        while (currentManager != null) {
            managersList.add(currentManager);
            currentManager = currentManager.getManager();
        }
        return managersList;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Создаем работников
        Worker manager1 = new Worker(1, "Alice", null);
        Worker employee1 = new Worker(2, "Bob", manager1);
        Worker employee2 = new Worker(3, "Charlie", manager1);
        Worker employee3 = new Worker(4, "David", employee1);

        // Добавляем работников в бинарное дерево
        tree.insert(manager1);
        tree.insert(employee1);
        tree.insert(employee2);
        tree.insert(employee3);

        // Получаем список начальников для работника
        List<Worker> managers = tree.getManagers(employee3);
        System.out.println("Менеджеры для " + employee3.name + ": " + managers);
    }
}
