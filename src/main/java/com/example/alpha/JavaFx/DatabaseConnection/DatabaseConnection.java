package com.example.alpha.JavaFx.DatabaseConnection;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DatabaseConnection{
    static Connection connection;
    public static Connection getConnection() throws SQLException {
//        String username = "sa";
//        String password = "123443215";
//        String url = "jdbc:sqlserver://Douma\\SQLEXPRESS:1433;databaseName=QuanLySinhVien;encrypt=false";
//        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
