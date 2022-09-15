package com.example.construction_office_automation.controller;

import animatefx.animation.*;
import com.example.construction_office_automation.HelloApplication;
import com.example.construction_office_automation.controller.validator.Validator;
import com.example.construction_office_automation.enums.Validation;
import com.example.construction_office_automation.model.Employees;
import com.example.construction_office_automation.model.Admin;
import com.example.construction_office_automation.model.Project;
import com.example.construction_office_automation.model.database.DatabaseConnection;
import com.example.construction_office_automation.security.EncryptPassword;
import com.example.construction_office_automation.utils.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.xml.transform.Result;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.construction_office_automation.enums.Validation.*;

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
            staffRoleError,
            addWorkerImgError,

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
            addAdminIdError,

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
//  PROJECT LABEL
    private Label
            pjName,
            pjStatus,
            projectProgress;

    @FXML

//  STATICSTICS LABEL

    private Label
            totalProjects,
            completedProject,
            ongoingProject,
            pendingProject;

    @FXML

//  WORKERS TABLE

    private TableView<Employees> workersTable;

    @FXML

//    WORKERS TABLE COLUMN

    private TableColumn<Employees,String>
            workerId,
            tableFirstName,
            tableSurname,
            tableOtherNames,
            tableEmail,
            tablePhoneNumber,
            tableDepartment,
            tableAge,
            tableGender;

    ObservableList<Employees> employeesList = FXCollections.observableArrayList();

    @FXML

    private TableView<Project> projectTable;

    @FXML

//    PROJECTS TABLE COLUMN

    private TableColumn<Project, String>
            pjIdCol,
            pjNameCol,
            pjOwnerCol,
            pjStartingCol,
            pjFinishingCol,
            pjLocationCol,
            pjManagerCol,
            pjStatusCol,
            pjProgressCol;

    ObservableList<Project> projectList = FXCollections.observableArrayList();



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
            addAdminIdField,

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

    private ChoiceBox
            staffRoleChoiceBox,
            pjMangerChoiceBox,
            departmentChoiceBox,
            editDepartmentChoiceBox,
            editPjLocationChoiceBox,
            editPjManager
                    ;

    @FXML

//  ADD ADMIN ROLE CHOICEBOX

    private ChoiceBox
            adminRoleChoiceBox;


    @FXML
//  GENDER RADIO BUTTONS

    private RadioButton addWorkerMale,addWorkerFemale,editWorkerFemale,editWorkerMale;


    @FXML

//  NOTIFICATION ICON

    private VBox notificationIcon;

    @FXML

//  ADD WORKER IMAGE CONTAINER

    private VBox addWorkerImgContainer;
    @FXML

//  ADD WORKER IMAGE VIEW

    private ImageView addWorkerImg,editWorkerImage;



    @FXML

    private DatePicker
            pjFinishingDatePicker,
            pjStartingDatePicker,
            editPjStartingDatePicker,
            editPjFinishingDatePicker
    ;

    @FXML

    private TextArea pjDescription;

    @FXML

    private ProgressBar projectProgressBar;


