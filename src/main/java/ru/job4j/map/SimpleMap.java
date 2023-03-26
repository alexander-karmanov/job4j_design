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

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
            modCount++;
        }
        int index = indexCalc(key);

        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int indexCalc(K key) {
        return (key == null) ? 0 : indexFor(hash(key.hashCode()));
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> capacity);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        for (int index = 0; index < table.length; index++) {
            MapEntry<K, V> entry = table[index];
            if (indexFor(hash(table[index].key.hashCode())) > capacity * LOAD_FACTOR) {
                int indexNew = indexFor(hash(newTable.hashCode()));
                newTable[indexNew] = entry;
                table[index] = null;
                modCount++;
            }
        }
    }

    @Override
    public V get(K key) {
        int indexFound = (key == null) ? 0 : indexFor(hash(key.hashCode()));
        V result = null;
        if (key != null && key.hashCode() == table[indexFound].key.hashCode()
                && table[indexFound].key.equals(key)) {
                result = table[indexFound].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int indexFound = (key == null) ? 0 : indexFor(hash((key.hashCode())));
        if (table[indexFound] != null && key != null
            && key.hashCode() == table[indexFound].key.hashCode()
            && table[indexFound].key.equals(key)) {
            table[indexFound] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
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
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
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
