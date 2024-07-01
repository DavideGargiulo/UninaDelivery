package com.unina.oobd2324gr18.control;

import com.unina.oobd2324gr22.entity.DAO.AccountDAO;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

/**
 * Gestisce il processo di login per l'applicazione.
 * Estende {@link BasicControl} per utilizzare funzionalità comuni di controllo dell'interfaccia utente.
 * Implementa il pattern Singleton per assicurarsi che esista una sola istanza di questa classe.
 *
 * @author DavideGargiulo
 */
public final class LoginControl extends BasicControl {

  // Dimensioni predefinite della finestra di login.
  protected static final double WINDOW_WIDTH = 600;
  protected static final double WINDOW_HEIGHT = 400;

  // DAO per l'accesso ai dati dell'account.
  private AccountDAO accountDAO = new AccountDAOPostgres();

  // Istanza singleton di LoginControl.
  private static LoginControl instance;

  /**
   * Costruttore privato per prevenire l'istanziazione diretta.
   * Chiama il costruttore della superclasse con il nome della pagina.
   */
  private LoginControl() {
    super("LoginPage");
  }

  /**
   * Fornisce l'accesso all'istanza singleton di LoginControl.
   * Se l'istanza non esiste, ne crea una nuova.
   *
   * @return L'istanza singleton di LoginControl.
   */
  public static LoginControl getInstance() {
    if (instance == null) {
      instance = new LoginControl();
    }
    return instance;
  }

  /**
   * Tenta di autenticare l'utente con le credenziali fornite.
   * Se le credenziali sono valide e corrispondono a un operatore, procede al login.
   *
   * @param email L'email fornita dall'utente.
   * @param password La password fornita dall'utente.
   */
  public void authenticate(final String email, final String password) {
    if (!areCredentialsValid(email, password)) return;

    try {
      Operator operator = accountDAO.getOperatorByEmailAndPassword(email, SHA256.toSHA256(password));
      if (operator != null) {
        Session.loginOperator(operator);
        DashboardControl.getInstance().setScene();
      } else {
        displayLoginError("Incorrect email or password.");
      }
    } catch (SQLException e) {
      displayInternalError(e);
    }
  }

  /**
   * Verifica se le credenziali fornite sono valide.
   * Controlla che email e password non siano vuote, che l'email sia nel formato corretto e che sia un'email di operatore.
   *
   * @param email L'email fornita.
   * @param password La password fornita.
   * @return true se le credenziali sono valide, altrimenti false.
   */
  private boolean areCredentialsValid(final String email, final String password) {
    if (email.isBlank() || password.isBlank()) {
      displayLoginError("Please enter both email and password.");
      return false;
    }

    if (!validateEmail(email)) {
      displayLoginError("Invalid email format.");
      return false;
    }

    if (!checkOperatorEmail(email)) {
      displayLoginError("Only operator accounts are allowed.");
      return false;
    }

    return true;
  }

  /**
   * Mostra un messaggio di errore di login.
   *
   * @param message Il messaggio di errore da visualizzare.
   */
  private void displayLoginError(final String message) {
    showAlert(Alert.AlertType.ERROR, "Login Error", "Authentication Failed", message);
  }

  /**
   * Valida l'email secondo un'espressione regolare.
   *
   * @param email L'email da validare.
   * @return true se l'email è valida, altrimenti false.
   */
  private boolean validateEmail(final String email) {
    return Pattern.matches("^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$", email);
  }

  /**
   * Controlla se l'email appartiene a un operatore.
   *
   * @param email L'email da controllare.
   * @return true se l'email appartiene a un operatore, altrimenti false.
   */
  private boolean checkOperatorEmail(final String email) {
    return Pattern.matches("^[a-zA-Z]\\.([a-zA-Z]+\\_[A-Za-z]+|[A-Za-z]+)[0-9]*@uninadelivery\\.operator\\.com$", email);
  }
}