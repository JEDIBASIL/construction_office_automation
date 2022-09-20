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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.IOException;
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
    final FileChooser fileChooser = new FileChooser();
    ObservableList<Admin> adminList = FXCollections.observableArrayList();
    ObservableList<Employees> employeesList = FXCollections.observableArrayList();
    ObservableList<Project> projectList = FXCollections.observableArrayList();
    Employees employees = new Employees();
    Project project = new Project();
    Session session = new Session();
    Validator validator = new Validator();
    Admin admin = new Admin();
    DatabaseConnection databaseConnection = new DatabaseConnection();
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
    private HBox closeModal, staticsticsContainer, projectPaginationContainer;
    @FXML

    private VBox menuContainer;
    @FXML
//   TAB LINKS
    private HBox homeTabLink, projectsTabLink, workersTabLink, settingsTabLink, logoutTabLink;
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
    private Button addWorkerTabLink;
    @FXML
//   BUTTON TO DISPLAY ADD DEPARTMENT MODAL
    private Button addDepartmentFormShow;
    @FXML

//  MODAL BOXES
    private VBox confirmationModal, addCategoryModalForm;
    @FXML
//  MODAL BOX BUTTONS
    private Button modalYesOption, modalNoOption, addDepartmentBtn;
    @FXML

    private Pagination workerPagination, projectPagination;
    @FXML
//  MODAL LABELS
    private Label modalHeading, modalSubHeading, totalWorkersLabel;
    @FXML
//  QUICK ACTION TAB LINKS
    private VBox addProjectQuickLink, addWorkerQuickLink, viewProjectsQuickLink;
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
            editGenderError,
            editAgeError,

    //    EDIT WORKER ERROR LABEL
    editPjNameError,
            editPjOwnerError,
            editPjLocationError,
            editPjDirectorError,
            editPjStartingDateError,
            editPjFinishingDateError,
            editRoleChoiceBoxError,
            editPjDescriptionError,
            editImageViewError,

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
    @FXML
//  PROJECT LABEL
    private Label
            pjName,
            pjStatus,
            loggedInUserId,
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

    private TableColumn<Employees, String>
            workerId,
            tableFirstName,
            tableSurname,
            tableOtherNames,
            tableEmail,
            tablePhoneNumber,
            tableDepartment,
            tableAge,
            tableGender;
    @FXML

    private TableView<Admin> adminTable;
    @FXML

    private TableColumn<Admin, String>
            adminUsernameCol,
            adminEmailCol,
            adminEmployeeIdCol,
            adminRoleCol,
            adminStatusCol;
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
    @FXML
//  TABLE SEARCH FIELD
    private TextField searchProjectField, searchWorkerField;
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

    addDepartmentField;
    @FXML

//   ADD ADMIN PASSWORD FILED

    private PasswordField addAdminPasswordField;
    @FXML

//  PROJECT LOCATION CHOICEBOX

    private ChoiceBox pjLocationChoiceBox;

    @FXML

    private PasswordField oldPasswordField,newPasswordField,confirmPasswordField;
    @FXML

    //  PROJECT LOCATION CHOICEBOX

    private ChoiceBox
            staffRoleChoiceBox,
            pjMangerChoiceBox,
            departmentChoiceBox,
            editDepartmentChoiceBox,
            editPjLocationChoiceBox,
            editPjManager;


//  NOTIFICATION CONTAINER
    @FXML

//  ADD ADMIN ROLE CHOICEBOX

    private ChoiceBox
            chartYearChoice,
            editRoleChoiceBox,
            adminRoleChoiceBox;
    @FXML
//  GENDER RADIO BUTTONS

    private RadioButton addWorkerMale, addWorkerFemale, editWorkerFemale, editWorkerMale;
    @FXML

//  ADD WORKER IMAGE CONTAINER

    private VBox addWorkerImgContainer, quickActionContainer;
    @FXML

//  ADD WORKER IMAGE VIEW

    private ImageView addWorkerImg, editWorkerImage;
    @FXML

    private DatePicker
            pjFinishingDatePicker,
            pjStartingDatePicker,
            editPjStartingDatePicker,
            editPjFinishingDatePicker;
    @FXML

    private TextArea pjDescription, editPjDescription;
    @FXML

    private ProgressBar projectProgressBar;
    @FXML
    private AnchorPane notificationContainer;
    @FXML

    private LineChart projectsChart;

