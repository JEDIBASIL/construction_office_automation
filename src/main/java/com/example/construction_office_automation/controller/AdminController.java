package com.example.construction_office_automation.controller;

import animatefx.animation.*;
import com.example.construction_office_automation.HelloApplication;
import com.example.construction_office_automation.enums.Validation;
import com.example.construction_office_automation.model.Employees;
import com.example.construction_office_automation.model.Admin;
import com.example.construction_office_automation.model.database.DatabaseConnection;
import com.example.construction_office_automation.utils.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController extends Thread implements Initializable {
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
//   BUTTON TO DISPLAY ADD DEPARTMENT MODAL
    private Button addDepartmentFormShow;

    @FXML

//  MODAL BOXES
    private VBox confirmationModal,addCategoryModalForm;

    @FXML
//  MODAL BOX BUTTONS
    private Button modalYesOption,modalNoOption,addDepartmentBtn;

    @FXML
//  MODAL LABELS
    private Label modalHeading,modalSubHeading;

    @FXML
//  QUICK ACTION TAB LINKS
    private VBox addProjectQuickLink,addWorkerQuickLink,viewProjectsQuickLink;

    @FXML

//   ADMIN DETAILS

    private Label loggedInUsername;

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

            adminRoleError,
            addAdminPasswordError,

    //    CHANGE PASSWORD ERROR LABEL

            oldPasswordError,
            newPasswordError,
            confirmNewPasswordError,

//         ADD DEPARTMENT ERROR LABEL

            addDepartmentError,

//         ADD WORKER GENDER ERROR LABEL
            genderError;
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

            addAdminField,

    //    ADD DEPARTMENT TEXT FIELD

           addDepartmentField
    ;

    @FXML

//   ADD ADMIN PASSWORD FILED

    private PasswordField addAdminPasswordField;

    @FXML

//  PROJECT LOCATION CHOICEBOX

    private ChoiceBox pjLocationChoiceBox;

    @FXML

    //  PROJECT LOCATION CHOICEBOX

    private ChoiceBox departmentChoiceBox,editDepartmentChoiceBox;

    @FXML

//  ADD ADMIN ROLE CHOICEBOX

    private ChoiceBox adminRoleChoiceBox;


    @FXML
//  GENDER RADIO BUTTONS

    private RadioButton addWorkerMale,addWorkerFemale,editWorkerFemale,editWorkerMale;


    @FXML

//  NOTIFICATION ICON

    private VBox notificationIcon;
    @FXML

//  NOTIFICATION CONTAINER

    private AnchorPane notificationContainer;

    final FileChooser fileChooser = new FileChooser();

    public AdminController() {
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

//  ADD WORKER GENDER TOGGLE GROUP

    private ToggleGroup addWorkerGenderGroup;

   Validation validation;


    Employees employees = new Employees();
    Session session = new Session();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        setDepartmentChoiceBox();
        setLocationChoiceBox();


//      ADDING GENDER TO A TOGGLE GROUP

        addWorkerGenderGroup = new ToggleGroup();
        addWorkerFemale.setToggleGroup(addWorkerGenderGroup);
        addWorkerMale.setToggleGroup(addWorkerGenderGroup);

        notificationContainer.setVisible(false);
        HBox[] sideBarLinks = {homeTabLink,projectsTabLink,workersTabLink,settingsTabLink};

//      SETTING THE MODAL CONTAINER INVISIBLE
        displayModal(null);

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
            switchPane(3);
            switchActiveLink(sideBarLinks,settingsTabLink);
            displayModal("Hello world","it fells good to be on earth");

        });
//       >>>

//       ADDED AN EVENT LISTENER ON THE CLOSE MODAL ICON

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

        addDepartmentFormShow.setOnAction(e->displayModal("FORM"));
        notificationIcon.setOnMouseClicked(e->notificationContainer.setVisible(true));
    }

//   FUNCTION TO SHOW FILE-CHOOSER

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

//   FUNCTION TO CHANGE THE NAME OF THE CURRENT TAB




//   METHOD TO SWITCH TO DARK MODE
     public  void switchTheme(boolean theme){
        if(theme){
            appContainer.getStylesheets().add(1, String.valueOf(HelloApplication.class.getResource("styles/dark-mode.css")));
        }else {
            appContainer.getStylesheets().remove(1);

        }
     }

