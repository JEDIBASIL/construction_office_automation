module com.example.construction_office_automation {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;
    requires java.sql;
    requires org.controlsfx.controls;


    opens com.example.construction_office_automation to javafx.fxml;
    exports com.example.construction_office_automation;
    exports com.example.construction_office_automation.controller;
    opens com.example.construction_office_automation.controller to javafx.fxml;
}