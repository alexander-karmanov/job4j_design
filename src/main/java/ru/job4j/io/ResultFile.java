package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            for (int row = 1; row < 11; row++) {
                for (int cell = 1; cell < 11; cell++) {
                    String s = Integer.toString(row * cell);
                    out.write(s.getBytes());
                    out.write(" ".getBytes());
                    if (cell == 10) {
                        out.write(System.lineSeparator().getBytes());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
