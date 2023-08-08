package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import dataClass.User;

public class App extends Application {

    private static Stage primaryStage;
    public static String loginName; // Store the login name here
    private static User user;
    public static String currentCategory;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        App.user = user;
    }

    @Override
    public void start(Stage primaryStage) {
        App.primaryStage = primaryStage;
        showLoginScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showLoginScreen() {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("../fxml/LoginScreen.fxml"));
            primaryStage.setTitle("Login Screen");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setRoot(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource(fxmlFile + ".fxml"));
            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.setTitle(fxmlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}