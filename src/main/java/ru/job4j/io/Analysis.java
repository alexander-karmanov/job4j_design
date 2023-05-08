package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String flag = null;
            while (reader.ready()) {
                String status = reader.readLine();
                if (flag == null && (status.startsWith("400") || status.startsWith("500"))) {
                    writer.write(status.split(" ")[1]);
                    writer.append(";");
                    flag = status;
                } else if (flag != null && (!status.startsWith("400") || !status.startsWith("500"))) {
                    writer.write(status.split(" ")[1]);
                    writer.append(";");
                    flag = null;
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
