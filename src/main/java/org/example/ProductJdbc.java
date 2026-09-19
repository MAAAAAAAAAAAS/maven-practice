package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductJdbc {
    public static void main(String[] args) throws SQLException {
        // 1. Подключение к H2
        String url = "jdbc:h2:./testdb";
        String user = "sa";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Подключение успешно!");

            try (Statement statement = connection.createStatement()) {
                statement.execute("DROP TABLE IF EXISTS product");
                statement.execute("CREATE TABLE IF NOT EXISTS product (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(100), " +
                        "price DOUBLE)");
                System.out.println("Таблица создана!");
            }

            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO product (name, price) VALUES (?, ?)")) {
                ps.setString(1, "Телефон");
                ps.setDouble(2, 80000);
                ps.executeUpdate();

                ps.setString(1, "Микроволновка");
                ps.setDouble(2, 10000);
                ps.executeUpdate();

                ps.setString(1, "Стол");
                ps.setDouble(2, 12000);
                ps.executeUpdate();

                System.out.println("Данные добавлены!");
            }

            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT * FROM product")) {
                System.out.println("Продукты:");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    System.out.println(id + ": " + name + ", " + price);
                }
            }

            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT * FROM product WHERE price > 15000")) {
                System.out.println("Продукты с ценой больше 15000:");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    System.out.println(id + ": " + name + ", " + price);
                }
            }

            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT * FROM product ORDER BY price")) {
                System.out.println("Продукты, отсортированные по цене:");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    System.out.println(id + ": " + name + ", " + price);
                }
            }

            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT AVG(price) AS avg_price FROM product")) {
                if (rs.next()) {
                    double avgPrice = rs.getDouble("avg_price");
                    System.out.println("Средняя цена: " + avgPrice);
                }
            }

            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS total FROM product")) {
                if (rs.next()) {
                    int count = rs.getInt("total");
                    System.out.println("Кол-во продуктов: " + count);
                }
            }

            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT * FROM product ORDER BY price DESC LIMIT 1")) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    System.out.println(id + ": " + name + ", " + price);
                }
            }
            addProduct(connection,"Наушники", 5000);

            //try (PreparedStatement ps = connection.prepareStatement(
            //        "DELETE FROM product WHERE price < 15000")) {
            //    int deleted = ps.executeUpdate();
            //    System.out.println("Удалено строк: " + deleted);
            //}

            try (PreparedStatement ps = connection.prepareStatement(
                    "UPDATE product SET price = price * 1.1")) {
                int updated = ps.executeUpdate();
                System.out.println("Обновлено строк: " + updated);
            }

            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT * FROM product")) {
                System.out.println("Продукты после всех изменений:");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ": " +
                            rs.getString("name") + ", " + rs.getDouble("price"));
                }
            }

            System.out.println(findProductsCheaperThan(connection, 12000));
        }
    }
    public static void addProduct(Connection connection, String name, double price) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO product (name, price) VALUES (?, ?)")) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.executeUpdate();

            System.out.println("Продукт добавлен: " + name + ", " + price);
        }
    }

    public static List<String> findProductsCheaperThan(Connection connection, double maxPrice) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM product WHERE price < ?")) {
            ps.setDouble(1, maxPrice);
            try (ResultSet rs = ps.executeQuery()) {
                List<String> result = new ArrayList<>();
                while (rs.next()) {
                    String name = rs.getString("name");
                    result.add(name);
                }
                return result;
            }
        }
    }
}
