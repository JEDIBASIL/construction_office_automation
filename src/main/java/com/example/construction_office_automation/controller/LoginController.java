package com.example.construction_office_automation.controller;

import com.example.construction_office_automation.HelloApplication;
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




public class LoginController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML

//    LOGIN USERNAME TEXT FIELD

    private TextField LoginusernamField;

    @FXML

//    LOGIN USERNAME TEXT FIELD

    private PasswordField loginPasswordField;

    @FXML

//    LOGIN USERNAME TEXT FIELD

    private ChoiceBox loginRole;

    @FXML

//   ERROR LABELS

    private Label loginUsernameError,loginRoleError,LoginPasswordError;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public  void switchSence(ActionEvent event, String fxml){

        try{
            Parent pane = FXMLLoader.load(HelloApplication.class.getResource(fxml));
            stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(pane, 900, 700);
            stage.setScene(scene);
            if(fxml.equals("admin-dashboard.fxml")){
                stage.setResizable(true);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
