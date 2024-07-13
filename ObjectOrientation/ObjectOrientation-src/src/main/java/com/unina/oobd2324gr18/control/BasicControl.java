package com.unina.oobd2324gr18.control;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.animation.*;
import java.io.IOException;
import com.unina.oobd2324gr18.boundary.BasePageController;

import java.util.Optional;

/**
 * Fornisce una classe base astratta per il controllo delle interfacce utente.
 * Questa classe include metodi comuni per la gestione delle finestre di dialogo di avviso.
 *
 * @autor DavideGargiulo
 */
public abstract class BasicControl {

  private static final double FADE_OUT_DURATION = 0.2;
  private static final int ICON_WIDTH = 30;
  private static final int ICON_HEIGHT = 30;

  private String pageName;

  protected BasicControl(final String defaultPageName) {
    this.pageName = defaultPageName;
  }

  /**
   * Set the scene based on the provided page name.
   *
   * @param newPageName the new page name
   * @throws Exception if the scene cannot be set
   */
  protected void setScene(final String newPageName) throws Exception {
    this.pageName = newPageName;
    setScene();
  }

  /**
   * Set the default scene.
   *
   * @throws Exception if the scene cannot be set
   */
  protected void setScene() throws Exception {
    try {
      System.out.println("Setting scene: " + pageName);
      Scene scene = setupScene();
      App.switchScene(getWidth(), getHeight(), scene);
    } catch (IllegalStateException e) {
      showInternalError(e);
    }
  }

  /**
   * Load the controller and initialize it.
   *
   * @param <T> the type of the controller
   * @param loader the FXMLLoader instance
   */
  private <T extends BasePageController<BasicControl>> void loadController(final FXMLLoader loader) {
    T pageController = loader.getController();
    pageController.init(this);
  }

  /**
   * Setup the scene by loading the FXML and CSS.
   *
   * @return the configured scene
   * @throws StaticResourceLoadingException if the resources cannot be loaded
   */
  private Scene setupScene() throws IllegalArgumentException {
    try {
        System.out.println("Loading scene: " + pageName);
        FXMLLoader loader = createLoader();
        Scene scene = createScene(loader);
        System.out.println("Prima di morire");
        setController(loader);
        System.out.println("Dopo morto");
        setStylesheet(scene);
        return scene;
    } catch (IOException e) {
        System.err.println("Error loading scene: " + e.getMessage());
        e.printStackTrace();
        throw new IllegalArgumentException(e);
    }
}

  private FXMLLoader createLoader() throws IOException {
    System.out.println("Creating FXMLLoader for: " + pageName);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + pageName + ".fxml"));
    if (loader.getLocation() == null) {
      throw new IOException("FXML file not found: " + "/fxml/" + pageName + ".fxml");
    }
    System.out.println("FXMLLoader created: " + loader);
    System.out.println("FXML file found: " + loader.getLocation());
    return loader;
  }

  private Scene createScene(FXMLLoader loader) throws IOException {
    System.out.println("Loading controller for: " + pageName);
    return new Scene(loader.load(), getWidth(), getHeight());
  }

  private void setController(FXMLLoader loader) {
    System.out.println("Setting controller for: " + pageName);
    loadController(loader);
  }

  private void setStylesheet(Scene scene) {
    System.out.println("Setting stylesheet for: " + pageName);
    String stylesheet = getClass().getResource("/styles/" + pageName + ".css").toExternalForm();
    if (stylesheet == null) {
      System.err.println("Stylesheet not found: " + "/styles/" + pageName + ".css");
    } else {
      System.out.println("Stylesheet found: " + stylesheet);
      scene.getStylesheets().add(stylesheet);
    }
  }

  public double getWidth() {
    return this instanceof LoginControl ? LoginControl.WIDTH : Math.max(NonLoginControl.WIDTH, App.getStage().getWidth());
  }

  public double getHeight() {
    return this instanceof LoginControl ? LoginControl.HEIGHT : Math.max(NonLoginControl.HEIGHT, App.getStage().getHeight());
  }

  /**
   * Show an alert dialog.
   *
   * @param alertType the type of the alert
   * @param title the title of the alert
   * @param header the header text of the alert
   * @param content the content of the alert
   * @param buttons the buttons to display
   * @return the selected button type
   */
  protected Optional<ButtonType> showAlert(
          final Alert.AlertType alertType,
          final String title,
          final String header,
          final String content,
          final ButtonType... buttons) {
      Alert alert = new Alert(alertType, content, buttons);
      styleAlert(alert, title, header, content);
      return alert.showAndWait();
  }

  /**
   * Style the alert dialog.
   *
   * @param alert the alert to style
   * @param title the title of the alert
   * @param header the header text of the alert
   * @param content the content of the alert
   */
  private void styleAlert(Alert alert, String title, String header, String content) {
      alert.setTitle(title);
      alert.setHeaderText(header);
      alert.setContentText(content);

      Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
      alertStage.getIcons().add(App.getStage().getIcons().get(0));

      if (alert.getAlertType() == Alert.AlertType.NONE) {
          alert.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> event.consume());
      }
  }

  /**
   * Exit the application with a confirmation dialog.
   */
  public void exit() {
      Optional<ButtonType> result = showAlert(
              Alert.AlertType.CONFIRMATION,
              "Conferma",
              "Conferma chiusura",
              "Sei sicuro di voler uscire dall'applicazione?",
              ButtonType.OK, ButtonType.CANCEL);

      if (result.isPresent() && result.get() == ButtonType.OK) {
          fadeOutTransition(0.0, e -> App.getStage().close());
      }
  }

  /**
   * Perform a fade-out transition.
   *
   * @param toValue the target opacity value
   * @param onFinish the event handler to call when finished
   */
  protected void fadeOutTransition(final double toValue, final EventHandler<ActionEvent> onFinish) {
      Duration duration = Duration.seconds(FADE_OUT_DURATION);
      KeyValue kv = new KeyValue(App.getStage().opacityProperty(), toValue);
      KeyFrame kf = new KeyFrame(duration, kv);
      Timeline timeline = new Timeline(kf);
      timeline.setOnFinished(onFinish);
      timeline.play();
  }

  /**
   * Show an internal error alert.
   *
   * @param e the exception to display
   */
  protected void showInternalError(final Exception e) {
      e.printStackTrace();
      showAlert(
              Alert.AlertType.ERROR,
              "Errore",
              "Errore inaspettato.",
              "Si è verificato un errore interno inatteso, si prega di riprovare o riavviare l'applicazione.");
  }

  /**
   * Get the current page name.
   *
   * @return the page name
   */
  public String getPageName() {
      return pageName;
  }

  /**
   * Set a new page name.
   *
   * @param newPageName the new page name
   */
  public void setPageName(final String newPageName) {
      this.pageName = newPageName;
  }
}