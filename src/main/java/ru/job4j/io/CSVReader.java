package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

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

            System.out.println(Arrays.toString(filt));

            while (reader.ready()) {
                line = reader.readLine();
                var scanner = new Scanner(line).useDelimiter(delimiter);
                int idx = 0;
                StringJoiner sj = new StringJoiner(",");

                while (scanner.hasNext()) {
                    /* for (int i = 0; i < filt.length; i++) {
                        //if (i == idx) {
                            //sj.add(scanner.next());
                       // }
                    } */
                    System.out.print(scanner.next());
                    idx++;
                }
                /* System.out.print(sj); */
                System.out.print(System.lineSeparator());
            }
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
