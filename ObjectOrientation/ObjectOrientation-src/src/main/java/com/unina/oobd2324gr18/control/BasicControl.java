package com.unina.oobd2324gr18.control;

import java.util.Optional;
import javafx.scene.control.*;
import javafx.stage.Stage;

public abstract class BasicControl {
  protected Optional<ButtonType> showAlert(final Alert.AlertType alertType, final String title, final String headerText, final String contentText, final ButtonType... buttons) {
    Alert alert = new Alert(alertType, contentText, buttons);
    configureAlert(alert, title, headerText);

    if (alertType == Alert.AlertType.NONE) {
      alert.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> event.consume());
    }

    return alert.showAndWait();
  }

  private void configureAlert(Alert alert, String title, String headerText) {
    alert.getDialogPane().getStylesheets().add(getClass().getResource("../../../../resources/styles/components/alerts.css").toExternalForm());
    alert.setTitle(title);
    alert.setHeaderText(headerText);

    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
    alertStage.getIcons().add(App.getStage().getIcons().get(0));
  }
}