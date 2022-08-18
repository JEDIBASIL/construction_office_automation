module com.example.construction_office_automation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.construction_office_automation to javafx.fxml;
    exports com.example.construction_office_automation;
}