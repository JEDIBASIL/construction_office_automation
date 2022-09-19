package com.example.construction_office_automation.controller;

import com.example.construction_office_automation.HelloApplication;
import com.example.construction_office_automation.controller.validator.Validator;
import com.example.construction_office_automation.model.Admin;
import com.example.construction_office_automation.model.database.DatabaseConnection;
import com.example.construction_office_automation.security.EncryptPassword;
import com.example.construction_office_automation.utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static com.example.construction_office_automation.enums.Validation.*;

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

    public boolean addAdmin(Admin admin){
        if(databaseConnection.dbConnect()){
            String ADD_ADMIN = "INSERT INTO admin (username,email,employee_id,role,password,status) VALUES(?,?,?,?,?,?)";
            int upd =0;
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(ADD_ADMIN);
                preparedStatement.setString(1,admin.getUsername());
                preparedStatement.setString(2,admin.getEmail());
                preparedStatement.setString(3,admin.getId());
                preparedStatement.setString(4,admin.getRole());
                preparedStatement.setString(5,admin.getPassword());
                preparedStatement.setString(6,admin.getStatus());
                upd = preparedStatement.executeUpdate();
                if(upd != 0) return true;
            }catch (SQLException | SecurityException se){
                se.printStackTrace();
            }
        }
        return false;
    }

    public void signUp(ActionEvent event){
        File file = new File(".panda.txt");
        Admin admin = new Admin();
        Validator validator = new Validator();
        EncryptPassword encryptPassword = new EncryptPassword();

        admin.setUsername(validator.validateTextFields(usernameError,username,NAME.toString(),"Username",null));
        admin.setEmail(validator.validateTextFields(emailError,emailField,EMAIL.toString(),"Email",null));
        admin.setPassword(encryptPassword.EncryptPassword(validator.validatePasswordFields(passwordError,passwordField,null,"Password",null)));
        admin.setPassword(encryptPassword.EncryptPassword(validator.validatePasswordFields(confirmPasswordError,confirmPasswordField,passwordField,"Password",CONFIRMATION.toString())));
        admin.setStatus("Active");
        admin.setRole("Project executive");
        admin.setId("10001");
        if(admin.validateFields())
            if(addAdmin(admin)) {
                switchSence(event,"sign-in.fxml");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                        file.setReadOnly();
                    } catch (IOException ie) {
                        ie.printStackTrace();
                    }
                }
            }

    }

    public  void switchSence(ActionEvent event, String fxml){

        try{
            FXMLLoader pane = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(pane.load(), 1200, 700);
            stage.setScene(scene);
            if(fxml.equals("admin-dashboard.fxml")){
                stage.setResizable(true);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected  void onSignupClicked(ActionEvent event){
        signUp(event);
    }

}