//  ADD WORKER GENDER TOGGLE GROUP
    @FXML
    private CategoryAxis projectMonthAxis;
    @FXML
    private NumberAxis projectMonthNumberAxis;
    @FXML
    private Tab addAdminTab, adminTableTab;
    @FXML
    private TabPane settingsTab;
    private ToggleGroup addWorkerGenderGroup, editWorkerGenderGroup;
    private Validation validation;
    public AdminController() {
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        chartYearChoice.getItems().addAll(generateYear());
        chartYearChoice.setValue(LocalDate.now().getYear());
        setDepartmentChoiceBox();
        setLocationChoiceBox();
        totalWorkersLabel.setText(getTotalWorkers() + "");
        staffRoleChoiceBox.getItems().addAll("Project manager", "Project monitor");
        editRoleChoiceBox.getItems().addAll("Project manager", "Project monitor");
        adminRoleChoiceBox.getItems().addAll("Project manager", "Project monitor");
        pjMangerChoiceBox.getItems().addAll(getProjectManager());
        editPjManager.getItems().addAll(getProjectManager());


//      ADDING GENDER TO A TOGGLE GROUP

        addWorkerGenderGroup = new ToggleGroup();
        addWorkerFemale.setToggleGroup(addWorkerGenderGroup);
        addWorkerMale.setToggleGroup(addWorkerGenderGroup);

        editWorkerGenderGroup = new ToggleGroup();
        editWorkerFemale.setToggleGroup(editWorkerGenderGroup);
        editWorkerMale.setToggleGroup(editWorkerGenderGroup);

        notificationContainer.setVisible(false);
        HBox[] sideBarLinks = {homeTabLink, projectsTabLink, workersTabLink, settingsTabLink};

//      SETTING THE MODAL CONTAINER INVISIBLE
        displayModal(null);

//      ADDED AN EVENTLISTENER TO EACH OF THE SIDEBAR LINKS
        homeTabLink.setOnMouseClicked(event -> {
            switchPane(0);
            switchActiveLink(sideBarLinks, homeTabLink);
        });
        projectsTabLink.setOnMouseClicked(event -> {
            switchPane(1);
            switchActiveLink(sideBarLinks, projectsTabLink);
        });
        workersTabLink.setOnMouseClicked(event -> {
            switchPane(2);
            switchActiveLink(sideBarLinks, workersTabLink);
        });
        settingsTabLink.setOnMouseClicked(event -> {
            switchPane(3);
            switchActiveLink(sideBarLinks, settingsTabLink);
        });
        logoutTabLink.setOnMouseClicked(event -> switchSence(event, "sign-in.fxml"));
//       >>>

//       ADDED AN EVENT LISTENER ON THE CLOSE MODAL ICON

//       ADDED AN EVENT LISTENER ON THE DARKMODECHECKBOX TO TOGGLE THEME
        darkModeCheckBox.setOnAction(event -> {
            if (darkModeCheckBox.isSelected()) {
                switchTheme(true);
                darkModeCheckBox.setText("ON");
            } else {
                switchTheme(false);
                darkModeCheckBox.setText("OFF");
            }
        });

        addProjectTabLink.setOnAction(e -> switchPane(5));
        addProjectQuickLink.setOnMouseClicked(e -> {
            switchPane(5);
            switchActiveLink(sideBarLinks, projectsTabLink);

        });

        addWorkerQuickLink.setOnMouseClicked(e -> {
            switchPane(4);
            switchActiveLink(sideBarLinks, workersTabLink);
        });
        addWorkerTabLink.setOnAction(e -> {
            switchPane(4);
            switchActiveLink(sideBarLinks, workersTabLink);

        });

        viewProjectsQuickLink.setOnMouseClicked(e -> {
            switchPane(1);
            switchActiveLink(sideBarLinks, projectsTabLink);
        });

        addDepartmentFormShow.setOnAction(e -> displayModal("FORM"));


        addWorkerImgContainer.setOnMouseClicked(e -> {
            File file = displayFileChooser("Add worker image");
            if (file != null) {
                addWorkerImg.setImage(new Image(file.toURI().toString()));
                employees.setImgUrl(addWorkerImg.getImage().getUrl());
            }
        });
        editWorkerImage.setOnMouseClicked(e -> {
            File file = displayFileChooser("Add worker image");
            if (file != null) {
                editWorkerImage.setImage(new Image(file.toURI().toString()));
                employees.setImgUrl(addWorkerImg.getImage().getUrl());
            }
        });

        workersTable.setOnMouseClicked(e -> {
            if (workersTable.getSelectionModel().getSelectedIndex() != -1) {
                switchPane(6);
                displayWorker(workersTable.getSelectionModel().getSelectedItem().getId());
            } else workersTable.getSelectionModel().clearSelection();
        });
        projectTable.setOnMouseClicked(e -> {
            if (projectTable.getSelectionModel().getSelectedIndex() != -1) {
                switchPane(7);
                displayProject((int) projectTable.getSelectionModel().getSelectedItem().getProjectId());
            } else projectTable.getSelectionModel().clearSelection();
        });

        chartYearChoice.setOnAction(e -> {
            projectsChart.getData().clear();
            setProjectsChart(Integer.parseInt(chartYearChoice.getValue() + ""));
            appContainer.resize(appContainer.getWidth() + 1, appContainer.getHeight() + 1);
        });

        searchProjectField.setOnKeyPressed(e -> {
            ObservableList<Project> projectList2 = FXCollections.observableArrayList();
            for (Project project1 : projectList) {
                if (
                        project1.getProjectName().toLowerCase().contains(searchProjectField.getText().toLowerCase().trim()) ||
                                project1.getProjectOwner().toLowerCase().contains(searchProjectField.getText().toLowerCase().trim())
                ) {
                    projectList2.add(project1);
                }
                projectTable.setItems(projectList2);
            }
        });

        searchWorkerField.setOnKeyPressed(e -> {
            ObservableList<Employees> employeesList2 = FXCollections.observableArrayList();
            String madeString = "";
            for (Employees employees1 : employeesList) {
                if (
                        employees1.getFirstName().toLowerCase().contains(searchWorkerField.getText().toLowerCase().trim()) ||
                                employees1.getSurname().toLowerCase().contains(searchWorkerField.getText().toLowerCase().trim()) ||
                                employees1.getOtherNames().toLowerCase().contains(searchWorkerField.getText().toLowerCase().trim())
                ) {
                    employeesList2.add(employees1);
                }
                workersTable.setItems(employeesList2);
            }
        });

        workerPagination.setOnMouseEntered(e -> {
            System.out.println("entered");
            System.out.println(workerPagination.getCurrentPageIndex() + 1);
        });


        projectPaginationContainer.setOnMouseClicked(e -> {
            System.out.println("happy");
            System.out.println("INDEX "+projectPagination.getCurrentPageIndex());
            displayProjects(projectPagination.getCurrentPageIndex() * 10L, 10);
        });

        projectPagination.setOnKeyPressed(e-> displayProjects(projectPagination.getCurrentPageIndex() * 10L, 10));



    }

