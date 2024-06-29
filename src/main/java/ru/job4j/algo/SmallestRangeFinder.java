package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        int counter = 1;
        int[] result = new int[2];
        int left = 0;
        int right = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (counter != k) {
                right++;
                if (nums[i] < nums[right]) {
                    counter++;
                } else {
                    left = right;
                    counter = 1;
                }
            }
        }
        result[0] = left;
        result[1] = right;
        result = counter == k ? result : null;
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
