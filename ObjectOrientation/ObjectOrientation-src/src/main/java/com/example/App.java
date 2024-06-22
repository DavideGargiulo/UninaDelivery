package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the initial root from FXML
        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));

        // Initialize the scene with the loaded root
        scene = new Scene(root);

        primaryStage.setTitle("Window Title");
        primaryStage.setScene(scene);

        // Set minimum window width and height
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(720);

        primaryStage.show();
    }

    // Method to change the root of the static scene
    public static void setRoot(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        // Load the new root
        Parent root = loader.load();
        // Set the new root on the existing scene
        scene.setRoot(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}