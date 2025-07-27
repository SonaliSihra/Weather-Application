package com.example.weatherapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.weatherapplication.ReadConfigMain.getPropertiesConfig;

public class HelloApplication extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
//        //StackPane root = new StackPane();
////        Scene scene = new Scene(root, 650, 650);
////        //scene.getStylesheets().add("com/example/weatherapplication/application.css");
////        fxmlLoader.setRoot(root);
////        root.setStyle(
////                "-fx-background-image:                                                url('src/main/resources/images/sleet-pictures.jpg'); -fx-background-repeat: no-repeat; -fx-background-size: 1920 1080; -fx-background-position: center center;");
//        stage.setScene(scene);
//        //scene.setRoot(root);
////        root.setStyle(
////                "-fx-background-image:                                                url('src/main/resources/images/sleet-pictures.jpg');");
////        scene.setRoot(root);
//        stage.setTitle("Hello!");
//        stage.show();
//    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        getPropertiesConfig();
        Scene scene = new Scene(root, 1000, 700);

        stage.setTitle("Weather Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}