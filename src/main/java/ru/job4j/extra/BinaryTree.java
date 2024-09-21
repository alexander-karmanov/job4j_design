package ru.job4j.extra;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    TreeNode root;

    // Вставка
    public void insert(Worker worker) {
        root = insertRec(root, worker);
    }

    private TreeNode insertRec(TreeNode root, Worker worker) {
        if (root == null) {
            return new TreeNode(worker);
        }
        if (worker.id < root.worker.id) {
            root.left = insertRec(root.left, worker);
        } else {
            root.right = insertRec(root.right, worker);
        }
        return root;
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
