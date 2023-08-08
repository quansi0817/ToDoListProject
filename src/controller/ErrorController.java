package controller;

import javafx.fxml.FXML;

public class ErrorController {
    @FXML
    private void handleGoBack() {
        App.setRoot("../fxml/LoginScreen");
    }
}
