package ru.job4j.io.filesearch;
/*
1. Создать программу для поиска файлов. Все классы, относящиеся к этому заданию должны быть в отдельном пакете
Важно! Допускается использование ранее созданных вами классов.
2. Программа должна искать данные в заданном каталоге и подкаталогах.
3. Имя файла может задаваться, целиком, по маске, по регулярному выражению(не обязательно).
4. Программа должна запускаться с параметрами, например:  -d=c:  -n=*.?xt -t=mask -o=log.txt
Ключи
-d - директория, в которой начинать поиск.
-n - имя файла, маска, либо регулярное выражение.
-t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
-o - результат записать в файл.
5. Параметры в программу должны передаваться в командной строке.
6. Программа должна записывать результат в файл.
7. В программе должна быть валидация ключей и подсказка.
*/

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {

    private static List<Path> result = new ArrayList<>();
    public static void perform(ArgsName argsName)  throws IOException {

        System.out.println("Searching all files according to criteria");
        /* -d=C: -n=*.?xt -t=mask -o=log.txt  */

        String rootDir = argsName.get("d");
        String mask = argsName.get("n");
        String type = argsName.get("t");
        String out = argsName.get("o");

        System.out.println("rootDir = " + rootDir);
        System.out.println("out = " + out);

         try {
            Files.walk(Paths.get(rootDir))
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        if (file.toFile().isFile() && file.toFile().getPath().contains(".txt")) {
                            result.add(file);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

         try (BufferedWriter writer = new BufferedWriter(new FileWriter(out))) {
            for (Path el : result) {
                writer.write(el.toString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
        e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        perform(argsName);
    }
}
