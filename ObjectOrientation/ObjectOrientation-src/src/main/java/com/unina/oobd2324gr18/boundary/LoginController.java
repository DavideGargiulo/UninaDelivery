package com.unina.oobd2324gr18.boundary;

import com.unina.oobd2324gr18.control.LoginControl;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class LoginController extends BasePageController<LoginControl> {

  @FXML
  private TextField emailTextField;

  @FXML
  private PasswordField passwordTextField;

  @FXML
  private Button loginButton;

  @FXML
  private TextField plainPasswordTextField;
}