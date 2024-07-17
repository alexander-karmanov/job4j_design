package ru.job4j.newcoll.tree;

import ru.job4j.collection.SimpleQueue;

import java.util.Queue;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        Queue<Node<T>> queue = new SimpleQueue<>();
        /*TODO реализуйте метод.*/
        return 0;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        Queue<Node<T>> queue = new SimpleQueue<>();
        /*TODO реализуйте метод.*/
        return null;
    }
}
