package com.unina.oobd2324gr18.boundary;

import com.unina.oobd2324gr18.control.App;
import com.unina.oobd2324gr18.control.BasicControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

public abstract class BasePageController<T extends BasicControl> {

  // Controllo associato a questa pagina
  private T control;

  // Dimensione dell'icona per i pulsanti della barra del titolo
  private static final int ICON_SIZE = 30;

  // Variabili per tenere traccia della posizione iniziale del mouse
  private double xOffset = 0;
  private double yOffset = 0;

  /**
   * Inizializza il controller con il controllo specificato.
   *
   * @param control Il controllo da associare a questa pagina.
   */
  public void init(final T control) {
    this.control = control;
    initialize(control);
    makeWindowDraggable();
  }

  /**
   * Metodo astratto per l'inizializzazione specifica del controller.
   *
   * @param control Il controllo da utilizzare per l'inizializzazione.
   */
  protected abstract void initialize(T c);

  /**
   * Fornisce l'accesso al controllo associato.
   *
   * @return Il controllo associato.
   */
  protected T getControl() {
    return control;
  }

  private void setControl(final T c) {
    control = c;
  }

  /**
   * Makes the window draggable by mouse.
   */
  private void makeWindowDraggable() {
    Stage stage = App.getStage();
    stage
      .sceneProperty()
      .addListener((obs, oldScene, newScene) -> {
        if (newScene != null) {
          newScene.setOnMousePressed(event -> handleMousePressed(event));
          newScene.setOnMouseDragged(event -> handleMouseDragged(event));
        }
      });

    // If the scene is already set
    Scene currentScene = stage.getScene();
    if (currentScene != null) {
      currentScene.setOnMousePressed(event -> handleMousePressed(event));
      currentScene.setOnMouseDragged(event -> handleMouseDragged(event));
    }
  }

  private void handleMousePressed(MouseEvent event) {
    xOffset = event.getSceneX();
    yOffset = event.getSceneY();
  }

  private void handleMouseDragged(MouseEvent event) {
    Stage primaryStage = App.getStage();
    primaryStage.setX(event.getScreenX() - xOffset);
    primaryStage.setY(event.getScreenY() - yOffset);
  }
}
