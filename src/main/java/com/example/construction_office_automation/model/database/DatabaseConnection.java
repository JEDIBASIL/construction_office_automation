package com.example.construction_office_automation.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    static Connection connection;

    static Statement statement;

    final String jdbcUrl = "jdbc:h2:./construction_office_automation";

    public boolean dbConnect(){
        try{
            connection = DriverManager.getConnection(jdbcUrl,"","panda");
            System.out.println("connecting to database...");
            return true;
        }catch (SQLException se){
            se.printStackTrace();
            return false;
        }
    }
}
