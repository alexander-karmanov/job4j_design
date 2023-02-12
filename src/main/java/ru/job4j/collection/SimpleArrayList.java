package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            expandArray();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        get(index);
        T tmp = container[index];
        container[index] = newValue;
        return tmp;
    }

    @Override
    public T remove(int index) {
        get(index);
        T tmp = container[index];
        System.arraycopy(container, index  + 1, container, index, container.length - index - 1);
        container[size - 1] = null;
        size--;
        modCount++;
        return tmp;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }

    public void expandArray() {
        if (size == 0) {
            container = Arrays.copyOf(container, container.length + 1);
        }
        container = Arrays.copyOf(container, container.length * 2);
    }
}
