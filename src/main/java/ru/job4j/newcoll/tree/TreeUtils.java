package ru.job4j.newcoll.tree;

import java.util.*;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            count++;
            queue.addAll(current.getChildren());
        }
        return count;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        List<T> result = new ArrayList<>();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            queue.addAll(current.getChildren());
            result.add(current.getValue());
        }
        return result;
    }

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     * @param root корень дерева
     * @param parent ключ узла-родителя
     * @param child ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        boolean result = false;
        Optional<Node<T>> prnt = findByKey(root, parent);
        Optional<Node<T>> chld = findByKey(root, child);
        if (prnt.isPresent()) {
            if (chld.isEmpty()) {
                Node<T> newChild = new Node<>(child);
                prnt.get().getChildren().add(newChild);
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Optional<Node<T>> result = Optional.empty();
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> element = stack.pop();
            if (element.getValue().equals(key)) {
                result = Optional.of(element);
            }
            stack.addAll(element.getChildren());
        }
        return result;
    }


    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        Optional<Node<T>> result = Optional.empty();
        if (root == null) {
            throw new IllegalArgumentException("Node root is empty");
        }
        if (root.getValue() == key) {
            result = Optional.of(root);
        }
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> parent = stack.pop();
            List<Node<T>> children = parent.getChildren();
            if (children.size() > 0) {
                for (Node<T> child : children) {
                    T childValue = child.getValue();
                        if (childValue != null && childValue == key) {
                            result = Optional.of(child);
                            parent.setChildren(children.stream().filter(c -> !c.equals(child)).toList());
                            break;
                        }
                    stack.push(child);
                }
            }
        }
        return result;
    }
}
