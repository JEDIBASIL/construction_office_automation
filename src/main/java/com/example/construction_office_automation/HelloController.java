package com.example.construction_office_automation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
//   TAB LINKS
    private HBox homeTabLink,projectsTabLink,workersTabLink,settingsTabLink;

    @FXML
//   APP TABPANE
    private TabPane appTabPane;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


//   METHOD TO SWITCH APP TABPANE
     public void switchPane(int tab){
        appTabPane.getSelectionModel().select(tab);
     }

//  ALGORITHM TO SWITCH PANE ON CLICK OF EACH ITEM IN THE SIDEBAR
    public  static  void switchActiveLink(HBox[] links, HBox activeLink){

        if(activeLink == null){
            for(HBox link:links){
                link.getStyleClass().remove("active");
            }
        }else{
            for(HBox link:links){
                link.getStyleClass().remove("active");
            }
            activeLink.getStyleClass().add("active");
        }
    }



}