package com.unina.oobd2324gr18.control;

import com.unina.oobd2324gr18.DAO.AccountDAO;
import com.unina.oobd2324gr18.DAO.AccountDAOPostgresql;
import com.unina.oobd2324gr18.DTO.OperatorDTO;
import com.unina.oobd2324gr18.utils.SHA512;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * Gestisce il processo di login per l'applicazione.
 * Estende {@link BasicControl} per utilizzare funzionalità comuni di controllo dell'interfaccia utente.
 * Implementa il pattern Singleton per assicurarsi che esista una sola istanza di questa classe.
 *
 *
 * @author DavideGargiulo
 */
public final class LoginControl extends BasicControl {
  private static LoginControl instance;
  private AccountDAO accountDAO = new AccountDAOPostgresql();

  protected static final int WIDTH = 800;
  protected static final int HEIGHT = 600;

  private LoginControl() {
    super("LoginPage");
  }

  public static LoginControl getInstance() {
    if (instance == null) {
      instance = new LoginControl();
    }
    return instance;
  }

  /**
   * Attempts to log in a user with the given username and password.
   *
   * @param username the username of the user
   * @param password the password of the user
   * @return an OperatorDTO if the login is successful, null otherwise
   */
  public OperatorDTO login(String username, String password) {
    if (!isValidInput(username, password)) {
      showAlert("Input Validation Error", "Username or password is invalid.");
      return null;
    }

    String hashedPassword = SHA512.getSecurePassword(password, null);

    try {
      OperatorDTO operator = accountDAO.findOperatorByBusinessemailNPassword(username, hashedPassword);
      if (operator == null) {
        showAlert("Login Failed", "Invalid username or password.");
        return null;
      }
      return operator;
    } catch (SQLException e) {
      showAlert("Database Error", "An error occurred while accessing the database.");
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Validates the input for username and password.
   *
   * @param username the username to validate
   * @param password the password to validate
   * @return true if both username and password are valid, false otherwise
   */
  private boolean isValidInput(String username, String password) {
    if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
      return false;
    }

    // Minimum length validation
    if (username.length() < 5 || password.length() < 8) {
      return false;
    }

    // Regex pattern for allowed characters in username (e.g., alphanumeric characters only)
    if (!username.matches("^[a-zA-Z0-9]+$")) {
      return false;
    }

    // Additional password strength criteria, e.g., at least one number, one letter, and one special character
    if (!password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).{8,}$")) {
      return false;
    }

    return true;
  }

  /**
   * Displays an alert with the given title and message.
   *
   * @param title   the title of the alert
   * @param message the message of the alert
   */
  private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
  }
}