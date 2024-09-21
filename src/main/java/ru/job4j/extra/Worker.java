package ru.job4j.extra;

public class Worker {
    int id;
    String name;
    Worker manager;

    public Worker(int id, String name, Worker manager) {
        this.id = id;
        this.name = name;
        this.manager = manager;
    }

    public Worker getManager() {
        return manager;
    }

    @Override
    public String toString() {
        return name;
    }
}
