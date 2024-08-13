package ru.job4j.algo.sliding.window;

public class Event implements Comparable<Event> {
    int time;
    boolean isStart;

    Event(int time, boolean isStart) {
        this.time = time;
        this.isStart = isStart;
    }

    @Override
    public int compareTo(Event other) {
        if (this.time == other.time) {
            /* Start events come before end events if they are at the same time */
            return this.isStart ? -1 : 1;
        }
        return Integer.compare(this.time, other.time);
    }
}
