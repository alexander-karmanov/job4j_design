package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int threshold = (int) (table.length * LOAD_FACTOR);

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= threshold) {
            expand();
            modCount++;
        }
        int index = (key == null) ? 0 : indexFor(hash(key.hashCode()));

        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> capacity);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {

        if (count == (capacity * LOAD_FACTOR)) {
            capacity *= 2;
            table = Arrays.copyOf(table, capacity);
        }
        modCount++;
    }

    @Override
    public V get(K key) {
        int hCode = indexFor(hash(key.hashCode()));
        if (table[hCode] != null
            && table[hCode].key.equals(key)) {
            return table[hCode].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int index = (key == null) ? 0 : indexFor(hash((key.hashCode())));
        if (table[index] == null) {
            return false;
        }
        table[index] = null;
        count--;
        modCount++;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
