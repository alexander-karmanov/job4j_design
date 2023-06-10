package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> log = readPhrases();
        saveLog(log);
    }

    private List<String> textToList() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            Scanner scanner = new Scanner(reader);
             while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        List<String> list = textToList();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            System.out.println("Введите фразу");
            while (reader.ready()) {
                Scanner input = new Scanner(System.in);
                int random = new Random().nextInt(list.size());
                String phrase = input.nextLine();
                if (OUT.equals(phrase)) {
                    result.add(OUT);
                    System.out.println("Чат закрыт");
                    break;
                }
                boolean stop = true;
                if (STOP.equals(phrase)) {
                    result.add(STOP);
                    while (stop) {
                        phrase = input.nextLine();
                        if (CONTINUE.equals(phrase)) {
                            System.out.println("Продолжаем");
                            stop = false;
                        }
                    }
                }
                String answer = list.get(random);
                    result.add(phrase);
                    result.add(answer);
                    System.out.println(answer);
            }
                } catch (IOException e) {
                e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String s : log) {
                writer.write(s + System.lineSeparator());
            }
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("data/chat.log", "data/answers.txt");
        cc.run();
    }
}
