package ru.job4j.serialization.json;

public class OperatingSystem {
    private final String version;
    private final int build;

    public OperatingSystem(String version, int build) {
        this.version = version;
        this.build = build;
    }

    public String getVersion() {
        return version;
    }

    public int getBuild() {
        return build;
    }

    @Override
    public String toString() {
        return "OperatingSystem{"
                + "version='" + version + '\''
                + ", build=" + build
                + '}';
    }
}
