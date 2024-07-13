package ru.job4j.algo.sort;

import java.util.Comparator;
import java.util.List;

public class QuickList {
    public static <T> void quickSort(List<T> sequence, Comparator<T> comparator) {
        quickSort(sequence, 0, sequence.size() - 1, comparator);
    }

    private static <T> void quickSort(List<T> sequence, int start, int end, Comparator<T> comparator) {
        if (start < end) {
            int h = breakPartition(sequence, start, end, comparator);
            quickSort(sequence, start, h - 1, comparator);
            quickSort(sequence, h, end, comparator);
        }
    }

    private static <T> int breakPartition(List<T> sequence, int start, int end, Comparator<T> comparator) {
        T pivot = sequence.get(start);
        int beginToEnd = start;
        int endToBegin = end;

        while (beginToEnd <= endToBegin) {
            while (comparator.compare(sequence.get(beginToEnd), pivot) < 0) {
                beginToEnd++;
            }
            while (comparator.compare(sequence.get(endToBegin), pivot) > 0) {
                endToBegin--;
            }
            if (beginToEnd <= endToBegin) {
                swap(sequence, beginToEnd++, endToBegin--);
            }
        }
        return beginToEnd;
    }

    private static <T> void swap(List<T> array, int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}
