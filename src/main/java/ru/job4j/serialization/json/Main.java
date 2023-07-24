package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.json.Computer;

public class Main {
    public static void main(String[] args) {
        final ru.job4j.serialization.json.Computer computer = new ru.job4j.serialization.json.Computer(true, 4680, new OperatingSystem("21H1", 19043),
                new String[] {"64-bit", "4 Gb RAM"});
        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        gson.toJson(computer);
        System.out.println(gson.toJson(computer));

        /* Создаём новую json-строку с модифицированными данными*/
        final String computerJson =
                "{"
                        + "\"working\":true,"
                        + "\"model\":5100,"
                        + "\"system\":"
                        + "{"
                        + "\"version\":\"21H1\","
                        + "\"build\":20144"
                        + "},"
                        + "\"properties\":"
                        + "[\"64-bit\",\"4 Gb RAM\"]"
                        + "}";
        final ru.job4j.serialization.json.Computer computerMod = gson.fromJson(computerJson, Computer.class);
        System.out.println(computerMod);

    }

}
