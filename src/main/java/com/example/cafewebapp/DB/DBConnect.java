package com.example.cafewebapp.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    public static Connection connection;
    public static Connection getConn(){
        try {
            if(connection == null){
                Class.forName("org.postgresql.Driver");
                connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/EPAM-training","postgres","4444");

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
