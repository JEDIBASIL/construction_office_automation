package com.example.construction_office_automation;

import com.example.construction_office_automation.model.Employees;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController extends Thread implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
//   ADMIN DASHBOARD SCENE
    private AnchorPane appContainer;

    @FXML
//  MODAL CONTAINER
    private AnchorPane modalContainer;

    @FXML
//  CLOSE MODAL ICON
    private HBox closeModal;

    @FXML
//  TOAST CONTAINER
    private VBox toast;
     int toastTimer;
     @FXML
     private Label toastTimerLabel;
    @FXML
//   TAB LINKS
    private HBox homeTabLink,projectsTabLink,workersTabLink,settingsTabLink;

    @FXML
//   APP TABPANE
    private TabPane appTabPane;

//   DARK MODE CHECKBOX
    @FXML
     private CheckBox darkModeCheckBox;
    @FXML
//  ADD PROJECT TAB LINK
    private Button addProjectTabLink;

    @FXML
//  ADD WORKER TAB LINK
    private  Button addWorkerTabLink;

    @FXML
//  QUICK ACTION TAB LINKS
    private VBox addProjectQuickLink,addWorkerQuickLink,viewProjectsQuickLink;
    @FXML
//  ERROR LABELS
    private Label
//    ADD WORKER ERROR LABEL

            firstNameError,
            surnameError,
            otherNamesError,
            emailError,
            departmentError,
            phoneNumberError,
            ageError,

//    ADD PROJECT ERROR LABEL

            pjNameError,
            pjOwnerError,
            pjStartingDateError,
            pjFinishingDateError,
            pjLocationError,
            pjDirectorError,
            pjDescriptionError,

//    EDIT WORKER ERROR LABEL

            editFirstNameError,
            editSurnameError,
            editOtherNamesError,
            editEmailError,
            editDepartmentError,
            editPhoneNumberError,
            editAgeError,

//    EDIT WORKER ERROR LABEL
            editPjNameError,
            editPjOwnerError,
            editPjLocationError,
            editPjDirectorError,
            editPjStartingDateError,
            editPjFinishingDateError,

    //    ADD ADMIN ERROR LABEL

            addAdminUsernameError,
            addAdminPasswordError,

    //    CHANGE PASSWORD ERROR LABEL

            oldPasswordError,
            newPasswordError,
            confirmNewPasswordError
    ;

    @FXML
//   TEXT FIELDS

    private TextField

//    ADD WORKER TEXT FIELD

            firstNameField,
            surnameField,
            otherNamesField,
            emailField,
            phoneNumberField,
            ageField,

    //    ADD PROJECT TEXT FIELD

            pjNameField,
            pjOwnerField,
            pjDateField,

    //    EDIT WORKER TEXT FIELD

            editFirstNameField,
            editSurnameField,
            editOtherNamesField,
            editEmailField,
            editPhoneNumberField,
            editAgeField,

    //    EDIT PROJECT TEXT FIELD

            editPjNameField,
            editPjOwnerField,

    //    ADD ADMIN TEXT FIELD

            addAdminField
    ;


    final FileChooser fileChooser = new FileChooser();

    public HelloController() {
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }



    Employees employees = new Employees();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        HBox[] sideBarLinks = {homeTabLink,projectsTabLink,workersTabLink,settingsTabLink};

//      SETTING THE MODAL CONTAINER INVISIBLE
        displayModal(false);

//      ADDED AN EVENTLISTENER TO EACH OF THE SIDEBAR LINKS
        homeTabLink.setOnMouseClicked(event -> {
            switchPane(0);
            switchActiveLink(sideBarLinks,homeTabLink);
        });
        projectsTabLink.setOnMouseClicked(event -> {
            switchPane(1);
            switchActiveLink(sideBarLinks,projectsTabLink);
        });
        workersTabLink.setOnMouseClicked(event -> {
            switchPane(2);
            switchActiveLink(sideBarLinks,workersTabLink);
        });
        settingsTabLink.setOnMouseClicked(event -> {
            switchPane(7);
            switchActiveLink(sideBarLinks,settingsTabLink);
        });
//       >>>

//       ADDED AN EVENT LISTENER ON THE CLOSE MODAL ICON
        closeModal.setOnMouseClicked(e->displayModal(false));

