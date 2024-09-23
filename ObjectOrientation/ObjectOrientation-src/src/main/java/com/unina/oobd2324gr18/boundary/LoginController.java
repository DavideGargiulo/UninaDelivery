package com.unina.oobd2324gr18.boundary;

import com.unina.oobd2324gr18.control.LoginControl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController extends BasePageController<LoginControl> {

    private double x = 0, y = 0;

    @FXML
    private AnchorPane sideBar;

    private Stage stage;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        sideBar.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        sideBar.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }

    @Override
    protected void initialize(final LoginControl control) {
        // Non c'è bisogno di inizializzare nulla
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void closeProgram(ActionEvent event) {
        stage.close();
    }
}
