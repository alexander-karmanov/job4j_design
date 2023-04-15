package ru.job4j.exam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindNumbers {
    public void calcSumQuadratic(int number, int[] data) {
        System.out.println("");
        System.out.println("O(n2) complexity:");
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if ((data[i] + data[j]) == number) {
                    System.out.println("Pair found " + data[i] + ", " + data[j]);
                }
            }
        }
    }

    public int[] calcSumLinear(int number, int[] data) {
        System.out.println("");
        System.out.println("O(n) complexity: ");
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            if (map.containsKey(number - data[i])) {
                System.out.println("index = " + i + ", " + map.get(number - data[i]));
                return new int[] {i, map.get(number - data[i])};

            }
            map.put(data[i], i);
        }
        return null;
    }

    public void calcSumLogarithmic(int number, int[] data)  {
        System.out.println("");
        System.out.println("O(log n) complexity:");
        Arrays.sort(data);
        int low = 0;
        int high = data.length - 1;
        while (low < high) {
            if (data[low] + data[high] == number) {
                System.out.println("Pair found " + data[low] + ", " + data[high]);
            }
            if (data[low] + data[high] < number) {
                low++;
            } else {
                high--;
            }
        }
    }

    public static void main(String[] args) {
        int number = 3;
        int[] data = {0, 2, 3, 5, 7, 2, 7, 5, 0, 8, 3};
        FindNumbers fn = new FindNumbers();
        fn.calcSumQuadratic(number, data);
        fn.calcSumLinear(number, data);
        fn.calcSumLogarithmic(number, data);
    }
}
