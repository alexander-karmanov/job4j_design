package ru.job4j.algo.sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IntervalMerger {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new LinkedList<>();
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            if (interval[0] <= currentEnd) {
                currentInterval[1] = Math.max(currentEnd, interval[1]);
            } else {
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
