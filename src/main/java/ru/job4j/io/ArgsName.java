package ru.job4j.io;

import java.util.*;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();
    private static StringBuilder sb = new StringBuilder();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(sb.append("This key: '").append(key).append("' is missing").toString());
        }
        return values.get(key);
    }

    private static void validation(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(sb.append("Error: This argument '").append(arg).append("' does not contain an equal sign").toString());
            }
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(sb.append("Error: This argument '").append(arg).append("' does not start with a '-' character").toString());
            }
            if (arg.startsWith("-=")) {
                throw new IllegalArgumentException(sb.append("Error: This argument '").append(arg).append("' does not contain a key").toString());
            }
            if (arg.indexOf("=") == arg.length() - 1) {
                throw new IllegalArgumentException(
                        String.format(sb.append("Error: This argument '").append(arg).append("' does not contain a value").toString()));
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
