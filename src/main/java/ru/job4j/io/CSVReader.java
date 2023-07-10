package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {

        StringJoiner joiner = new StringJoiner(argsName.get("delimiter"));
        List<String> list = new ArrayList<>();
        String delimiter = argsName.get("delimiter");
        String file = (argsName.get("path"));
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

            String[] array2 = new String[temp.length];

            while (reader.ready()) {
                StringJoiner sj = new StringJoiner(delimiter);
                line = reader.readLine();
                var scanner = new Scanner(line).useDelimiter(delimiter);
                int idx = 0;

                while (scanner.hasNext()) {
                    String word = scanner.next();
                    String[] array = word.split(delimiter);

                    for (int i = 0; i < array.length; i++) {
                        for (int j = 0; j < temp.length; j++) {
                            /* System.out.println("idx = " + idx + ", array[j] = " + array2[i] + ", j = " + j + ", temp[i] = " + temp[i] + ", i = " + i); */
                            if (temp[j] == idx) {
                                array2[j] = array[i];
                                sj.add(array2[j]);
                            }
                         }
                    }
                    idx++;
                }
                list.add(sj.toString());
            }

            System.out.println(Arrays.toString(array2));

            if ("stdout".equals(argsName.get("out"))) {
                console(filt, list, joiner);
            } else {
                toFile(filt, list, argsName, joiner);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void console(String[] filt, List<String> list, StringJoiner joiner) {
        Arrays.stream(filt).forEach(joiner::add);
        System.out.println(joiner);
        list.forEach(System.out::println);
    }

    public static void toFile(String[] filt, List<String> list, ArgsName argsName, StringJoiner joiner) throws Exception {
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
