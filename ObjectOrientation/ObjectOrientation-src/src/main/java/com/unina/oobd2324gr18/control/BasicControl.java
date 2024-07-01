package com.unina.oobd2324gr18.control;

import java.util.Optional;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Fornisce una classe base astratta per il controllo delle interfacce utente.
 * Questa classe include metodi comuni per la gestione delle finestre di dialogo di avviso.
 *
 * @author DavideGargiulo
 */
public abstract class BasicControl {
  /**
   * Mostra un avviso con le opzioni specificate.
   *
   * @param alertType Il tipo di avviso da mostrare.
   * @param title Il titolo dell'avviso.
   * @param headerText Il testo dell'intestazione dell'avviso.
   * @param contentText Il testo del contenuto dell'avviso.
   * @param buttons I bottoni da includere nell'avviso.
   * @return Un Optional che contiene il tipo di bottone premuto dall'utente, se presente.
   */
  protected Optional<ButtonType> showAvviso(final Alert.AlertType alertType, final String title, final String headerText, final String contentText, final ButtonType... buttons) {
    Alert alert = new Alert(alertType, contentText, buttons);
    configureAvviso(alert, title, headerText);

    // Impedisce la chiusura dell'avviso se il tipo è NONE
    if (alertType == Alert.AlertType.NONE) {
      alert.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> event.consume());
    }

    return alert.showAndWait();
  }

  /**
   * Configura l'avviso con stili e icone specifici.
   *
   * @param alert L'avviso da configurare.
   * @param title Il titolo da impostare per l'avviso.
   * @param headerText Il testo dell'intestazione da impostare per l'avviso.
   */
  private void configureAvviso(Alert alert, String title, String headerText) {
    // Imposta lo stile dell'avviso
    alert.getDialogPane().getStylesheets().add(getClass().getResource("../../../../resources/styles/components/alerts.css").toExternalForm());
    alert.setTitle(title);
    alert.setHeaderText(headerText);

    // Imposta l'icona dell'avviso
    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
    alertStage.getIcons().add(App.getStage().getIcons().get(0));
  }

  // Campo che memorizza il nome della pagina associata a questo controllo.
  private final String pageName;

  /**
   * Costruttore protetto per BasicControl.
   * Questo costruttore inizializza l'istanza con il nome della pagina fornito.
   *
   * @param pageName Il nome della pagina da associare a questo controllo.
   */
  protected BasicControl(final String pageName) {
    this.pageName = pageName;
  }
}