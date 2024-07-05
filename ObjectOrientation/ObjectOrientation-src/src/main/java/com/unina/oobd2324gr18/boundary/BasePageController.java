package com.unina.oobd2324gr18.boundary;

import com.unina.oobd2324gr18.control.App;
import com.unina.oobd2324gr18.control.BasicControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.input.MouseEvent;

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
}