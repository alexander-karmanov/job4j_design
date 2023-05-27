package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        ArgsName argsName = new ArgsName();
        /* for (File file : sources) {
            packSingleFile(file, target);
        } */
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

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(new String[]{args[0], args[1], args[2]});
        Path dir = Path.of(argsName.get("d"));
        String ext = argsName.get("e");
        List<Path> list = SearchFiles.search(dir, p -> !p.toFile().getName().endsWith(ext));
    }
}
