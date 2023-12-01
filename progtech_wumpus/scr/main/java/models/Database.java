package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

interface IDatabaseConstants {

    String DB_URL = "jdbc:mysql://localhost:3306/progtech_wumpus";
    String DB_USER = "root";
    String DB_PASSWORD = "";
}

public class Database implements IDatabaseConstants {

    private Connection connection;

    public Database() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error at creating database connection!");
            System.out.println(e.toString());
            System.exit(0);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
