package ru.job4j.io.duplicates;

import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> base = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        if (!base.containsKey(fileProperty)) {
            base.put(fileProperty, new ArrayList<>());
        }
        base.get(fileProperty).add(file);
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getDuplicates() {

        List<Path> result = new ArrayList<>();

         for (FileProperty key: base.keySet()) {
            if (base.get(key).size() >= 2) {
                System.out.println(base.get(key));
                /* result.add(Path.of(base.get(key).toString())); */
            }
        }
        return result;
    }
}
