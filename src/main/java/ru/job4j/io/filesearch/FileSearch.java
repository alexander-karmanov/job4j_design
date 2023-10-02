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
import ru.job4j.io.SearchFiles;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileSearch {

    public static void perform(ArgsName argsName)  throws IOException {

        String rootDir = argsName.get("d");
        String mask = argsName.get("n");
        String type = argsName.get("t");
        String out = argsName.get("o");

        if ("name".equals(type)) {
            System.out.println("Search using filename");
            /* -d=C:\\Tools\ -n="README.txt" -t=name -o=log.txt */
            Predicate<Path> condition = p -> p.toFile().getName().contains(mask);
            saveResult(rootDir, condition, out);
        }

        if ("regex".equals(type))  {
            System.out.println("Search using regular expression");
            /* -d=C:\\Tools\ -n="\w+.(txt|zip)$" -t=regex -o=log.txt */
            String regex = mask;
            Pattern pattern = Pattern.compile(regex);
            Predicate<Path> condition = p -> pattern.matcher(p.toFile().getName()).matches();
                    saveResult(rootDir, condition, out);
        }

        if ("mask".equals(type))  {
            System.out.println("Search using mask");
            /* -d=C:\\Tools\ -n=*.?xt -t=mask -o=log.txt */
            String regex = mask.replace(".", "[.]")
                               .replace("*", ".*")
                               .replace("?", ".");
            Pattern pattern = Pattern.compile(regex);
            Predicate<Path> condition = p -> pattern.matcher(p.toFile().getName()).matches();
            saveResult(rootDir, condition, out);
        }
    }

    private static void validate(ArgsName argsName) throws IOException {
        Path dir = Path.of(argsName.get("d"));
        String name = argsName.get("n");
        String type = argsName.get("t");
        String out = argsName.get("o");
        if (!dir.toFile().isDirectory()) {
            throw new IllegalArgumentException("Wrong directory");
        }
        if (!name.contains(".") || name.length() < 3) {
            throw new IllegalArgumentException("Wrong extension");
        }
        if (!"name".equals(type) && !"regex".equals(type) && !"mask".equals(type)) {
            throw new IllegalArgumentException("Wrong type of search");
        }
        if (!out.contains(".") || out.length() < 3) {
            throw new IllegalArgumentException("Wrong out file");
        }
    }

    private static void saveResult(String rootDir, Predicate<Path> condition, String out) throws IOException {
        List<Path> result = SearchFiles.search(Path.of(rootDir), condition);
         for (Path el : result) {
            System.out.println("found file  = " + el);
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
        validate(argsName);
        perform(argsName);
    }
}
