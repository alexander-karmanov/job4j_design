package ru.job4j.ood.dip.example2;

public class Authorization {
    PostgreSqlConnection connection;

    public Authorization(PostgreSqlConnection connection) {
        this.connection = connection;
    }

    public boolean authorize(String login, String password) {
        return false;
    }
}
