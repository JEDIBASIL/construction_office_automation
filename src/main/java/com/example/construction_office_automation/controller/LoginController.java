package com.example.construction_office_automation.controller;

import com.example.construction_office_automation.HelloApplication;
import com.example.construction_office_automation.controller.validator.Validator;
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

//    LOGIN USERNAME TEXT FIELD

    private ChoiceBox loginRole;

    @FXML

//   ERROR LABELS

    private Label loginUsernameError,loginRoleError,loginPasswordError;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginRole.getItems().addAll("Project manager","Project director","Project  executive");
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
        Validator validator = new Validator();
        Session session = new Session();
        session.setUsername(validator.validateTextFields(loginUsernameError,loginusernamField,NAME.toString(),"Username",null));
        session.setRole(validator.validateChoiceBox(loginRoleError,loginRole,"Role"));
        session.setPassword(validator.validatePasswordFields(loginPasswordError,loginPasswordField,null,"Password",null));

        if(session.isLoggedIn()){
            if(session.getRole() != null && session.getRole().equals("Project director")) switchSence(event,"admin-dashboard.fxml");
            if(session.getRole() != null && session.getRole().equals("Project manager")) switchSence(event,"project-manger.fxml");
        }

    }

}
