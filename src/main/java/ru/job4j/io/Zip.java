package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
            zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toString()))) {
                   zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) throws IOException {
        Path dir = Path.of(argsName.get("d"));
        String ext = argsName.get("e");
        String archive = argsName.get("o");

        if (!dir.toFile().isDirectory()) {
            throw new IllegalArgumentException("Wrong directory");
        }

        if (ext.length() <= 1) {
            throw new IllegalArgumentException("Wrong extension");
        }

        if (archive.length() <= 4) {
            throw new IllegalArgumentException("Wrong archive name");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Error: wrong number of arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        Path dir = Path.of(argsName.get("d"));
        String ext = argsName.get("e");
        List<Path> sources = SearchFiles.search(dir, p -> !p.toFile().getName().endsWith(ext));
        Zip zip = new Zip();
        zip.packFiles(sources, new File(argsName.get("o")));
    }
}
