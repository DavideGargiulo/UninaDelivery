package com.unina.oobd2324gr18.control;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

  private static Stage stage;

  @Override
  public void start(final Stage primaryStage) throws Exception {
    LoginControl loginControl = LoginControl.getInstance();
    stage = primaryStage;
    primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.setTitle("UninaDelivery");
    primaryStage.getIcons();

    loginControl.setScene();
  }

  public static void launchApp(final String[] args) {
    launch(args);
  }

  public static Stage getStage() {
    return stage;
  }

  public static void switchScene(final double width, final double height, final Scene scene) {
    setStageSizes(width, height);
    stage.setScene(scene);
    stage.show();
  }

  public static void setStageSizes(final double width, final double height) {
    stage.setWidth(width);
    stage.setHeight(height);
  }
}