//   FUNCTION TO SHOW FILE-CHOOSER

    public void getLoggedUser(Session session1) {
        loggedInUsername.setText(session1.getUsername());
        loggedInUserId.setText(session1.getId());
        Long loggedInAdminId = Long.parseLong(loggedInUserId.getText());
        if (session1.getRole().equals("Project executive")) {
            totalProjects.setText(getTotalProject() + "");
            completedProject.setText(getTotalProject("Completed") + "");
            ongoingProject.setText(getTotalProject("Ongoing") + "");
            pendingProject.setText(getTotalProject("Pending") + "");
            setProjectsChart(Integer.parseInt(chartYearChoice.getValue() + ""));
            displayWorkers();
            displayProjects(projectPagination.getCurrentPageIndex() * 10L, 10);
            displayAdmins();
        }
        if (!session1.getRole().equals("Project executive")) {
            settingsTab.getTabs().removeAll(adminTableTab, addAdminTab);
            addDepartmentFormShow.setVisible(false);
            addProjectTabLink.setVisible(false);
            totalProjects.setText(getTotalProject(loggedInAdminId) + "");
            completedProject.setText(getTotalProject("Completed", loggedInAdminId) + "");
            ongoingProject.setText(getTotalProject("Ongoing", loggedInAdminId) + "");
            pendingProject.setText(getTotalProject("Pending", loggedInAdminId) + "");
            setProjectsChart(Integer.parseInt(chartYearChoice.getValue() + ""), loggedInAdminId);
        }

        if (session1.getRole().equals("Project manager")) {

        }
        if (session1.getRole().equals("Project monitor")) {
            menuContainer.getChildren().remove(workersTabLink);
            appTabPane.setFocusTraversable(false);
            staticsticsContainer.getChildren().remove(quickActionContainer);
//            workersTabLink.setVisible(false);
        }

    }

    public File displayFileChooser(String title) {
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Images", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        return file;
    }

