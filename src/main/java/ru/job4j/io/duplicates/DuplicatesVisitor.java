package ru.job4j.io.duplicates;

import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Path> files = new HashMap<>();
    private List<FileProperty> duplicates = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        if (!files.containsKey(fileProperty)) {
            duplicates.add(fileProperty);
        } else {
            files.put(fileProperty, file);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<FileProperty> getDuplicates() {
        return duplicates;
    }
}
