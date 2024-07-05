package com.unina.oobd2324gr18.utils;

/**
 * Eccezione personalizzata che indica che un metodo non è stato implementato.
 * Estende {@link UnsupportedOperationException} per fornire un messaggio di errore specifico.
 * Questa eccezione può essere utilizzata per segnalare che una parte del codice non è ancora stata completata.
 *
 * @author DavideGargiulo
 */
public class MethodNotImplemented extends UnsupportedOperationException {

  /**
   * Costruttore di default che inizializza l'eccezione con un messaggio di errore predefinito.
   */
  public MethodNotImplemented() {
    super("Method not implemented."); // Imposta il messaggio di errore standard.
  }
}