package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        list.add(index, value);
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        list.add(index + 1, value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            boolean result = filter.test(iterator.next());
            if (result) {
                iterator.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            boolean result = filter.test(iterator.next());
            if (result) {
                iterator.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements, Predicate<T> predicate) {
            for (int i = 0; i < elements.size(); i++) {
            list.removeIf(predicate);
            }
    }
}