//   FUNCTION TO CHANGE THE NAME OF THE CURRENT TAB

    //   METHOD TO SWITCH APP TABPANE
    public void switchPane(int tab) {
        appTabPane.getSelectionModel().select(tab);
    }

    public void switchSence(Event event, String fxml) {

        try {
            FXMLLoader pane = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(pane.load(), 520, 610);
            stage.setScene(scene);
            if (fxml.equals("sign-in.fxml") || fxml.equals("sign-out.fxml")) {
                stage.setResizable(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //   METHOD TO SWITCH TO DARK MODE
    public void switchTheme(boolean theme) {
        if (theme) {
            appContainer.getStylesheets().add(1, String.valueOf(HelloApplication.class.getResource("styles/dark-mode.css")));
        } else {
            appContainer.getStylesheets().remove(1);

        }
    }

    //  ALGORITHM TO SWITCH PANE ON CLICK OF EACH ITEM IN THE SIDEBAR
    public void switchActiveLink(HBox[] links, HBox activeLink) {

        if (activeLink == null) {
            for (HBox link : links) {
                link.getStyleClass().remove("active");
            }
        } else {
            for (HBox link : links) {
                link.getStyleClass().remove("active");
            }
            activeLink.getStyleClass().add("active");
        }
    }

    // FUNCTION TO ADD DATA TO DEPARTMENT CHOICEBOX
    public void setDepartmentChoiceBox() {
        editDepartmentChoiceBox.getItems().clear();
        departmentChoiceBox.getItems().clear();
        editDepartmentChoiceBox.getItems().addAll(getDepartments());
        departmentChoiceBox.getItems().addAll(getDepartments());
    }

    // FUNCTION TO ADD DATA TO DEPARTMENT CHOICEBOX
    public void setLocationChoiceBox() {
        pjLocationChoiceBox.getItems().addAll(getLocations());
        editPjLocationChoiceBox.getItems().addAll(getLocations());
    }

    // FUNCTION TO DISPLAY MODAL
    public void displayModal(String displayType) {
        if (displayType != null) {
            modalContainer.setVisible(true);
            if (displayType.equals("FORM")) {
                confirmationModal.setMaxHeight(0);
                confirmationModal.setMinHeight(0);
                confirmationModal.setVisible(false);
                new BounceInDown(addCategoryModalForm).play();
                addDepartmentField.requestFocus();
            }
        } else {
            confirmationModal.setMaxHeight(250);
            confirmationModal.setMinHeight(250);
            addCategoryModalForm.setMaxHeight(250);
            addCategoryModalForm.setMinHeight(250);
            confirmationModal.setVisible(true);
            addCategoryModalForm.setVisible(true);
            modalContainer.setVisible(false);
        }
    }

//   FUNCTION TO DISPLAY TOAST

    public void displayModal(String heading, String subHeading, String type) {
        modalHeading.setText(heading);
        modalSubHeading.setText(subHeading);
        addCategoryModalForm.setMaxHeight(0);
        addCategoryModalForm.setMinHeight(0);
        addCategoryModalForm.setVisible(false);
        new BounceInDown(confirmationModal).play();
        modalContainer.setVisible(true);
        if (type == null) {
            modalYesOption.setMaxWidth(0);
            modalYesOption.setPadding(new Insets(0, 0, 0, 0));
            modalYesOption.setVisible(false);
        } else {
            modalYesOption.setMaxWidth(150);
            modalYesOption.setVisible(true);
            modalYesOption.setText(type.toLowerCase());
        }
    }

    public void toast(String title, String message) {
        Notifications notificationsBuilder = Notifications.create()
                .title(title)
                .text(message)
                .graphic(null)
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_LEFT);
        notificationsBuilder.showConfirm();
    }

    @FXML
    protected void onAddWorkerClicked() {
        employees.setFirstName(validator.validateTextFields(firstNameError, firstNameField, NAME.toString(), "First name", null));
        employees.setSurname(validator.validateTextFields(surnameError, surnameField, NAME.toString(), "Surname", null));
        employees.setOtherNames(validator.validateTextFields(otherNamesError, otherNamesField, NAME.toString(), "Other names", null));
        employees.setEmail(validator.validateTextFields(emailError, emailField, EMAIL.toString(), "Email", null));
        employees.setDepartment(validator.validateChoiceBox(departmentError, departmentChoiceBox, "Department"));
        employees.setAge(Integer.parseInt(validator.validateTextFields(ageError, ageField, NUMBER.toString(), "Age", AGE.toString())));
        employees.setPhoneNumber(Long.parseLong(validator.validateTextFields(phoneNumberError, phoneNumberField, NUMBER.toString(), "Phone number", PHONE_NUMBER.toString())));
        employees.setGender(validator.validateRadioButton(genderError, addWorkerGenderGroup, "Gender"));
        employees.setRole(validator.validateChoiceBox(staffRoleError, staffRoleChoiceBox, "Role"));
        employees.setImgUrl(validator.validateImageView(addWorkerImgError, addWorkerImg, "Image"));
        if (employees.validateFields()) {
            if (!searchWorker(employees.getEmail())) {
                if (addWorker(employees)) {
                    toast("Success", "worker is added");
                    employeesList.setAll();
                    displayWorkers();
                } else toast("Error", "something went wrong");
            } else displayModal("Error", "Worker with " + employees.getEmail() + " already exist", null);
        }
    }

    @FXML
    protected void onAddProjectClicked() {
        project.setProjectName(validator.validateTextFields(pjNameError, pjNameField, NAME.toString(), "Project name", null));
        project.setProjectOwner(validator.validateTextFields(pjOwnerError, pjOwnerField, NAME.toString(), "Project owner", null));
        project.setStartingDate(validator.validateDatePicker(pjStartingDateError, pjStartingDatePicker, "Starting date"));
        project.setFinishingDate(validator.validateDatePicker(pjFinishingDateError, pjFinishingDatePicker, "Finishing date"));
        project.setProjectLocation(validator.validateChoiceBox(pjLocationError, pjLocationChoiceBox, "Location"));
        project.setProjectManager(validator.validateChoiceBox(pjDirectorError, pjMangerChoiceBox, "Project manager"));
        project.setProjectDescription(validator.validateTextArea(pjDescriptionError, pjDescription, "Project Description"));
        project.setProjectMonitor("NONE");
        project.setProjectStatus("Pending");
        project.setProjectProgress(0);
        if (project.getStartingDate().equals(LocalDate.now()))
            displayModal("Error", "project starting date can't be today", null);
        else if (project.checkFields()) {
            if (addProject(project)) {
                toast("Success", "Project added successfully");
                displayProjects(projectPagination.getCurrentPageIndex() * 10L, 10);
                switchPane(1);
                projectsChart.getData().clear();
                setProjectsChart(Integer.parseInt(chartYearChoice.getValue() + ""));
            } else {
                toast("Error", "an error occured");
            }
        }

    }

    @FXML

    protected void onEditProjectDetailsClicked() {
        project.setProjectName(validator.validateTextFields(editPjNameError, editPjNameField, NAME.toString(), "Project name", null));
        project.setProjectOwner(validator.validateTextFields(editPjOwnerError, editPjOwnerField, NAME.toString(), "Project owner", null));
        project.setProjectLocation(validator.validateChoiceBox(editPjLocationError, editPjLocationChoiceBox, "Project location"));
        project.setProjectManager(validator.validateChoiceBox(editPjDirectorError, editPjManager, "Project manager"));
        project.setStartingDate(validator.validateDatePicker(editPjStartingDateError, editPjStartingDatePicker, "Starting date"));
        project.setFinishingDate(validator.validateDatePicker(editPjFinishingDateError, editPjFinishingDatePicker, "Finishing date"));

        if (projectTable.getSelectionModel().getSelectedItem() != null) {
            if (project.checkFields("DETAILS"))
                if (updateProjectDetails(project, (int) projectTable.getSelectionModel().getSelectedItem().getProjectId())) {
                    toast("Success", "Project edited successfully");
                    displayProjects(projectPagination.getCurrentPageIndex() * 10L, 10);
                    projectsChart.getData().clear();
                    setProjectsChart(Integer.parseInt(chartYearChoice.getValue() + ""));
                } else {
                    toast("Error", "an error occurred");
                }
        }

    }

    @FXML
    protected void onEditWorkerClicked() {
        employees.setFirstName(validator.validateTextFields(editFirstNameError, editFirstNameField, NAME.toString(), "First name", null));
        employees.setSurname(validator.validateTextFields(editSurnameError, editSurnameField, NAME.toString(), "Surname", null));
        employees.setOtherNames(validator.validateTextFields(editOtherNamesError, editOtherNamesField, NAME.toString(), "Other names", null));
        employees.setEmail(validator.validateTextFields(editEmailError, editEmailField, EMAIL.toString(), "Email", null));
        employees.setDepartment(validator.validateChoiceBox(editDepartmentError, editDepartmentChoiceBox, "Department"));
        employees.setAge(Integer.parseInt(validator.validateTextFields(editAgeError, editAgeField, NUMBER.toString(), "Age", AGE.toString())));
        employees.setPhoneNumber(Long.parseLong(validator.validateTextFields(editPhoneNumberError, editPhoneNumberField, NUMBER.toString(), "Phone number", PHONE_NUMBER.toString())));
        employees.setImgUrl(validator.validateImageView(editImageViewError, editWorkerImage, "Photo"));
        employees.setGender(validator.validateRadioButton(editGenderError, editWorkerGenderGroup, "Gender"));
        employees.setRole(validator.validateChoiceBox(editRoleChoiceBoxError, editRoleChoiceBox, "Role"));
        if (employees.validateFields()) {
            if (updateWorkerDetails(employees, workersTable.getSelectionModel().getSelectedItem().getId())) {
                toast("Success", "worker edite successfully");
                employeesList.setAll();
                displayWorkers();
            } else toast("Error", "something went wrong");
        }
    }

    @FXML
    protected void onAddAdminClicked() {
        admin.setId(validator.validateTextFields(addAdminIdError, addAdminIdField, NAME.toString(), "Employee id", null));
        admin.setUsername(validator.validateTextFields(addAdminUsernameError, addAdminField, NAME.toString(), "Username", null));
        admin.setRole(validator.validateChoiceBox(adminRoleError, adminRoleChoiceBox, "Role"));
        admin.setPassword(validator.validatePasswordFields(addAdminPasswordError, addAdminPasswordField, null, "Password", null));
        admin.setEmail("");
        admin.setStatus("Active");
        if (admin.validateFields()) {
            admin.setId(admin.getId().toLowerCase().replace("pj", ""));
            if (!searchWorker(Integer.parseInt(admin.getId())))
                displayModal("Error", "Worker with id " + admin.getId() + " does not exist", null);
            else if (searchAdmin(Integer.parseInt(admin.getId())))
                displayModal("Error", "Administrator with id " + admin.getId() + " already exist", null);
            else if (searchAdmin(admin.getUsername(), "USERNAME"))
                displayModal("Error", "Worker with username " + admin.getUsername() + " already  exist", null);
            else {
                if (addAdmin(admin)) {
                    toast("Success", "Admin added successfully");
                    adminList.setAll();
                    displayAdmins();

                }
            }
        }
    }

    @FXML
    protected void onDeleteWorker() {
        if (workersTable.getSelectionModel().getSelectedItem() != null)
            displayModal("Risky", "are you sure you want to delete PAN" + workersTable.getSelectionModel().getSelectedItem().getId(), "DELETE");
    }

    @FXML
    protected void onTerminateProject() {
        if (projectTable.getSelectionModel().getSelectedItem() != null)
            displayModal("Risky", "are you sure you want to terminate " + projectTable.getSelectionModel().getSelectedItem().getProjectName(), "TERMINATE");

    }

    @FXML

    protected void onTerminateBtnClick() {
        displayModal(null);
        if (modalYesOption.getText().equals("DELETE".toLowerCase())) {
            if (deleteWorker(workersTable.getSelectionModel().getSelectedItem().getId())) {
                toast("Success", "worker deleted");
                employeesList.setAll();
                displayWorkers();
                switchPane(2);
            }
        } else if (modalYesOption.getText().equals("TERMINATE".toLowerCase())) {
            if (terminateProject((int) projectTable.getSelectionModel().getSelectedItem().getProjectId())) {
                toast("Success", "project terminated");
                displayProjects(projectPagination.getCurrentPageIndex() * 10L, 10);
                switchPane(1);
                projectsChart.getData().clear();
                setProjectsChart(Integer.parseInt(chartYearChoice.getValue() + ""));
            }
        }
    }

    @FXML
    protected void onChangePasswordClicked(){
        EncryptPassword encryptPassword = new EncryptPassword();
        String adminPassword = validator.validatePasswordFields(oldPasswordError,oldPasswordField,null,"Password",null);
        String adminNewPassword = validator.validatePasswordFields(newPasswordError,newPasswordField,null,"Password",null);
        admin.setPassword(validator.validatePasswordFields(confirmNewPasswordError,confirmPasswordField,newPasswordField,"Password",CONFIRMATION.toString()));
        if(admin.getPassword() !=null){
            if(confirmPassword(Long.parseLong(loggedInUserId.getText()),adminPassword)){
                if(changePassword(Long.parseLong(loggedInUserId.getText()),admin.getPassword()))
                    toast("Message","your password has been changed");
            }else{
                displayModal("Error","wrong password",null);
            }
        }

    }

    @FXML

    protected void projectPanginationOnClick() {
        System.out.println("clicked");
        displayProjects(projectPagination.getCurrentPageIndex() * 10L, 10);
    }

    @FXML
    protected void onModalClose() {
        displayModal(null);
    }


//  DATABASE MANIPULATIONS

    @FXML
    protected void onAddDepartmentClicked() {
        employees.setDepartment(validator.validateTextFields(addDepartmentError, addDepartmentField, NAME.toString(), "Department", null));
        if (employees.getDepartment() != null) {
            if (addDepartment(employees)) {
                displayModal(null);
                toast("Success", "Department added");
                setDepartmentChoiceBox();
            }
        }
    }

    //  RETRIEVE LOCATIONS FROM DATABASE
    public List getLocations() {
        ArrayList locationList = new ArrayList();
        if (databaseConnection.dbConnect()) {
            String GET_LOCATIONS = "SELECT DISTINCT name FROM location";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_LOCATIONS);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String location = resultSet.getString("name");
                    locationList.add(location);
                }
            } catch (SecurityException | SQLException se) {
                se.printStackTrace();
            }
        }
        return locationList;
    }

