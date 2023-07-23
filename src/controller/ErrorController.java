package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ErrorController {
    @FXML
    private void handleGoBack() {
        App.setRoot("../fxml/LoginScreen");
    }
}
