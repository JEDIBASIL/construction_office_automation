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
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
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
    @FXML
    private VBox loginAlert;

    AdminController adminController = new AdminController();

    @FXML

//    LOGIN USERNAME TEXT FIELD

    private TextField loginusernamField;

    @FXML

//    LOGIN USERNAME TEXT FIELD

    private PasswordField loginPasswordField;



    @FXML

//   ERROR LABELS

    private Label loginUsernameError,loginPasswordError,alertText;

    @FXML
     private Button loginBtn;

    DatabaseConnection databaseConnection = new DatabaseConnection();
    Session session = new Session();
    EncryptPassword encryptPassword = new EncryptPassword();
    DecryptPassword decryptPassword = new DecryptPassword();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loginAlert.setVisible(false);
        loginBtn.setOnAction(e->{
           onLogin(e);
        });
    }

    public  void switchSence(ActionEvent event, String fxml,Session session1){

        try{
            FXMLLoader pane = new FXMLLoader(HelloApplication.class.getResource(fxml));
            stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(pane.load(), 1200, 700);
            AdminController adminController1 = pane.getController();
            adminController1.getLoggedUser(session1);
            stage.setScene(scene);
            if(fxml.equals("admin-dashboard.fxml")){
                stage.setResizable(true);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML

    protected  boolean onLogin(ActionEvent event){
        Validator validator = new Validator();
        Session session = new Session();
        session.setUsername(validator.validateTextFields(loginUsernameError,loginusernamField,NAME.toString(),"Username",null));
        session.setPassword(validator.validatePasswordFields(loginPasswordError,loginPasswordField,null,"Password",null));

        if(session.isLoggedIn()){
           if(confirmUsername(session)){
               loginAlert.setVisible(true);
               loginAlert.setVisible(true);
               alertText.setText("Verified");
               if(session.getRole() != null) switchSence(event,"admin-dashboard.fxml",session);
               return true;
           }else {
               loginAlert.setVisible(true);
               alertText.setText("Password or Username is incorrect");
               loginAlert.getStyleClass().add("error-alert");
               return false;
           }
        }

        return false;
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
                   session.setId(resultSet.getString("employee_id"));
                   session.setEmail(resultSet.getString("email"));
                    return true;
                }
            }catch (SecurityException | SQLException se){
                se.printStackTrace();
            }
        }
        return false;
    }
}
