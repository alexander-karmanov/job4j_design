package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, IOException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private Connection initConnection() throws IOException, SQLException, ClassNotFoundException {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            String driver = config.getProperty("hibernate.connection.driver_class");
            String url = config.getProperty("hibernate.connection.url");
            String login = config.getProperty("hibernate.connection.username");
            String password = config.getProperty("hibernate.connection.password");
            Class.forName(driver);
            return DriverManager.getConnection(url, login, password);
        }
    }

    private void sqlExecute(String sql) throws SQLException, IOException, ClassNotFoundException {
        connection = initConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS " + tableName + "(%s, %s, %s);",
                    "id SERIAL PRIMARY KEY",
                    "name TEXT",
                    "producer VARCHAR(50)"
        );
        sqlExecute(sql);
        System.out.println(getTableScheme(tableName));
    }

    public void dropTable(String tableName) throws Exception {
        System.out.println("drop table method. table = " + tableName);
        String sql = ("DROP TABLE IF EXISTS " + tableName + ";");
        sqlExecute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        System.out.println("addColumn method. table = " + tableName);
        String sql = "ALTER TABLE " + tableName
                   + " ADD COLUMN " + columnName + " " + type + ";";
        sqlExecute(sql);
        System.out.println(getTableScheme(tableName));
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        System.out.println("dropColumn method. table = " + tableName);
        String sql = "ALTER TABLE " + tableName
                   + " DROP COLUMN " + columnName + ";";
        sqlExecute(sql);
        System.out.println(getTableScheme(tableName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        System.out.println("renameColumn method. table = " + tableName);
        String sql = "ALTER TABLE " + tableName
                   + " RENAME COLUMN " + columnName + " TO " + newColumnName + ";";
        sqlExecute(sql);
        System.out.println(getTableScheme(tableName));
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.dropTable("cars");
        tableEditor.createTable("cars");
        tableEditor.addColumn("cars", "price", "int");
        tableEditor.dropColumn("cars", "producer");
        tableEditor.renameColumn("cars", "name", "model");
    }
}
