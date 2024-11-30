package org.openjfx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTesting {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/restaurant?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "toor01";

        try {
            // Optional: Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection successful!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
