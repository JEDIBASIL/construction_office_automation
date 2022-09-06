package com.example.construction_office_automation.controller;

import com.example.construction_office_automation.model.Admin;
import com.example.construction_office_automation.model.database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupController {

    @FXML

//  ERROR LABELS
    private Label usernameError,emailError,passwordError,confirmPasswordError;

    @FXML

//  TEXT FIELDS
    private TextField username,emailField;

    @FXML

//  PASSWORD FIELDS
    private PasswordField passwordField,confirmPasswordField;

DatabaseConnection databaseConnection  = new DatabaseConnection();

    public void addAdmin(Admin admin){
        if(databaseConnection.dbConnect()){
            String ADD_ADMIN = "INSERT INTO admin (username,email,employee_id,role,password) VALUES(?,?,?,?,?)";
            int upd =0;
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(ADD_ADMIN);
                preparedStatement.setString(1,admin.getUsername());
                preparedStatement.setString(2,admin.getEmail());
                preparedStatement.setString(3,admin.getId());
                preparedStatement.setString(4,admin.getRole());
                preparedStatement.setString(5,admin.getPassword());
                upd = preparedStatement.executeUpdate();
                if(upd != 0) System.out.println("admin added successfully");
            }catch (SQLException | SecurityException se){
                se.printStackTrace();
            }
        }
    }

    public void signUp(){

    }

}