//  NOTIFICATION CONTAINER

    @FXML
    private AnchorPane notificationContainer;

    @FXML

    private LineChart projectsChart;

    @FXML
    private CategoryAxis  projectMonthAxis;

    @FXML
    private NumberAxis projectMonthNumberAxis;


    final FileChooser fileChooser = new FileChooser();

    public AdminController() {
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

//  ADD WORKER GENDER TOGGLE GROUP

    private ToggleGroup addWorkerGenderGroup,editWorkerGenderGroup;

   private Validation validation;


   Employees employees = new Employees();
   Project project = new Project();
   Session session = new Session();
   Validator validator = new Validator();
   Admin admin = new Admin();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        setDepartmentChoiceBox();
        setLocationChoiceBox();
        setProjectsChart();
        staffRoleChoiceBox.getItems().addAll("Project manager","Project monitor");
        adminRoleChoiceBox.getItems().addAll("Project manager","Project monitor");
        pjMangerChoiceBox.getItems().addAll(getProjectManager());
        editPjManager.getItems().addAll(getProjectManager());

        totalProjects.setText(getTotalPorject()+"");
        completedProject.setText(getTotalPorject("Completed")+"");
        ongoingProject.setText(getTotalPorject("Ongoing")+"");
        pendingProject.setText(getTotalPorject("Pending")+"");

        displayWorkers();
        displayProjects();

//      ADDING GENDER TO A TOGGLE GROUP

        addWorkerGenderGroup = new ToggleGroup();
        addWorkerFemale.setToggleGroup(addWorkerGenderGroup);
        addWorkerMale.setToggleGroup(addWorkerGenderGroup);

        editWorkerGenderGroup = new ToggleGroup();
        editWorkerFemale.setToggleGroup(editWorkerGenderGroup);
        editWorkerMale.setToggleGroup(editWorkerGenderGroup);

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

        addWorkerImgContainer.setOnMouseClicked(e->{
            File file = displayFileChooser("Add worker image");
            if(file != null){
                addWorkerImg.setImage(new Image(file.toURI().toString()));
                employees.setImgUrl(addWorkerImg.getImage().getUrl());
            }
        });

        workersTable.setOnMouseClicked(e->{
            if(workersTable.getSelectionModel().getSelectedIndex() != -1) {
                    switchPane(6);
                    displayWorker(workersTable.getSelectionModel().getSelectedItem().getId());
            }else workersTable.getSelectionModel().clearSelection();
        });
        projectTable.setOnMouseClicked(e->{
            if(projectTable.getSelectionModel().getSelectedIndex() != -1) {
                switchPane(7);
                displayProject((int) projectTable.getSelectionModel().getSelectedItem().getProjectId());
            }else projectTable.getSelectionModel().clearSelection();
        });


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
        editPjLocationChoiceBox.getItems().addAll(getLocations());
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

    public void displayModal(String heading,String subHeading,String type){
        modalHeading.setText(heading);
        modalSubHeading.setText(subHeading);
        addCategoryModalForm.setMaxHeight(0);
        addCategoryModalForm.setMinHeight(0);
        addCategoryModalForm.setVisible(false);
        new BounceInDown(confirmationModal).play();
        modalContainer.setVisible(true);
        if(type == null) {
            modalYesOption.setMaxWidth(0);
            modalYesOption.setPadding(new Insets(0,0,0,0));
            modalYesOption.setVisible(false);
        }else{
            modalYesOption.setMaxWidth(150);
            modalYesOption.setVisible(true);
            modalYesOption.setText(type.toLowerCase());
        }
    }

//   FUNCTION TO DISPLAY TOAST

    public  void  toast(String title,String message){
        Notifications notificationsBuilder =Notifications.create()
                .title(title)
                .text(message)
                .graphic(null)
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_LEFT)
                ;
        notificationsBuilder.showConfirm();
    }

    @FXML
    protected void onAddWorkerClicked(){
        employees.setFirstName(validator.validateTextFields(firstNameError,firstNameField,NAME.toString(),"First name",null));
        employees.setSurname(validator.validateTextFields(surnameError,surnameField,NAME.toString(),"Surname",null));
        employees.setOtherNames(validator.validateTextFields(otherNamesError,otherNamesField,NAME.toString(),"Other names",null));
        employees.setEmail(validator.validateTextFields(emailError,emailField,EMAIL.toString(),"Email",null));
        employees.setDepartment(validator.validateChoiceBox(departmentError,departmentChoiceBox,"Department"));
        employees.setAge(Integer.parseInt(validator.validateTextFields(ageError,ageField,NUMBER.toString(),"Age",AGE.toString())));
        employees.setPhoneNumber(Long.parseLong(validator.validateTextFields(phoneNumberError,phoneNumberField,NUMBER.toString(),"Phone number",PHONE_NUMBER.toString())));
        employees.setGender(validator.validateRadioButton(genderError,addWorkerGenderGroup,"Gender"));
        employees.setRole(validator.validateChoiceBox(staffRoleError,staffRoleChoiceBox,"Role"));
        employees.setImgUrl(validator.validateImageView(addWorkerImgError,addWorkerImg,"Image"));
        if(employees.validateFields()){
            System.out.println("ready to send");
            if(!searchWorker(employees.getEmail())) {
                if(addWorker(employees)) {
                    toast("Success", "worker is added");
                    employeesList.setAll(new Employees[]{});
                    displayWorkers();
                }
                else  toast("Error","something went wrong");
            }
            else displayModal("Error","Worker with "+employees.getEmail()+" already exist",null);
        }
    }


    @FXML
    protected void onAddProjectClicked(){
        project.setProjectName(validator.validateTextFields(pjNameError,pjNameField,NAME.toString(),"Project name",null));
        project.setProjectOwner(validator.validateTextFields(pjOwnerError,pjOwnerField,NAME.toString(),"Project owner",null));
        project.setStartingDate(validator.validateDatePicker(pjStartingDateError,pjStartingDatePicker,"Starting date"));
        project.setFinishingDate(validator.validateDatePicker(pjFinishingDateError,pjFinishingDatePicker,"Finishing date"));
        project.setProjectLocation(validator.validateChoiceBox(pjLocationError,pjLocationChoiceBox,"Location"));
        project.setProjectManager(validator.validateChoiceBox(pjDirectorError,pjMangerChoiceBox,"Project manager"));
        project.setProjectDescription(validator.validateTextArea(pjDescriptionError,pjDescription,"Project Description"));
        project.setProjectMonitor("NONE");
        project.setProjectStatus("Pending");
        project.setProjectProgress(0);

        if(project.checkFields()) {
            if(addProject(project)){
                toast("Success","Project added successfully");
                projectList.setAll(new Project[]{});
                displayProjects();
                switchPane(1);
                projectsChart.getData().clear();
                setProjectsChart();
            }else{
                toast("Error","an error occured");
            }
        }

    }




    @FXML
    protected void onAddAdminClicked(){
        admin.setId(validator.validateTextFields(addAdminIdError,addAdminIdField,NAME.toString(),"Employee id",null));
        admin.setUsername(validator.validateTextFields(addAdminUsernameError,addAdminField,NAME.toString(),"Username",null));
        admin.setRole(validator.validateChoiceBox(adminRoleError,adminRoleChoiceBox,"Role"));
        admin.setPassword(validator.validatePasswordFields(addAdminPasswordError,addAdminPasswordField,null,"Password",null));
        admin.setEmail("");
       if(admin.validateFields()){
           admin.setId(admin.getId().toLowerCase().replace("pj",""));
           if(!searchWorker(Integer.parseInt(admin.getId()))) displayModal("Error","Worker with id "+admin.getId()+" does not exist",null);
           else if(searchAdmin(Integer.parseInt(admin.getId()))) displayModal("Error","Administrator with id "+admin.getId()+" already exist",null);
           else if(searchAdmin(admin.getUsername(),"USERNAME")) displayModal("Error","Worker with username "+admin.getUsername()+" already  exist",null);
           else {
               addAdmin(admin);
           };
       }
    }


    @FXML
    protected  void  onDeleteWorker(){
        if(workersTable.getSelectionModel().getSelectedItem() !=null) displayModal("Risky","are you sure you want to delete PAN"+workersTable.getSelectionModel().getSelectedItem().getId(),"DELETE");
    }

    @FXML
    protected void  onTerminateProject(){
        if(projectTable.getSelectionModel().getSelectedItem() !=null) displayModal("Risky","are you sure you want to terminate "+projectTable.getSelectionModel().getSelectedItem().getProjectName(),"TERMINATE");

    }

    @FXML

    protected void onTerminateBtnClick(){
        displayModal(null);
        if(modalYesOption.getText().equals("DELETE".toLowerCase())){
            if(deleteWorker(workersTable.getSelectionModel().getSelectedItem().getId())) {
                toast("Success", "worker deleted");
                employeesList.setAll(new Employees[]{});
                displayWorkers();
                switchPane(2);
            }
        } else if(modalYesOption.getText().equals("TERMINATE".toLowerCase())){
            if(terminateProject((int) projectTable.getSelectionModel().getSelectedItem().getProjectId())) {
                toast("Success", "project terminated");
                projectList.setAll(new Project[]{});
                displayProjects();
                switchPane(1);
                setProjectsChart();
            }
        }
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

//    SEARCH WORKER TABLE

//    SEARCH ADMIN TABLE BASE ON EMPLOYEE ID
    public boolean searchAdmin(Integer id){
        if(databaseConnection.dbConnect()){
            String SEARCH_ADMIN = "SELECT * FROM admin WHERE employee_id = ?";
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(SEARCH_ADMIN);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) return true;
            }catch (SQLException | SecurityException se){
                se.printStackTrace();
            }
        }
        return false;
    }

    //    SEARCH WORKER TABLE BASE ON EMAIL
    public boolean searchAdmin(String query,String type){
        if(databaseConnection.dbConnect()){
            String SEARCH_ADMIN;
           if(type.equals("EMAIL")){
                SEARCH_ADMIN = "SELECT * FROM workers WHERE email = ?";
               try{
                   PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(SEARCH_ADMIN);
                   preparedStatement.setString(1, query);
                   ResultSet resultSet = preparedStatement.executeQuery();
                   if(resultSet.next()) return true;

               }catch (SQLException | SecurityException se){
                   se.printStackTrace();
               }
           }
           if(type.equals("USERNAME")){
                SEARCH_ADMIN = "SELECT * FROM admin WHERE username = ?";
                try{
                    PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(SEARCH_ADMIN);
                    preparedStatement.setString(1, query);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()) return true;

                }catch (SQLException | SecurityException se){
                    se.printStackTrace();
                }
            }
        }
        return false;
    }

    //    SEARCH WORKER TABLE BASE ON ID
    public boolean searchWorker(Integer id){
        if(databaseConnection.dbConnect()){
            String SEARCH_WORKER = "SELECT * FROM workers WHERE id = ?";
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(SEARCH_WORKER);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    admin.setEmail(resultSet.getString("email"));
                    return true;
                }

            }catch (SQLException | SecurityException se){
                se.printStackTrace();
            }
        }
        return false;
    }

    //    SEARCH WORKER TABLE BASE ON EMAIL
    public boolean searchWorker(String email){
        if(databaseConnection.dbConnect()){
            String SEARCH_WORKER = "SELECT * FROM workers WHERE email = ?";
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(SEARCH_WORKER);
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) return true;

            }catch (SQLException | SecurityException se){
                se.printStackTrace();
            }
        }
        return false;
    }

