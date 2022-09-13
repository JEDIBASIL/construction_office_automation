package com.example.construction_office_automation.controller.validator;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.example.construction_office_automation.enums.Validation.*;
import static com.example.construction_office_automation.enums.Validation.CONFIRMATION;

public class Validator {

    // VALIDATIONS
//  FUNCTION TO VALIDATE TEXT FIELD BASE ON ARGUMENTS
    public String validateTextFields(Label errorMessage, TextField field, String validationType, String fieldName, String extra){
        String emailRegex = "^(.+)@(.+)$";
        String numberRegex = "\\d+";

        if(validationType.equals(NAME.toString()) && extra == null){
            if(field.getText().trim().length() < 3) errorMessage.setText(fieldName+" must be at least 3 characters");
            else if (field.getText().length() > 25)errorMessage.setText(fieldName+" should not be more than 25 letters");
            else{
                errorMessage.setText("");
                return field.getText();
            }
        }

        if(validationType.equals(EMAIL.toString()) && extra == null){
            Pattern emailPattern = Pattern.compile(emailRegex);
            Matcher emailMatcher = emailPattern.matcher(field.getText());
            if(!emailMatcher.matches()){
                errorMessage.setText(fieldName+" is invalid");
            }else{
                errorMessage.setText("");
                return field.getText();
            }
        }

        if(validationType.equals(NUMBER.toString())) {
            Pattern numberPattern = Pattern.compile(numberRegex);
            Matcher numberMatcher = numberPattern.matcher(field.getText());

            if (numberMatcher.matches()) {
                Long text = Long.parseLong(field.getText());
                if(extra.equals(AGE.toString())){
                    if(text < 18) errorMessage.setText(fieldName+" should be above 18");

                    else if(text > 55) errorMessage.setText("workers " + fieldName + " should not be above 55");

                    else{
                        errorMessage.setText("");
                        return field.getText();
                    }
                }

                if(extra.equals(PHONE_NUMBER.toString())){
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

    public String validatePasswordFields(Label errorMessage, PasswordField field, PasswordField confirmationField, String fieldName, String extra){

        if(extra == null && confirmationField == null){
            if(field.getText().equals("") || field.getText().length() < 6) errorMessage.setText(fieldName+" should be at least 6 characters");
            else{
                errorMessage.setText("");
                return field.getText();
            }
        }
        if(extra == CONFIRMATION.toString() && confirmationField != null && field != null) {
            if (confirmationField.getText().equals("") || confirmationField.getText().length() < 6) errorMessage.setText(fieldName+" should be at least 6 characters");
            else if (!confirmationField.getText().equals(field.getText()))
                errorMessage.setText(fieldName + " do not match");
            else {
                errorMessage.setText("");
                return field.getText();
            }
        }



        return null;
    }


    //  FUNCTION TO VALIDATE CHOICE BOXS
    public String validateChoiceBox(Label errorMessage, ChoiceBox choiceBox, String choiceBoxName){
        if(choiceBox.getSelectionModel().isEmpty()){
            errorMessage.setText(choiceBoxName+" is required");
        }else{
            errorMessage.setText("");
            return choiceBox.getSelectionModel().getSelectedItem()+"";
        }
        return null;
    }

    //  FUNCTION TO VALIDATE RADIO BUTTONS
    public String validateRadioButton(Label errorMessage, ToggleGroup toggleGroup, String radioButtonName){
        String[] selected;
        if (toggleGroup.selectedToggleProperty().getValue() != null) {
            selected = toggleGroup.selectedToggleProperty().getValue().toString().split("'");
            errorMessage.setText("");
            return selected[selected.length -1];
        }else{
            errorMessage.setText(radioButtonName+" is not selected");
        }
        return null;
    }

// FUNCTION TO VALIDATE IMAGE VIEW

    public String validateImageView(Label errorMessage, ImageView imageView,String image){
        if(imageView.getImage() == null || imageView.getImage().equals("")){
            errorMessage.setText(image+ " is required");
        }else{
            errorMessage.setText("");
            return imageView.getImage().getUrl();
        }
        return null;
    }

//  FUNCTION TO VALIDATE DATE PICKER

    public LocalDate validateDatePicker (Label errorMessage,DatePicker datePicker,String datePickerName){
        datePicker.setEditable(false);
        if(datePicker.getValue() ==null) errorMessage.setText(datePickerName+" is required");
        else if(datePicker.getValue() != null && datePicker.getValue().toString().length() != 10) errorMessage.setText("invalid date format");
        else{
            errorMessage.setText("");
            return datePicker.getValue();
        }
        return null;
    }
}
