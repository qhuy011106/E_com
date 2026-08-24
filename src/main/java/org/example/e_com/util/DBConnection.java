package org.example.e_com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/E_com";
    private static final String username = "root";
    private static final String password = "0111";


    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }
}
