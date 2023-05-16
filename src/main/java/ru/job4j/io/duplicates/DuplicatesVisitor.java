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
    private Map<FileProperty, List<Path>> base = new HashMap<>();

    private Map<Path, FileProperty> result = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        if (!base.containsKey(fileProperty)) {
            base.put(fileProperty, new ArrayList<>());
        } else {
             result.put(file, fileProperty);
        }
        return FileVisitResult.CONTINUE;
    }

    public Map<Path, FileProperty> getDuplicates() {
        return result;
    }
}