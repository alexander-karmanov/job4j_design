package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E>  {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    private Node<E> tail;

    @Override
    public void add(E value) {
        Node<E> l = tail;
        Node<E> newNode = new Node<>(value, head);
        tail = newNode;
            if (l == null) {
                head = newNode;
            } else {
                l.next = newNode;
            }
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = head;
            for (int i = 0; i < index; i++) {
                rsl = rsl.next;
            }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return head.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E current = head.item;
                head = head.next;
                next();
                return current;
            }
        };
    }

    private static class Node<E> {
        final E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
