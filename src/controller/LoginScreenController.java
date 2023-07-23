package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.JSONObject;
import org.json.JSONTokener;

import dataClass.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class LoginScreenController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Add your login validation logic here
        boolean isValid = validateLogin(username, password);

        if (isValid) {
            // On successful login, switch to the Home Screen
            App.loginName = username;
            // createUser(username);
            // User user = new User(username);
            App.setRoot("../fxml/HomeScreen");

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fail To Login");
            alert.setHeaderText(null);
            alert.setContentText("Wrong user name or password, please try again.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Delete file logic
                    App.setRoot("../fxml/LoginScreen");
                } else {
                    App.setRoot("../fxml/LoginScreen");
                    // Cancel deletion
                }
            });
        }
    }

    private boolean validateLogin(String username, String password) {
        try {
            String filePath = "src/data/user.json";
            String usersData = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject userJsonObject = new JSONObject(new JSONTokener(usersData));
            Iterator<String> keys = userJsonObject.keys();

            while (keys.hasNext()) {
                if (username.equals(keys.next())) {
                    JSONObject userDetail = new JSONObject(userJsonObject.get(username).toString());
                    System.out.println(userDetail.get("password"));
                    if (userDetail.get("password").toString().equals(password)) {
                        createUser(username, userDetail.get("email").toString(), password);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return false;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createUser(String username, String email, String password) {
        User user = new User(email, username, password);
        App.setUser(user);
    }

    @FXML
    private void handleRegisterButtonAction() {
        App.setRoot("../fxml/RegisterScreen");
    }

}
