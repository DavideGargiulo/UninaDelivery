package com.unina.oobd2324gr18.control;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  private static Stage stage;

  public static void launchApp(final String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    stage = primaryStage;
    loader.setLocation(getClass().getResource("/fxml/Login.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root, 1280, 720);

    primaryStage.setScene(scene);
    primaryStage.setTitle("Fixed Size Window Application");
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  public static Stage getStage() {
    return stage;
  }
}