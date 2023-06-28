package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    static String[] filt = {};
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

            StringJoiner sj = new StringJoiner(",");
            while (reader.ready()) {
                line = reader.readLine();
                var scanner = new Scanner(line).useDelimiter(delimiter);
                int idx = 0;
                while (scanner.hasNext()) {
                     String word = scanner.next();
                     for (int i = 0; i < filt.length; i++) {
                        if (i == idx) {
                            sj.add(word);
                        }
                    }
                    idx++;
                }
                sj.add(System.lineSeparator());
            }

            if (argsName.get("out") != "stdout") {
                console(filt, sj);
            } else {
                toFile(filt, sj, argsName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void console(String[] filt, StringJoiner sj) {
        System.out.println(Arrays.toString(filt));
        System.out.println(sj);
    }

    public static void toFile(String[] filt, StringJoiner sj, ArgsName argsName) throws Exception {
        System.out.println("here write to file");
        /* File file = Path.of("./target.txt").toFile(); */

        String file = (argsName.get("out"));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
               System.out.println("writing a file");
               writer.write(Arrays.toString(filt) + System.lineSeparator());
               writer.write(String.valueOf(sj));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
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
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }
}
