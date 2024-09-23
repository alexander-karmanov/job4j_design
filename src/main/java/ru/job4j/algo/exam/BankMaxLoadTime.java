package ru.job4j.algo.exam;

import java.util.Arrays;
import java.util.List;

public class BankMaxLoadTime {
    public static int[] findMaxLoadTime(List<int[]> visitTimes) {
        if (visitTimes.size() == 0) {
            return new int[]{-1, -1};
        } else if (visitTimes.size() == 1) {
            return new int[]{visitTimes.get(0)[0], visitTimes.get(0)[1]};
        }

        Event[] sortedEvents = new Event[visitTimes.size() * 2];
        int j = 0;
        for (int[] visit : visitTimes) {
            sortedEvents[j++] = new Event(visit[0], EventType.ARRIVAL);
            sortedEvents[j++] = new Event(visit[1], EventType.DEPARTURE);
        }
        Arrays.sort(sortedEvents, Event::compareTo);
        return getMaxIntersectionsCount(sortedEvents);
    }

    private static int[] getMaxIntersectionsCount(Event[] events) {
        int[] rsl = new int[]{-1, -1};
        int count = 0;
        int max = 0;
        int min = 0;
        for (Event event : events) {
            if (event.type == EventType.ARRIVAL) {
                count++;
                if (count > max) {
                    max = count;
                    rsl[0] = event.time;
                    rsl[1] = event.time + 1;
                } else if (count == max && min > rsl[1] - rsl[0]) {
                    rsl[1] = event.time;
                    min = rsl[1] - rsl[0];
                }
            } else {
                count--;
                if (count == max && min > rsl[1] - rsl[0]) {
                    rsl[1] = event.time;
                    min = rsl[1] - rsl[0];
                }
            }
        }
        return rsl;
    }

    static class Event implements Comparable<Event> {
        int time;
        EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time == other.time) {
                return this.type == EventType.ARRIVAL ? -1 : 1;
            }
            return Integer.compare(this.time, other.time);
        }
    }

    enum EventType {
        ARRIVAL, DEPARTURE
    }
}
