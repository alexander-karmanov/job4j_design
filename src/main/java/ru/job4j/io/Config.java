package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(s -> !s.startsWith("#") && !s.isEmpty())
                    .map((s -> s.split("=", 2)))
                    .filter(this::checkLine)
                    .forEach(k -> values.put(k[0], k[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkLine(String[] str) {
        if (str.length != 2 || str[0].isBlank() || str[1].isBlank()) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        System.out.println(new Config("data/app.properties"));
    }
}
