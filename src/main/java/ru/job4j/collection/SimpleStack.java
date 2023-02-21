package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    private SimpleStack.Node<T> head;

    public T pop() {
        return deleteFirst();
    }

    public void push(T value) {
        addFirst(value);
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<T>(value, null);
        if (head != null) {
            newNode.next = head;
        }
        head = newNode;
    }
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> oldHead = head;
        head = head.next;
        oldHead.next = null;
        oldHead.item = null;
        return oldHead.item;
    }
    private static class Node<T> {
        private T item;
        private SimpleStack.Node<T> next;

        Node(T element, SimpleStack.Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
