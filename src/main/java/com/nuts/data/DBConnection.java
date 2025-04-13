package com.nuts.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASS");

        System.out.println("ENV DEBUG START:");
        System.out.println("DB_USER = " + user);
        System.out.println("DB_PASS = " + pass);
        System.out.println("DB_URL = " + url);
        System.out.println("ENV DEBUG END");

        System.out.println("Connecting to DB: " + url);
        return DriverManager.getConnection(url, user, pass);
    }
}
