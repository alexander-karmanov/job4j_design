package ru.job4j.algo.sort;

import java.util.Arrays;

public class Merge {
    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        int indexLeft = 0;
        int indexRight = 0;

        while (indexLeft < left.length && indexRight < right.length) {
            if (left[indexLeft] <= right[indexRight]) {
                result[i++] = left[indexLeft++];
            } else {
                result[i++] = right[indexRight++];
            }
        }
        while (indexLeft < left.length) {
            result[i++] = left[indexLeft++];
        }
        while (indexRight < right.length) {
            result[i++] = right[indexRight++];
        }
        return result;
    }
}