//    ADD ADMIN
    public void addAdmin(Admin admin){
        if(databaseConnection.dbConnect()){
            EncryptPassword encryptPassword = new EncryptPassword();
            String ADD_ADMIN = "INSERT INTO admin (username,email,employee_id,role,password) VALUES(?,?,?,?,?)";
            int upd =0;
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(ADD_ADMIN);
                preparedStatement.setString(1,admin.getUsername());
                preparedStatement.setString(2,admin.getEmail());
                preparedStatement.setString(3,admin.getId());
                preparedStatement.setString(4,admin.getRole());
                preparedStatement.setString(5,encryptPassword.EncryptPassword(admin.getPassword()));
                upd = preparedStatement.executeUpdate();
                if(upd != 0) System.out.println("admin added successfully");
            }catch (SQLException | SecurityException se){
                se.printStackTrace();
            }
        }
    }

    public boolean addWorker(Employees employees){
        if(databaseConnection.dbConnect()){
            String ADD_WORKER = "INSERT INTO WORKERS(first_name,surname,other_name,email,department,phone_number,age,gender,role,photo) VALUES(?,?,?,?,?,?,?,?,?,?)";
            try{
                int upd = 0;

                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(ADD_WORKER);
                preparedStatement.setString(1,employees.getFirstName());
                preparedStatement.setString(2,employees.getSurname());
                preparedStatement.setString(3,employees.getOtherNames());
                preparedStatement.setString(4,employees.getEmail());
                preparedStatement.setString(5,employees.getDepartment());
                preparedStatement.setString(6,employees.getPhoneNumber()+"");
                preparedStatement.setInt(7,employees.getAge());
                preparedStatement.setString(8,employees.getGender());
                preparedStatement.setString(9,employees.getRole());
                preparedStatement.setString(10,employees.getImgUrl());

                upd = preparedStatement.executeUpdate();

                if(upd != 0) return true;
            }catch (SecurityException | SQLException se ){
                se.printStackTrace();
            }
        }
        return false;
    }

    public void  displayWorkers(){
        if(databaseConnection.dbConnect()){
            String GET_ALL_WORKERS = "SELECT * FROM WORKERS";
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(GET_ALL_WORKERS);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                  employeesList.add(new Employees(
                          resultSet.getInt("id"),
                          resultSet.getString("first_name"),
                          resultSet.getString("surname"),
                          resultSet.getString("other_name"),
                          resultSet.getString("email"),
                          resultSet.getString("department"),
                          resultSet.getLong("phone_number"),
                          resultSet.getInt("age"),
                          resultSet.getString("gender")
                  ));
                }
            }catch (SQLException | SecurityException se){
                se.printStackTrace();
            }

            workerId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tableFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            tableSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
            tableOtherNames.setCellValueFactory(new PropertyValueFactory<>("otherNames"));
            tableEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            tablePhoneNumber.setCellValueFactory(new PropertyValueFactory<>("Department"));
            tableDepartment.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
            tableAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
            tableGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

            workersTable.setItems(employeesList);
        }
    }

    public boolean addProject(Project project){
        if(databaseConnection.dbConnect()){
            String ADD_PROJECT = "INSERT INTO projects(" +
                    "project_name," +
                    "project_owner," +
                    "project_location," +
                    "project_manger," +
                    "project_monitor," +
                    "project_description," +
                    "project_status," +
                    "project_progress," +
                    "starting_date," +
                    "finishing_date" +
                    ") VALUES(?,?,?,?,?,?,?,?,?,?)";

            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(ADD_PROJECT);
                preparedStatement.setString(1,project.getProjectName());
                preparedStatement.setString(2,project.getProjectOwner());
                preparedStatement.setString(3,project.getProjectLocation());
                preparedStatement.setString(4,project.getProjectManager());
                preparedStatement.setString(5,project.getProjectMonitor());
                preparedStatement.setString(6,project.getProjectDescription());
                preparedStatement.setString(7,project.getProjectStatus());
                preparedStatement.setInt(8,project.getProjectProgress());
                preparedStatement.setDate(9, Date.valueOf(project.getStartingDate()));
                preparedStatement.setDate(10, Date.valueOf(project.getFinishingDate()));


                int upd =0;
                upd = preparedStatement.executeUpdate();
                if(upd !=0) {
                    System.out.println("Project added successfully");
                    return true;
                }
            }catch (SecurityException | SQLException se){
                se.printStackTrace();
            }
        }

        return false;
    }
    public void displayWorker(Integer id){
        Employees employees = new Employees();
        if(databaseConnection.dbConnect()){
            String GET_WORKER = "SELECT * FROM workers WHERE id = ?";
            try {
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(GET_WORKER);
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    employees.setId(resultSet.getInt("id"));
                    employees.setFirstName( resultSet.getString("first_name"));
                    employees.setSurname( resultSet.getString("surname"));
                    employees.setOtherNames( resultSet.getString("other_name"));
                    employees.setEmail(resultSet.getString("email"));
                    employees.setDepartment(resultSet.getString("department"));
                    employees.setPhoneNumber((resultSet.getLong("phone_number")));
                    employees.setAge(resultSet.getInt("age"));
                    employees.setGender(resultSet.getString("gender"));
                    employees.setImgUrl(resultSet.getString("photo"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            editFirstNameField.setText(employees.getFirstName());
            editSurnameField.setText(employees.getSurname());
            editOtherNamesField.setText(employees.getOtherNames());
            editEmailField.setText(employees.getEmail());
            editAgeField.setText(employees.getAge()+"");
            editPhoneNumberField.setText(employees.getPhoneNumber()+"");
            editFirstNameField.setText(employees.getFirstName());
            editDepartmentChoiceBox.setValue(employees.getDepartment());
            editWorkerImage.setImage(new Image(employees.getImgUrl()));
            if(employees.getGender().equalsIgnoreCase("male")) editWorkerMale.setSelected(true);
            if(employees.getGender().equalsIgnoreCase("female")) editWorkerFemale.setSelected(true);

        }
    }

    public boolean deleteWorker(Integer id){
        if(databaseConnection.dbConnect()){
            String DELETE_WORKER = "DELETE FROM workers WHERE id =?";
            try {
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(DELETE_WORKER);
                preparedStatement.setInt(1,id);
                int delete = preparedStatement.executeUpdate();
                if(delete !=0) return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public List getProjectManager(){
        ArrayList projectMangerList = new ArrayList();
        if(databaseConnection.dbConnect()){
            String GET_PROJECT_MANAGERS = "SELECT id FROM workers WHERE role = ?";
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(GET_PROJECT_MANAGERS);
                preparedStatement.setString(1,"Project manager");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Integer manager = resultSet.getInt("id");
                    projectMangerList.add("PAN"+manager);
                }
            }catch (SecurityException | SQLException se){
                se.printStackTrace();
            }
        }
        return projectMangerList;
    }

    public void displayProjects(){
        if(databaseConnection.dbConnect()){
            String GET_ALL_PROJECTS = "SELECT * FROM projects";
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(GET_ALL_PROJECTS);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    projectList.add(new Project(
                            resultSet.getInt("project_id"),
                            resultSet.getString("project_name"),
                            resultSet.getString("project_owner"),
                            LocalDate.parse(resultSet.getDate("starting_date").toString()),
                            LocalDate.parse(resultSet.getDate("finishing_date").toString()),
                            resultSet.getString("project_location"),
                            resultSet.getString("project_manger"),
                            resultSet.getString("project_status"),
                            resultSet.getInt("project_progress")
                    ));
                }
            }catch (SQLException | SecurityException se){
                se.printStackTrace();
            }

            pjIdCol.setCellValueFactory(new PropertyValueFactory<>("projectId"));
            pjNameCol.setCellValueFactory(new PropertyValueFactory<>("projectName"));
            pjOwnerCol.setCellValueFactory(new PropertyValueFactory<>("projectOwner"));
            pjStartingCol.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
            pjFinishingCol.setCellValueFactory(new PropertyValueFactory<>("finishingDate"));
            pjLocationCol.setCellValueFactory(new PropertyValueFactory<>("projectLocation"));
            pjManagerCol.setCellValueFactory(new PropertyValueFactory<>("projectManager"));
            pjStatusCol.setCellValueFactory(new PropertyValueFactory<>("projectStatus"));
            pjProgressCol.setCellValueFactory(new PropertyValueFactory<>("projectProgress"));

            projectTable.setItems(projectList);
        }
    }

    public boolean terminateProject(Integer id){
        if(databaseConnection.dbConnect()){
            String TERMINATE_PROJECT = "DELETE FROM projects WHERE project_id =?";
            try {
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(TERMINATE_PROJECT);
                preparedStatement.setInt(1,id);
                int terminate = preparedStatement.executeUpdate();
                if(terminate !=0) return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    public void displayProject(int id){
        if(databaseConnection.dbConnect()){
            String GET_PROJECT = "SELECT * FROM projects WHERE project_id = ?";
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(GET_PROJECT);
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    project.setProjectName(resultSet.getString("project_name"));
                    project.setProjectOwner(resultSet.getString("project_owner"));
                    project.setProjectLocation(resultSet.getString("project_location"));
                    project.setProjectManager(resultSet.getString("project_manger"));
                    project.setProjectDescription(resultSet.getString("project_description"));
                    project.setProjectStatus(resultSet.getString("project_status"));
                    project.setStartingDate(LocalDate.parse(resultSet.getDate("starting_date").toString()));
                    project.setFinishingDate(LocalDate.parse(resultSet.getDate("finishing_date").toString()));
                    project.setProjectProgress(resultSet.getInt("project_progress"));
                }
            }catch (SQLException e) {
            throw new RuntimeException(e);
            }
            pjName.setText(project.getProjectName());
            pjStatus.setText(project.getProjectStatus());
            editPjNameField.setText(project.getProjectName());
            editPjOwnerField.setText(project.getProjectOwner());
            projectProgress.setText(project.getProjectProgress()+"%");
            editPjLocationChoiceBox.setValue(project.getProjectLocation());
            editPjStartingDatePicker.setValue(project.getStartingDate());
            editPjFinishingDatePicker.setValue(project.getFinishingDate());
            editPjManager.setValue(project.getProjectManager());
            projectProgressBar.setProgress(Double.parseDouble("0."+project.getProjectProgress()));
            if(!project.getProjectStatus().equals("Pending")){
                editPjStartingDatePicker.setDisable(true);
            }
        }
    }

    public Long getTotalPorject(){
        long result =0L;
        if(databaseConnection.dbConnect()){
            String COUNT_WORKERS = "SELECT COUNT(*) as total FROM projects";
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(COUNT_WORKERS);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) result = resultSet.getInt("total");
            }catch (SQLException | SecurityException se){
                se.printStackTrace();
            }
        }
        return result;
    }
    public Long getTotalPorject(String status){
        long result =0L;
        if(databaseConnection.dbConnect()){
            String COUNT_WORKERS = "SELECT COUNT(*) as total FROM projects WHERE project_status = ?";
            try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(COUNT_WORKERS);
                preparedStatement.setString(1,status);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) result = resultSet.getInt("total");
            }catch (SQLException | SecurityException se){
                se.printStackTrace();
            }
        }
        return result;
    }

    public boolean updateProjectDetails(Project project,int projectId){
        if(databaseConnection.dbConnect()){
            int upd =0;
            String UPDATE_PROJECT_DETAILS = "UPDATE projects " +
                    "SET project_name = ?," +
                    "project_owner = ?," +
                    "project_location = ?," +
                    "project_manger = ?," +
                    "project_description = ?," +
                    "starting_date = ?," +
                    "finishing_date = ?, " +
                    " WHERE  PROJECT_ID  = ?";
            try {
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(UPDATE_PROJECT_DETAILS);
                preparedStatement.setString(1,project.getProjectName());
                preparedStatement.setString(2,project.getProjectOwner());
                preparedStatement.setString(3,project.getProjectLocation());
                preparedStatement.setString(4,project.getProjectManager());
                preparedStatement.setString(5,project.getProjectDescription());
                preparedStatement.setDate(6, Date.valueOf(project.getStartingDate()));
                preparedStatement.setDate(7, Date.valueOf(project.getFinishingDate()));
                preparedStatement.setInt(8,projectId);
                upd = preparedStatement.executeUpdate();
                if(upd !=0) return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public boolean updateProjectProgress(int projectId){
        if(databaseConnection.dbConnect()){
            int upd =0;
            String UPDATE_PROJECT_PROGRESS = "UPDATE projects " +
                    "SET project_progress = ?," +
                    " WHERE  PROJECT_ID  = ?";
            try {
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(UPDATE_PROJECT_PROGRESS);
                preparedStatement.setInt(1,project.getProjectProgress());
                preparedStatement.setInt(8,projectId);
                upd = preparedStatement.executeUpdate();
                if(upd !=0) return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public void setProjectsChart(){
        if(databaseConnection.dbConnect()) {
            String GET_CHART_DATA = "SELECT DISTINCT MONTH(starting_date) AS total_month ,COUNT(*) AS total_project  from projects  GROUP BY total_month";
            final String[] month = {"","January","February","March","April","May","June","July",
                    "August","September","October","November","December"};

            try {
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(GET_CHART_DATA);
                ResultSet resultSet = preparedStatement.executeQuery();
                XYChart.Series projectSeries = new XYChart.Series();
                while (resultSet.next()){
                    projectSeries.getData().add(new XYChart.Data(month[resultSet.getInt("total_month")], resultSet.getInt("total_project")));
                }
                projectsChart.getData().add(projectSeries);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
