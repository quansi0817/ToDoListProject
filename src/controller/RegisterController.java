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

public class RegisterController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private void handleBackAction() {
        App.setRoot("../fxml/LoginScreen");
    }

    @FXML
    private void handleRegisterAction() {
        // Get user input message:
        String name = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        if (!name.isBlank() && !password.isBlank() && !email.isBlank()) {

            try {
                String filePath = "src/data/user.json";

                // Read the existing JSON data from the file into a String
                String usersData = new String(Files.readAllBytes(Paths.get(filePath)));

                // Parse the JSON data into a JSONObject
                JSONObject userJsonObject = new JSONObject(new JSONTokener(usersData));

                // get the last ID and the new ID
                // String lastId = "";
                // Iterator<String> keys = userJsonObject.keys();
                // while (keys.hasNext()) {
                // String key = keys.next();
                // System.out.println(key);
                // lastId = key;
                // }
                // int lastIntId = Integer.parseInt(lastId);

                // Get the input from user:

                // Store the input data into one
                JSONObject user = new JSONObject();
                // user.put("id", newId);
                user.put("password", password);
                user.put("email", email);
                System.out.println(userJsonObject);

                // Add new data to this ID JSONObject
                userJsonObject.put(name, user);

                // Write the updated JSONObject back to the file
                try (FileWriter fileWriter = new FileWriter(filePath)) {
                    fileWriter.write(userJsonObject.toString());
                    confirmRegister("success");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            confirmRegister("fail");
        }
    }

    private void confirmRegister(String state) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(state);
        alert.setHeaderText(null);
        if (state.equals("success")) {

            alert.setContentText("Account created successfully");
            // alert.showAndWait();
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    App.setRoot("../fxml/LoginScreen");
                } else {
                    App.setRoot("../fxml/LoginScreen");
                    // System.out.println("Deletion canceled.");
                }
            });
        } else {
            alert.setContentText("Account created failed, try again later");
            // alert.showAndWait();
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    App.setRoot("../fxml/RegisterScreen");
                } else {
                    App.setRoot("../fxml/RegisterScreen");
                    // System.out.println("Deletion canceled.");
                }
            });

        }
    }
}
