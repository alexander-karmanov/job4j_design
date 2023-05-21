package ru.job4j.io;

import java.util.*;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private static void validation(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            if (!arg.contains("=")) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain an equal sign");
            }
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not start with a '-' character");
            }
            if (arg.startsWith("-=")) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a key");
            }
            if (arg.indexOf("=") == arg.length() - 1) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '" + arg + "' does not contain a value"));
            }
        }
    }
    private void parse(String[] args) {
        for (String arg: args) {
            arg = arg.replaceFirst("-", "");
            String[] split = arg.split("=", 2);
            values.put(split[0], split[1]);
        }
        System.out.println(values);
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        validation(args);
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {

        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        System.out.println(jvm.get("request"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
