package com.unina.oobd2324gr18.boundary;

import com.unina.oobd2324gr18.control.App;
import com.unina.oobd2324gr18.control.BasicControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.stage.Stage;

public abstract class BasePageController<T extends BasicControl> {

  private T control;

  private static final int ICON_SIZE = 30;

  @FXML
  private Node titleBar;
  @FXML
  private Button exitButton;

  public void init(final T control) {
    this.control = control;
    initialize(control);
    makeWindowDraggable();
  }

  protected abstract void initialize(T control);

  protected T getControl() {
    return control;
  }

  private void makeWindowDraggable() {
    Stage stage = App.getStage();
    final double[] mousePosition = new double[2];

    titleBar.setOnMousePressed(event -> {
      mousePosition[0] = event.getSceneX();
      mousePosition[1] = event.getSceneY();
    });

    titleBar.setOnMouseDragged(event -> {
      if (stage.isMaximized()) {
        stage.setMaximized(false);
      }
      stage.setX(event.getScreenX() - mousePosition[0]);
      stage.setY(event.getScreenY() - mousePosition[1]);
    });
  }

  protected ImageView getIcon(String path) {
    ImageView icon = new ImageView(new Image(path));
    icon.setFitWidth(ICON_SIZE);
    icon.setFitHeight(ICON_SIZE);
    return icon;
  }
}