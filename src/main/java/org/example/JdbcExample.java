package org.example;

import java.sql.*;

public class JdbcExample {
    public static void main(String[] args) throws SQLException {
        // 1. Подключение к H2
        String url = "jdbc:h2:./testdb";
        String user = "sa";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Подключение успешно!");

            // 2. Создание таблицы
            try (Statement statement = connection.createStatement()) {
                statement.execute("DROP TABLE IF EXISTS users");
                statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(100), " +
                        "age INT)");
                System.out.println("Таблица создана!");
            }

            // 3. Вставка данных
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO users (name, age) VALUES (?, ?)")) {
                ps.setString(1, "Саня");
                ps.setInt(2, 20);
                ps.executeUpdate();

                ps.setString(1, "Саша");
                ps.setInt(2, 22);
                ps.executeUpdate();

                ps.setString(1, "Санёк");
                ps.setInt(2, 25);
                ps.executeUpdate();

                System.out.println("Данные добавлены!");
            }

            // 4. Выборка данных
            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT * FROM users")) {
                System.out.println("Пользователи:");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    System.out.println(id + ": " + name + ", " + age);
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(
                    "UPDATE users SET age = ? WHERE name = ?")) {
                ps.setInt(1,21);
                ps.setString(2,"Саня");
                int updated = ps.executeUpdate();
                System.out.println("Обновлено строк: " + updated);
            }

            try (PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM users WHERE name = ?")) {
                ps.setString(1,"Саша");
                int deleted = ps.executeUpdate();
                System.out.println("Удалено строк: " + deleted);
            }

            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT * FROM users")) {
                System.out.println("Пользователи после всех изменений:");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ": " +
                            rs.getString("name") + ", " + rs.getInt("age"));
                }
            }
        }
    }
}