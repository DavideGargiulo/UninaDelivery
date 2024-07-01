package com.unina.oobd2324gr18.control;



public final class AuthControl extends BasicControl {

  /** Dimensions of the login window. */
  protected static final double WINDOW_WIDTH = 600;
  protected static final double WINDOW_HEIGHT = 400;

  /** Data access object for account operations. */
  private AccountDAO accountDAO = new AccountDAOPostgres();

  /** Singleton instance for AuthControl. */
  private static AuthControl instance;

  private AuthControl() {
    super("LoginPage");
  }

  /**
   * Provides access to the singleton instance.
   *
   * @return Singleton instance of AuthControl
   */
  public static AuthControl getInstance() {
    if (instance == null) {
      instance = new AuthControl();
    }
    return instance;
  }

  private void displayLoginError(final String message) {
    showAlert(Alert.AlertType.ERROR, "Login Error", "Authentication Failed", message);
  }

  private boolean validateEmail(final String email) {
    return Pattern.matches(
        "^([a-zA-Z0-9]+\\.[a-zA-Z0-9]+|[a-zA-Z0-9]+)@[a-zA-Z]+\\.[a-zA-Z]{2,}$", email);
  }

  private boolean checkOperatorEmail(final String email) {
    return Pattern.matches(
        "^[a-zA-Z]\\.([a-zA-Z]+\\_[A-Za-z]+|[A-Za-z]+)[0-9]*@uninadelivery\\.operator\\.com$", email);
  }

  public void authenticate(final String email, final String password) {
    if (email.isBlank() || password.isBlank()) {
      displayLoginError("Please enter both email and password.");
      return;
    }

    if (!validateEmail(email)) {
      displayLoginError("Invalid email format.");
      return;
    }

    if (!checkOperatorEmail(email)) {
      displayLoginError("Only operator accounts are allowed.");
      return;
    }

    Operator operator = verifyCredentials(email, password);
    if (operator != null) {
      Session.loginOperator(operator);
      try {
        DashboardControl.getInstance().setScene();
      } catch (Exception e) {
        e.printStackTrace();
        displayInternalError(e);
      }
    } else {
      displayLoginError("Incorrect email or password.");
    }
  }

  private Operator verifyCredentials(final String email, final String password) {
    try {
      return accountDAO.getOperatorByEmailAndPassword(email, SHA256.toSHA256(password));
    } catch (SQLException e) {
      displayInternalError(e);
      return null;
    }
  }
}