package ru.job4j.ood.srp.example1;

public class SimpleCalculator implements Calculator {

    @Override
    public double calculate(double num1, double num2, char operation) {
        double result = 0;

        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                System.out.println("Операция не распознана. Повторите ввод.");
        }

        return result;
    }

    @Override
    public void print(double number) {
        System.out.println(number);
    }
}
