package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("D:\\java_test"), duplicatesVisitor);
        Set set = duplicatesVisitor.getDuplicates().entrySet();
        for (Object s : set) {
            System.out.println(s);
        }
    }
}
