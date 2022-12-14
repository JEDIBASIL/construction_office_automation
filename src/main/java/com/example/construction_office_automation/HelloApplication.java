package com.example.construction_office_automation;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        File file = new File(".panda.txt");

        FXMLLoader fxmlLoader = new FXMLLoader();

        if(file.exists()) fxmlLoader.setLocation(HelloApplication.class.getResource("sign-in.fxml"));
        else fxmlLoader.setLocation(HelloApplication.class.getResource("sign-up.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 520, 610);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        Image icon = new Image(String.valueOf(HelloApplication.class.getResource("ICONS/icons8-verified_account.png")));
        stage.getIcons().add(icon);

        stage.show();


    }


    public static void main(String[] args) {
        launch();

    }
}