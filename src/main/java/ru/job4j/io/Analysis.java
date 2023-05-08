package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean flag = true;
            while (reader.ready()) {
                String status = reader.readLine();
                if ((flag) == (status.startsWith("400") || status.startsWith("500"))) {
                    writer.append(status.split(" ")[1]).append(";").append("\n");
                    flag = false;

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
