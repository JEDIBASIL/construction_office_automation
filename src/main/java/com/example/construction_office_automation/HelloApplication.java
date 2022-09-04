package com.example.construction_office_automation;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sign-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Hello!");
        System.out.println(scene.getRoot());
        stage.setScene(scene);
        Image icon = new Image(String.valueOf(HelloApplication.class.getResource("ICONS/icons8-verified_account.png")));
        stage.getIcons().add(icon);

        stage.show();
        System.out.println(stage);


    }


    public static void main(String[] args) {
        launch();

    }
}