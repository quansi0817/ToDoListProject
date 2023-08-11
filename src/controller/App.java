package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
            App.primaryStage.setTitle("Completed");
            App.primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("../asset/img/icon.png")));
            Scene scene = new Scene(root);
            App.primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setRoot(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource(fxmlFile + ".fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}