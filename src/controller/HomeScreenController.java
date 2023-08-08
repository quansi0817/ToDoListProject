package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HomeScreenController implements Initializable {

    @FXML
    private Label welcomeLabel;

    @FXML
    private void handleLogoutButtonAction() {
        // Switch back to the Login Screen when the user clicks the "Logout" button
        // System.out.println(Main.loginName);
        App.setRoot("../fxml/LoginScreen");
    }

    @FXML
    private void printUserInfo() {
        System.out.println(App.getUser().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Perform any actions you want when the scene is initialized.
        welcomeLabel.setText("Welcome, " + App.loginName + "!");
    }

    @FXML
    private void createNewListAction() {

    }

}