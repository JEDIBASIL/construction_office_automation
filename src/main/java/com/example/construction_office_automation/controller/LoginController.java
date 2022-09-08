package com.example.construction_office_automation.controller;

import com.example.construction_office_automation.HelloApplication;
import com.example.construction_office_automation.controller.validator.Validator;
import com.example.construction_office_automation.model.database.DatabaseConnection;
import com.example.construction_office_automation.security.DecryptPassword;
import com.example.construction_office_automation.security.EncryptPassword;
import com.example.construction_office_automation.utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static com.example.construction_office_automation.enums.Validation.NAME;


public class LoginController implements Initializable {

    private Stage stage;

    private Scene scene;

    AdminController adminController = new AdminController();

    @FXML

//    LOGIN USERNAME TEXT FIELD

    private TextField loginusernamField;

    @FXML

//    LOGIN USERNAME TEXT FIELD

    private PasswordField loginPasswordField;



    @FXML

//   ERROR LABELS

    private Label loginUsernameError,loginPasswordError;


    DatabaseConnection databaseConnection = new DatabaseConnection();
    Session session = new Session();
    EncryptPassword encryptPassword = new EncryptPassword();
    DecryptPassword decryptPassword = new DecryptPassword();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public  void switchSence(ActionEvent event, String fxml){

        try{
            Parent pane = FXMLLoader.load(HelloApplication.class.getResource(fxml));
            stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(pane, 1200, 700);
            stage.setScene(scene);
            if(fxml.equals("admin-dashboard.fxml")){
                stage.setResizable(true);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML

    protected  void onLoginClicked(ActionEvent event){
        System.out.println("clicked");
        System.out.println(decryptPassword.DecryptPassword("ĐĉĘĘġĕč"));
        Validator validator = new Validator();
        Session session = new Session();
        session.setUsername(validator.validateTextFields(loginUsernameError,loginusernamField,NAME.toString(),"Username",null));
        session.setPassword(validator.validatePasswordFields(loginPasswordError,loginPasswordField,null,"Password",null));

        if(session.isLoggedIn()){
            System.out.println(session.getUsername());
            System.out.println(session.getPassword());
           if(confirmUsername(session)){
               System.out.println("Found");
               System.out.println(session.getRole());
               if(session.getRole() != null && session.getRole().equals("Project executive")) switchSence(event,"admin-dashboard.fxml");
               if(session.getRole() != null && session.getRole().equals("Project director")) switchSence(event,"project-manager.fxml");
               if(session.getRole() != null && session.getRole().equals("Project manager")) switchSence(event,"project-manger.fxml");
           }else System.out.println("Not found");
        }

    }

    public boolean confirmUsername(Session session){
        if(databaseConnection.dbConnect()){
            String CONFIRM_USERNAME = "SELECT * FROM admin WHERE  username = ? AND password = ?";
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(CONFIRM_USERNAME);
                preparedStatement.setString(1,session.getUsername());
                preparedStatement.setString(2,encryptPassword.EncryptPassword(session.getPassword()));
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                   session.setRole( resultSet.getString("role"));
                    return true;
                }
            }catch (SecurityException | SQLException se){
                se.printStackTrace();
            }
        }
        return false;
    }
}