//  ALGORITHM TO SWITCH PANE ON CLICK OF EACH ITEM IN THE SIDEBAR
    public    void switchActiveLink(HBox[] links, HBox activeLink){

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




// FUNCTION TO ADD DATA TO DEPARTMENT CHOICEBOX
    public void setDepartmentChoiceBox(){
        departmentChoiceBox.getItems().addAll(getDepartments());
    }


    // FUNCTION TO ADD DATA TO DEPARTMENT CHOICEBOX
    public void setLocationChoiceBox(){
        pjLocationChoiceBox.getItems().addAll(getLocations());
    }

// FUNCTION TO DISPLAY MODAL
    public void displayModal(String displayType){
        if(displayType != null){
            modalContainer.setVisible(true);
            if(displayType.equals("FORM")){
                confirmationModal.setMaxHeight(0);
                confirmationModal.setMinHeight(0);
                confirmationModal.setVisible(false);
                new BounceInDown(addCategoryModalForm).play();
                addDepartmentField.requestFocus();
            }
        }else{
            confirmationModal.setMaxHeight(250);
            confirmationModal.setMinHeight(250);
            addCategoryModalForm.setMaxHeight(250);
            addCategoryModalForm.setMinHeight(250);
            confirmationModal.setVisible(true);
            addCategoryModalForm.setVisible(true);
            modalContainer.setVisible(false);
        }
    }
    public void displayModal(String heading,String subHeading){
        modalHeading.setText(heading);
        modalSubHeading.setText(subHeading);
        addCategoryModalForm.setMaxHeight(0);
        addCategoryModalForm.setMinHeight(0);
        addCategoryModalForm.setVisible(false);
        new BounceInDown(confirmationModal).play();
        modalContainer.setVisible(true);
    }

    @FXML
    protected void onAddWorkerClicked(){

    }



    @FXML
    protected void onAddProjectClicked(){

    }

    @FXML
    protected void onAddAdminClicked(){

    }



    @FXML
    protected void onModalClose(){
        displayModal(null);
    }

    @FXML
    protected void onAddDepartmentClicked(){
    }



//  DATABASE MANIPULATIONS

    DatabaseConnection databaseConnection = new DatabaseConnection();

//  RETRIEVE LOCATIONS FROM DATABASE
    public List getLocations(){
        ArrayList locationList = new ArrayList();
        if(databaseConnection.dbConnect()){
            String GET_LOCATIONS = "SELECT DISTINCT name FROM location";
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(GET_LOCATIONS);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    String location = resultSet.getString("name");
//                    System.out.println(location);
                    locationList.add(location);
                }
            }catch (SecurityException | SQLException se){
                se.printStackTrace();
            }
        }
        return locationList;
    }

//  ADD NEW DEPARTMENT TO DATABASE

    public void addDepartment(Employees employees){
        if(databaseConnection.dbConnect()){
            String ADD_DEPARTMENT = "INSERT INTO department (name) VALUES(?)";
            try{
                int upd =0;
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(ADD_DEPARTMENT);
                preparedStatement.setString(1,employees.getDepartment());
                upd = preparedStatement.executeUpdate();
                if(upd !=0){
                    System.out.println("department added successfully");
                }
            }catch (SecurityException | SQLException se){
                se.printStackTrace();
            }
        }
    }

    //  RETRIEVE DEPARTMENTS FROM DATABASE
    public List getDepartments(){
        ArrayList departmentList = new ArrayList();
        if(databaseConnection.dbConnect()){
            String GET_LOCATIONS = "SELECT name FROM department";
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(GET_LOCATIONS);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    String location = resultSet.getString("name");
//                    System.out.println(location);
                    departmentList.add(location);
                }
            }catch (SecurityException | SQLException se){
                se.printStackTrace();
            }
        }
        return departmentList;
    }

//    ADD ADMIN
    public void addAdmin(Admin admin){
        if(databaseConnection.dbConnect()){
            String ADD_ADMIN = "INSERT INTO admin (username,email,role,password) VALUES(?,?,?,?)";
            int upd =0;
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(ADD_ADMIN);
                preparedStatement.setString(1,admin.getUsername());
                preparedStatement.setString(2,admin.getEmail());
                preparedStatement.setString(3,admin.getRole());
                preparedStatement.setString(4,admin.getPassword());
                upd = preparedStatement.executeUpdate();
                if(upd != 0) System.out.println("admin added successfully");
            }catch (SQLException | SecurityException se){
                se.printStackTrace();
            }
        }
    }


}