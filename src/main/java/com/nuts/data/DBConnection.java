package com.nuts.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    /** Opens a JDBC connection to the given URL/user/pass */
    public static Connection getConnection(String url, String user, String pass)
            throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
