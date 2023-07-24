package ru.job4j.serialization.json;


public class Computer {
    private final boolean working;
    private final int model;   /*    4680 */
    private final OperatingSystem system;
    private final String[] properties;

    public Computer(boolean working, int model, OperatingSystem system, String[] properties) {
        this.working = working;
        this.model = model;
        this.system = system;
        this.properties = properties;
    }
}
