package ru.job4j.kiss.fool;

import java.util.Scanner;
import java.util.function.Predicate;

public class Fool {

    private static Predicate<Integer> divBy3 = n -> (n % 3 == 0);
    private static Predicate<Integer> divBy5 = n -> (n % 5 == 0);
    private static Predicate<Integer> divBy3And5 = divBy3.and(divBy5);

    private String getValue(int i) {
        String value = String.valueOf(i);
        if (divBy3And5.test(i)) {
            value = "FizzBuzz";
        } else if (divBy3.test(i)) {
            value = "Fizz";
        } else if (divBy5.test(i)) {
            value = "Buzz";
        }
        return value;
    }

    private String wrongAnswer(int i, String answer) {
        return null;
    }

    public static void main(String[] args) {
        Fool fool = new Fool();
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(fool.getValue(startAt));
            startAt++;
            var answer = input.nextLine();

            if (divBy3And5.test(startAt)) {
                if (!"FizzBuzz".equals(answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
            } else if (divBy3.test(startAt)) {
                if (!"Fizz".equals(answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
            } else if (divBy5.test(startAt)) {
                if (!"Buzz".equals(answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
            } else {
                if (!String.valueOf(startAt).equals(answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
            }
            startAt++;
        }
    }
}
