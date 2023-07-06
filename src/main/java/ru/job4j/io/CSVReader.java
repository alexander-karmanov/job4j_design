package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    static StringJoiner joiner = new StringJoiner(";");
    static List<String> list = new ArrayList<>();
    public static void handle(ArgsName argsName) throws Exception {
        String file = (argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String filter = (argsName.get("filter"));

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            var line = reader.readLine();
            String[] first = line.split(delimiter);
            String[] filt = filter.split(",");
            int[] temp = new int[filt.length];

            for (int i = 0; i < first.length; i++) {
                for (int j = 0; j < filt.length; j++) {
                    if (first[i].equals(filt[j])) {
                        temp[j] = i;
                        break;
                    }
                }
            }

            while (reader.ready()) {
                StringJoiner sj = new StringJoiner(delimiter);
                line = reader.readLine();
                var scanner = new Scanner(line).useDelimiter(delimiter);
                int idx = 0;

                while (scanner.hasNext()) {
                     String word = scanner.next();
                     for (int i = 0; i < temp.length; i++) {
                        if (temp[i] == idx) {
                            sj.add(word);
                        }
                    }
                    idx++;
                }
                list.add(sj.toString());
            }

            if ("stdout".equals(argsName.get("out"))) {
                console(filt, list);
            } else {
                toFile(filt, list, argsName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void console(String[] filt, List<String> list) {
        Arrays.stream(filt).forEach(joiner::add);
        System.out.println(joiner);
        list.forEach(System.out::println);
    }

    public static void toFile(String[] filt, List<String> list, ArgsName argsName) throws Exception {
        Arrays.stream(filt).forEach(joiner::add);
        String file = (argsName.get("out"));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
               writer.write(joiner.toString() + System.lineSeparator());
            for (String el : list) {
                writer.write(el);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        Path in = Path.of(argsName.get("path")).toAbsolutePath();
        String delimiter = argsName.get("delimiter");
        String filter = (argsName.get("filter"));
        if (!Files.exists(in)) {
            throw new IllegalArgumentException("Source file not found");
        }
        if (!delimiter.equals(";") && !delimiter.equals(",")) {
            throw new IllegalArgumentException("Wrong delimiter");
        }
        if (!argsName.get("out").endsWith(".csv") && !"stdout".equals(argsName.get("out"))) {
            throw new IllegalArgumentException("Wrong output");
        }
        if (filter.isBlank()) {
            throw new IllegalArgumentException("Filter not set");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }
}