//       ADDED AN EVENT LISTENER ON THE DARKMODECHECKBOX TO TOGGLE THEME
        darkModeCheckBox.setOnAction(event -> {
            if (darkModeCheckBox.isSelected()){
                switchTheme(true);
                darkModeCheckBox.setText("ON");
            }else{
                switchTheme(false);
                darkModeCheckBox.setText("OFF");
            }
        });

        addProjectTabLink.setOnAction(e->switchPane(4));
        addProjectQuickLink.setOnMouseClicked(e-> {
            switchPane(4);
            switchActiveLink(sideBarLinks,projectsTabLink);

        });

        addWorkerQuickLink.setOnMouseClicked(e-> {
            switchPane(3);
            switchActiveLink(sideBarLinks,workersTabLink);
        });
        addWorkerTabLink.setOnAction(e-> {
            switchPane(3);
            switchActiveLink(sideBarLinks,workersTabLink);

        });

        viewProjectsQuickLink.setOnMouseClicked(e-> {
            switchPane(1);
            switchActiveLink(sideBarLinks,projectsTabLink);
        });

    }

//   FUNCTION TO SHOW FILECHOOSER

     public  File displayFileChooser(String title){
         fileChooser.setTitle(title);
         fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Images","*.png","*.jpg","*.jpeg"));
           File file = fileChooser.showOpenDialog(null);
           return file;
     }

//   METHOD TO SWITCH APP TABPANE
     public void switchPane(int tab){
        appTabPane.getSelectionModel().select(tab);
     }

//   FUNCTION TO HIDE AND SHOW MODAL
     public void displayModal(boolean display){
         modalContainer.setVisible(display);
     }


//   METHOD TO SWITCH TO DARK MODE
     public  void switchTheme(boolean theme){
        if(theme == true){
            appContainer.getStylesheets().add(1, String.valueOf(HelloApplication.class.getResource("styles/dark-mode.css")));
        }else {
            appContainer.getStylesheets().remove(1);

        }
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

//   FUNCTION TO DISPLAY TOAST
    public void displayToast(boolean animate,String animationType){
        TranslateTransition toastAnimation = new TranslateTransition();
        toastAnimation.setNode(toast);
        toastAnimation.setDuration(Duration.seconds(0.3));
        if(animate){
            toastAnimation.setByX(-430);
            toastAnimation.setRate(3);
        }
        if(!animate) {
            toastAnimation.setDuration(Duration.seconds(0.3));
            toastAnimation.setDelay(Duration.seconds(5));
            toastAnimation.setByX(430);
        }

        if(!animate && animationType != null && animationType.equals("CLOSE")) {
            toastAnimation.setDelay(Duration.seconds(0));
            toastAnimation.setByX(430);
        }
        toastAnimation.play();
//        toastAnimation.stop();
    }

//  FUNCTION TO VALIDATE TEXT FIELD BASE ON ARGUMENTS
    public String validateTextFields(Label errorMessage,TextField field,String validationType,String fieldName,String extra){
        String emailRegex = "^(.+)@(.+)$";
        String numberRegex = "\\d+";

        if(validationType.equals("NAME") && extra == null){
            if(field.getText().trim().length() < 3) errorMessage.setText(fieldName+" must be at least 3 characters");
            else if (field.getText().length() > 25)errorMessage.setText(fieldName+" should not be more than 25 letters");
            else{
                errorMessage.setText("");
                return field.getText();
            }
        }

        if(validationType.equals("EMAIL") && extra == null){
            Pattern emailPattern = Pattern.compile(emailRegex);
            Matcher emailMatcher = emailPattern.matcher(field.getText());
            if(!emailMatcher.matches()){
                errorMessage.setText(fieldName+" is invalid");
            }else{
                errorMessage.setText("");
                return field.getText();
            }
        }

        if(validationType.equals("NUMBER")) {
            Pattern numberPattern = Pattern.compile(numberRegex);
            Matcher numberMatcher = numberPattern.matcher(field.getText());

            if (numberMatcher.matches()) {
                Long text = Long.parseLong(field.getText());
                if(extra.equals("AGE")){
                    if(text < 18) errorMessage.setText(fieldName+" should be above 18");

                    else if(text > 55) errorMessage.setText("workers " + fieldName + " should not be above 55");

                    else{
                        errorMessage.setText("");
                        return field.getText();
                    }
                }

                if(extra.equals("PHONE_NUMBER")){
                    if (field.getText().length() < 10 || field.getText().length() > 16) errorMessage.setText(fieldName + " is invalid");
                    else{
                        errorMessage.setText("");
                        return field.getText();
                    }
                }
                return "0";
            }else{
                errorMessage.setText(fieldName+" should be a number");
                return "0";
            }
        }



        return null;
    }



}