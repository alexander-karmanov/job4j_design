package ru.job4j.ood.dip.example2;

import java.sql.Connection;

public class PostgreSqlConnection {
    private Connection connection;
    private String driver = "org.postgresql.Driver";

    public PostgreSqlConnection(Connection connection) {
        this.connection = connection;
    }

    public void connect() {
        System.out.println("Connect");
    }
}