//  ADD NEW DEPARTMENT TO DATABASE

    public boolean addDepartment(Employees employees) {
        if (databaseConnection.dbConnect()) {
            String ADD_DEPARTMENT = "INSERT INTO department (name) VALUES(?)";
            try {
                int upd = 0;
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(ADD_DEPARTMENT);
                preparedStatement.setString(1, employees.getDepartment());
                upd = preparedStatement.executeUpdate();
                if (upd != 0) return true;
            } catch (SecurityException | SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }

    //  RETRIEVE DEPARTMENTS FROM DATABASE
    public List getDepartments() {
        ArrayList departmentList = new ArrayList();
        if (databaseConnection.dbConnect()) {
            String GET_LOCATIONS = "SELECT name FROM department";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_LOCATIONS);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String location = resultSet.getString("name");
                    departmentList.add(location);
                }
            } catch (SecurityException | SQLException se) {
                se.printStackTrace();
            }
        }
        return departmentList;
    }

//    SEARCH WORKER TABLE

    //    SEARCH ADMIN TABLE BASE ON EMPLOYEE ID
    public boolean searchAdmin(Integer id) {
        if (databaseConnection.dbConnect()) {
            String SEARCH_ADMIN = "SELECT * FROM admin WHERE employee_id = ?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SEARCH_ADMIN);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) return true;
            } catch (SQLException | SecurityException se) {
                se.printStackTrace();
            }
        }
        return false;
    }

    //    SEARCH WORKER TABLE BASE ON EMAIL
    public boolean searchAdmin(String query, String type) {
        if (databaseConnection.dbConnect()) {
            String SEARCH_ADMIN;
            if (type.equals("EMAIL")) {
                SEARCH_ADMIN = "SELECT * FROM workers WHERE email = ?";
                try {
                    PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SEARCH_ADMIN);
                    preparedStatement.setString(1, query);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) return true;

                } catch (SQLException | SecurityException se) {
                    se.printStackTrace();
                }
            }
            if (type.equals("USERNAME")) {
                SEARCH_ADMIN = "SELECT * FROM admin WHERE username = ?";
                try {
                    PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SEARCH_ADMIN);
                    preparedStatement.setString(1, query);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) return true;

                } catch (SQLException | SecurityException se) {
                    se.printStackTrace();
                }
            }
        }
        return false;
    }

    //    SEARCH WORKER TABLE BASE ON ID
    public boolean searchWorker(Integer id) {
        if (databaseConnection.dbConnect()) {
            String SEARCH_WORKER = "SELECT * FROM workers WHERE id = ?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SEARCH_WORKER);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    admin.setEmail(resultSet.getString("email"));
                    return true;
                }

            } catch (SQLException | SecurityException se) {
                se.printStackTrace();
            }
        }
        return false;
    }

    //    SEARCH WORKER TABLE BASE ON EMAIL
    public boolean searchWorker(String email) {
        if (databaseConnection.dbConnect()) {
            String SEARCH_WORKER = "SELECT * FROM workers WHERE email = ?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SEARCH_WORKER);
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) return true;

            } catch (SQLException | SecurityException se) {
                se.printStackTrace();
            }
        }
        return false;
    }

    //    ADD ADMIN
    public boolean addAdmin(Admin admin) {
        if (databaseConnection.dbConnect()) {
            EncryptPassword encryptPassword = new EncryptPassword();
            String ADD_ADMIN = "INSERT INTO admin (username,email,employee_id,role,password,status) VALUES(?,?,?,?,?,?)";
            int upd = 0;
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(ADD_ADMIN);
                preparedStatement.setString(1, admin.getUsername());
                preparedStatement.setString(2, admin.getEmail());
                preparedStatement.setString(3, admin.getId());
                preparedStatement.setString(4, admin.getRole());
                preparedStatement.setString(5, encryptPassword.EncryptPassword(admin.getPassword()));
                preparedStatement.setString(6, admin.getStatus());
                upd = preparedStatement.executeUpdate();
                if (upd != 0) return true;
            } catch (SQLException | SecurityException se) {
                se.printStackTrace();
            }
        }
        return false;
    }

    public boolean addWorker(Employees employees) {
        if (databaseConnection.dbConnect()) {
            String ADD_WORKER = "INSERT INTO WORKERS(first_name,surname,other_name,email,department,phone_number,age,gender,role,photo) VALUES(?,?,?,?,?,?,?,?,?,?)";
            try {
                int upd = 0;

                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(ADD_WORKER);
                preparedStatement.setString(1, employees.getFirstName());
                preparedStatement.setString(2, employees.getSurname());
                preparedStatement.setString(3, employees.getOtherNames());
                preparedStatement.setString(4, employees.getEmail());
                preparedStatement.setString(5, employees.getDepartment());
                preparedStatement.setString(6, employees.getPhoneNumber() + "");
                preparedStatement.setInt(7, employees.getAge());
                preparedStatement.setString(8, employees.getGender());
                preparedStatement.setString(9, employees.getRole());
                preparedStatement.setString(10, employees.getImgUrl());

                upd = preparedStatement.executeUpdate();

                if (upd != 0) return true;
            } catch (SecurityException | SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }

    public void displayWorkers() {
        if (databaseConnection.dbConnect()) {
            String GET_ALL_WORKERS = "SELECT * FROM WORKERS";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_ALL_WORKERS);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
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
            } catch (SQLException | SecurityException se) {
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

    public void displayAdmins() {
        if (databaseConnection.dbConnect()) {
            String GET_ALL_ADMIN = "SELECT * FROM admin";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_ALL_ADMIN);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    adminList.add(new Admin(
                            resultSet.getString("username"),
                            resultSet.getString("email"),
                            resultSet.getString("employee_id"),
                            resultSet.getString("role"),
                            resultSet.getString("status")
                    ));
                }
            } catch (SQLException | SecurityException se) {
                se.printStackTrace();
            }

            adminUsernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
            adminEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            adminEmployeeIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            adminRoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
            adminStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

            adminTable.setItems(adminList);
        }
    }

    public boolean addProject(Project project) {
        if (databaseConnection.dbConnect()) {
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

            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(ADD_PROJECT);
                preparedStatement.setString(1, project.getProjectName());
                preparedStatement.setString(2, project.getProjectOwner());
                preparedStatement.setString(3, project.getProjectLocation());
                preparedStatement.setString(4, project.getProjectManager());
                preparedStatement.setString(5, project.getProjectMonitor());
                preparedStatement.setString(6, project.getProjectDescription());
                preparedStatement.setString(7, project.getProjectStatus());
                preparedStatement.setInt(8, project.getProjectProgress());
                preparedStatement.setDate(9, Date.valueOf(project.getStartingDate()));
                preparedStatement.setDate(10, Date.valueOf(project.getFinishingDate()));


                int upd = 0;
                upd = preparedStatement.executeUpdate();
                if (upd != 0) return true;
            } catch (SecurityException | SQLException se) {
                se.printStackTrace();
            }
        }

        return false;
    }

    public void displayWorker(Integer id) {
        Employees employees = new Employees();
        if (databaseConnection.dbConnect()) {
            String GET_WORKER = "SELECT * FROM workers WHERE id = ?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_WORKER);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    employees.setId(resultSet.getInt("id"));
                    employees.setFirstName(resultSet.getString("first_name"));
                    employees.setSurname(resultSet.getString("surname"));
                    employees.setOtherNames(resultSet.getString("other_name"));
                    employees.setEmail(resultSet.getString("email"));
                    employees.setDepartment(resultSet.getString("department"));
                    employees.setPhoneNumber((resultSet.getLong("phone_number")));
                    employees.setAge(resultSet.getInt("age"));
                    employees.setGender(resultSet.getString("gender"));
                    employees.setImgUrl(resultSet.getString("photo"));
                    employees.setRole(resultSet.getString("role"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            editFirstNameField.setText(employees.getFirstName());
            editSurnameField.setText(employees.getSurname());
            editOtherNamesField.setText(employees.getOtherNames());
            editEmailField.setText(employees.getEmail());
            editAgeField.setText(employees.getAge() + "");
            editPhoneNumberField.setText(employees.getPhoneNumber() + "");
            editFirstNameField.setText(employees.getFirstName());
            editDepartmentChoiceBox.setValue(employees.getDepartment());
            editRoleChoiceBox.setValue(employees.getRole());
            editWorkerImage.setImage(new Image(employees.getImgUrl()));
            if (employees.getGender().equalsIgnoreCase("male")) editWorkerMale.setSelected(true);
            if (employees.getGender().equalsIgnoreCase("female")) editWorkerFemale.setSelected(true);

        }
    }

    public boolean deleteWorker(Integer id) {
        if (databaseConnection.dbConnect()) {
            String DELETE_WORKER = "DELETE FROM workers WHERE id =?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_WORKER);
                preparedStatement.setInt(1, id);
                int delete = preparedStatement.executeUpdate();
                if (delete != 0) return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public List getProjectManager() {
        ArrayList projectMangerList = new ArrayList();
        if (databaseConnection.dbConnect()) {
            String GET_PROJECT_MANAGERS = "SELECT id FROM workers WHERE role = ?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_PROJECT_MANAGERS);
                preparedStatement.setString(1, "Project manager");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Integer manager = resultSet.getInt("id");
                    projectMangerList.add("PAN" + manager);
                }
            } catch (SecurityException | SQLException se) {
                se.printStackTrace();
            }
        }
        return projectMangerList;
    }

    public void displayProjects(long offset, long limit) {
        if (databaseConnection.dbConnect()) {
            String GET_ALL_PROJECTS = "SELECT * FROM projects LIMIT ? OFFSET ?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_ALL_PROJECTS);
                preparedStatement.setLong(1, limit);
                preparedStatement.setLong(2, offset);
                ResultSet resultSet = preparedStatement.executeQuery();

                projectList.setAll();
                while (resultSet.next()) {
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
            } catch (SQLException | SecurityException se) {
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

    public boolean terminateProject(Integer id) {
        if (databaseConnection.dbConnect()) {
            String TERMINATE_PROJECT = "DELETE FROM projects WHERE project_id =?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(TERMINATE_PROJECT);
                preparedStatement.setInt(1, id);
                int terminate = preparedStatement.executeUpdate();
                if (terminate != 0) return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public void displayProject(int id) {
        if (databaseConnection.dbConnect()) {
            String GET_PROJECT = "SELECT * FROM projects WHERE project_id = ?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_PROJECT);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            pjName.setText(project.getProjectName());
            pjStatus.setText(project.getProjectStatus());
            editPjNameField.setText(project.getProjectName());
            editPjOwnerField.setText(project.getProjectOwner());
            projectProgress.setText(project.getProjectProgress() + "%");
            editPjLocationChoiceBox.setValue(project.getProjectLocation());
            editPjStartingDatePicker.setValue(project.getStartingDate());
            editPjFinishingDatePicker.setValue(project.getFinishingDate());
            editPjManager.setValue(project.getProjectManager());
            projectProgressBar.setProgress(Double.parseDouble("0." + project.getProjectProgress()));
            editPjStartingDatePicker.setDisable(!project.getProjectStatus().equals("Pending"));
        }
    }

    public Long getTotalProject() {
        long result = 0L;
        if (databaseConnection.dbConnect()) {
            String COUNT_WORKERS = "SELECT COUNT(*) as total FROM projects";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(COUNT_WORKERS);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) result = resultSet.getInt("total");
            } catch (SQLException | SecurityException se) {
                se.printStackTrace();
            }
        }
        return result;
    }

    public Long getTotalProject(long admin) {
        long result = 0L;
        if (databaseConnection.dbConnect()) {
            String COUNT_WORKERS = "SELECT COUNT(*) as total FROM projects WHERE project_manger = ? OR project_monitor = ?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(COUNT_WORKERS);
                preparedStatement.setString(1, "PAN" + admin);
                preparedStatement.setString(2, "PAN" + admin);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) result = resultSet.getInt("total");
            } catch (SQLException | SecurityException se) {
                se.printStackTrace();
            }
        }
        return result;
    }

    public Long getTotalProject(String status) {
        long result = 0L;
        if (databaseConnection.dbConnect()) {
            String COUNT_WORKERS = "SELECT COUNT(*) as total FROM projects WHERE project_status = ?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(COUNT_WORKERS);
                preparedStatement.setString(1, status);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) result = resultSet.getInt("total");
            } catch (SQLException | SecurityException se) {
                se.printStackTrace();
            }
        }
        return result;
    }

    public Long getTotalProject(String status, long admin) {
        long result = 0L;
        if (databaseConnection.dbConnect()) {
            String COUNT_WORKERS = "SELECT COUNT(*) as total FROM projects WHERE  project_manger = ? AND project_status = ?  OR project_monitor = ? ";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(COUNT_WORKERS);
                preparedStatement.setString(1, "PAN" + admin);
                preparedStatement.setString(2, status);
                preparedStatement.setString(3, "PAN" + admin);


                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) result = resultSet.getInt("total");
            } catch (SQLException | SecurityException se) {
                se.printStackTrace();
            }
        }
        return result;
    }

    public Long getTotalWorkers() {
        long result = 0L;
        if (databaseConnection.dbConnect()) {
            String COUNT_WORKERS = "SELECT COUNT(*) as total FROM workers";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(COUNT_WORKERS);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) result = resultSet.getInt("total");
            } catch (SQLException | SecurityException se) {
                se.printStackTrace();
            }
        }
        return result;
    }


    public boolean updateProjectDetails(Project project, int projectId) {
        if (databaseConnection.dbConnect()) {
            int upd = 0;
            String UPDATE_PROJECT_DETAILS = "UPDATE projects " +
                    "SET project_name = ?," +
                    "project_owner = ?," +
                    "project_location = ?," +
                    "project_manger = ?," +
                    "project_description = ?," +
                    "starting_date = ?," +
                    "finishing_date = ? " +
                    " WHERE  PROJECT_ID  = ?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_PROJECT_DETAILS);
                preparedStatement.setString(1, project.getProjectName());
                preparedStatement.setString(2, project.getProjectOwner());
                preparedStatement.setString(3, project.getProjectLocation());
                preparedStatement.setString(4, project.getProjectManager());
                preparedStatement.setString(5, project.getProjectDescription());
                preparedStatement.setDate(6, Date.valueOf(project.getStartingDate()));
                preparedStatement.setDate(7, Date.valueOf(project.getFinishingDate()));
                preparedStatement.setInt(8, projectId);
                upd = preparedStatement.executeUpdate();
                if (upd != 0) return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public boolean updateProjectProgress(int projectId) {
        if (databaseConnection.dbConnect()) {
            int upd = 0;
            String UPDATE_PROJECT_PROGRESS = "UPDATE projects " +
                    "SET project_progress = ?," +
                    " WHERE  PROJECT_ID  = ?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_PROJECT_PROGRESS);
                preparedStatement.setInt(1, project.getProjectProgress());
                preparedStatement.setInt(8, projectId);
                upd = preparedStatement.executeUpdate();
                if (upd != 0) return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public void setProjectsChart(int year) {
        if (databaseConnection.dbConnect()) {
            String GET_CHART_DATA = "SELECT DISTINCT MONTH(starting_date) AS total_month ,COUNT(*) AS total_project  from projects WHERE YEAR(starting_date) = ?   GROUP BY total_month";
            final String[] month = {"", "January", "February", "March", "April", "May", "June", "July",
                    "August", "September", "October", "November", "December"};

            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_CHART_DATA);
                preparedStatement.setInt(1, year);
                ResultSet resultSet = preparedStatement.executeQuery();
                XYChart.Series projectSeries = new XYChart.Series();
                while (resultSet.next()) {
                    projectSeries.getData().add(new XYChart.Data(month[resultSet.getInt("total_month")], resultSet.getInt("total_project")));
                }
                projectsChart.getData().add(projectSeries);
                projectsChart.setTitle("Monthly project statistics for " + year);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setProjectsChart(int year, long admin) {
        if (databaseConnection.dbConnect()) {
            String GET_CHART_DATA = "SELECT DISTINCT MONTH(starting_date) AS total_month ,COUNT(*) AS total_project  from projects WHERE YEAR(starting_date) = ? AND project_manger = ? OR project_monitor = ? GROUP BY total_month";
            final String[] month = {"", "January", "February", "March", "April", "May", "June", "July",
                    "August", "September", "October", "November", "December"};

            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_CHART_DATA);
                preparedStatement.setInt(1, year);
                preparedStatement.setString(2, "PAN" + admin);
                preparedStatement.setString(3, "PAN" + admin);
                ResultSet resultSet = preparedStatement.executeQuery();
                XYChart.Series projectSeries = new XYChart.Series();
                while (resultSet.next()) {
                    projectSeries.getData().add(new XYChart.Data(month[resultSet.getInt("total_month")], resultSet.getInt("total_project")));
                }
                projectsChart.getData().add(projectSeries);
                projectsChart.setTitle("Monthly project statistics for " + year);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public List generateYear() {
        List yearList = new ArrayList();
        int gottenYear = LocalDate.now().getYear() - 2;
        int counter = 0;
        while (counter < 100) {
            counter++;
            yearList.add(gottenYear + counter);
        }
        return yearList;
    }

    public boolean updateWorkerDetails(Employees employees, int id) {
        if (databaseConnection.dbConnect()) {
            String UPDATE_WORKER_DETAILS = "UPDATE workers" +
                    " " +
                    "SET " +
                    "first_name = ?," +
                    "surname = ?," +
                    "email = ?," +
                    "department = ?," +
                    "phone_number = ?," +
                    "age = ?," +
                    "gender = ?," +
                    "role = ?," +
                    "photo = ?" +
                    "WHERE id = ?";
            try {
                PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_WORKER_DETAILS);
                preparedStatement.setString(1, employees.getFirstName());
                preparedStatement.setString(2, employees.getSurname());
                preparedStatement.setString(3, employees.getEmail());
                preparedStatement.setString(4, employees.getDepartment());
                preparedStatement.setLong(5, employees.getPhoneNumber());
                preparedStatement.setInt(6, employees.getAge());
                preparedStatement.setString(7, employees.getGender());
                preparedStatement.setString(8, employees.getRole());
                preparedStatement.setString(9, employees.getImgUrl());
                preparedStatement.setInt(10, id);
                int upd = preparedStatement.executeUpdate();
                if (upd != 0) return true;

            } catch (SQLException | SecurityException se) {
                se.printStackTrace();
            }
        }
        return false;
    }

    public boolean changePassword(Long id,String newPassword){
        EncryptPassword encryptPassword = new EncryptPassword();
        if(databaseConnection.dbConnect()){
            String UPDATE_PASSWORD = "UPDATE admin SET password = ? WHERE employee_id = ?";
            try {
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(UPDATE_PASSWORD);
                preparedStatement.setString(1,encryptPassword.EncryptPassword(newPassword));
                preparedStatement.setLong(2,id);
                int upd = preparedStatement.executeUpdate();
                if(upd !=0) return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    public boolean confirmPassword(Long id,String password){
        EncryptPassword encryptPassword = new EncryptPassword();
        if(databaseConnection.dbConnect()){
            String CONFIRM_PASSWORD = "SELECT * FROM admin WHERE employee_id = ? AND password = ?";
            try {
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(CONFIRM_PASSWORD);
                preparedStatement.setLong(1,id);
                preparedStatement.setString(2,encryptPassword.EncryptPassword(password));
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }


}
