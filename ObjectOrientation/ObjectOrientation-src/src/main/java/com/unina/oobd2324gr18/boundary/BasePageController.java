package com.unina.oobd2324gr18.boundary;

import com.unina.oobd2324gr18.control.App;
import com.unina.oobd2324gr18.control.BasicControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.stage.Stage;

/**
 * Fornisce una classe base astratta per i controller delle pagine dell'interfaccia utente.
 * Questa classe implementa funzionalità comuni come la gestione del trascinamento della finestra.
 *
 * @param <T> Il tipo di controllo associato a questa pagina.
 * @author DavideGargiulo
 */
public abstract class BasePageController<T extends BasicControl> {

  // Controllo associato a questa pagina
  private T control;

  // Dimensione dell'icona per i pulsanti della barra del titolo
  private static final int ICON_SIZE = 30;

  // Nodo della barra del titolo per il trascinamento della finestra
  @FXML
  private Node titleBar;

  // Pulsante di uscita
  @FXML
  private Button exitButton;

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
  protected abstract void initialize(T control);

  /**
   * Fornisce l'accesso al controllo associato.
   *
   * @return Il controllo associato.
   */
  protected T getControl() {
    return control;
  }

  /**
   * Rende la finestra trascinabile utilizzando la barra del titolo.
   */
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

  /**
   * Crea e restituisce un'icona a partire dal percorso specificato.
   *
   * @param path Il percorso dell'immagine dell'icona.
   * @return Un oggetto ImageView che rappresenta l'icona.
   */
  protected ImageView getIcon(String path) {
    ImageView icon = new ImageView(new Image(path));
    icon.setFitWidth(ICON_SIZE);
    icon.setFitHeight(ICON_SIZE);
    return icon;
  }
}