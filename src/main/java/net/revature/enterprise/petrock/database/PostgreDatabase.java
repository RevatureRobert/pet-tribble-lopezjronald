package net.revature.enterprise.petrock.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreDatabase {

    public static final String URL = "jdbc:postgresql://<SomeName>.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=public";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "password";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Unable to connect to the database");
            return null;
        }
    }
}
