package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {

        Map<String, List<String>> map  = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        String file = (argsName.get("path"));

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            String[] input = line.split(System.lineSeparator());
            String[] title = input[0].split(";");

            for (String s : title) {
                if (!map.containsKey(s)) {
                    map.put(s, new ArrayList<>());
                }
            }
            while (line != null) {
                line = reader.readLine();
                list.add(line);
            }
            list.remove(null);

            System.out.println(map);
            System.out.println(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) throws IOException {
        Path path = Path.of(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        Path out = Path.of(argsName.get("out"));
        String filter = (argsName.get("filter"));

        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Source file not found");
        }
        if (!delimiter.equals(";")) {
            throw new IllegalArgumentException("Wrong delimiter");
        }
        if (!Files.exists(out)) {
            throw new IllegalArgumentException("Target file not found");
        }
        if (filter.isBlank()) {
            throw new IllegalArgumentException("Filter not set");
        }
    }

    public static void main(String[] args) throws Exception {
        File file = new File("source.csv");
        File target = new File("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=" + target.getAbsolutePath(), "-filter=name,education"});
        validate(argsName);
        handle(argsName);
    }
}
