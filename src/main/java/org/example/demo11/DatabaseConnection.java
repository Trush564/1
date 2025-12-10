package org.example.demo11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    static final String URL = "jdbc:postgresql://localhost:5432/addressbook";
    static final String USER = "postgres";
    static final String PASSWORD = "7632";  
    

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

