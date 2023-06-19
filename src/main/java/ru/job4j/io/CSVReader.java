package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        List<String> title = new ArrayList<>();
        String file = (argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String filter = (argsName.get("filter"));

        List<String> filt = new ArrayList<>(Stream.of(filter.split(delimiter)).toList());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            var line = reader.readLine();
            var scanner = new Scanner(new ByteArrayInputStream(line.getBytes()))
                    .useDelimiter(delimiter);
            while (scanner.hasNext()) {
                title.add(scanner.next());
            }

            /* результат сопоставления первой строки с фильтром */
            title.retainAll(filt);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        Path in = Path.of(argsName.get("path")).toAbsolutePath();
        String delimiter = argsName.get("delimiter");
        Path out = Path.of(argsName.get("out"));
        String filter = (argsName.get("filter"));

        if (!Files.exists(in)) {
            throw new IllegalArgumentException("Source file not found");
        }
        if (!delimiter.equals(";") && !delimiter.equals(",")) {
            throw new IllegalArgumentException("Wrong delimiter");
        }
        /* if (!Files.exists(out)) {
            throw new IllegalArgumentException("Target file not found");
        } */

        if (filter.isBlank()) {
            throw new IllegalArgumentException("Filter not set");
        }
        handle(argsName);
    }
}
