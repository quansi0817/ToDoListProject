package controller;

import dataClass.TaskList;

import java.util.*;

import java.io.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import tool.WriteData;
import javafx.scene.control.TextInputDialog;

public class ToDoListController {
    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button addCategoryButton;

    @FXML
    private ListView<String> listView;

    private ObservableList<String> items = FXCollections.observableArrayList();
    private String[] listArr;
    private Scanner input;
    private String filePath = "src/data/list.csv";

    @FXML
    public void initialize() throws IOException {

        loadList();

        // edit button action
        editButton.setOnAction(event -> {
            System.out.println(event);
            String selectedCategory = listView.getSelectionModel().getSelectedItem();
            System.out.println(selectedCategory);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Edit Category");
            alert.setHeaderText(null);
            alert.setContentText("You selected " + selectedCategory);
            alert.showAndWait();

        });

        deleteButton.setOnAction(event -> {
            String selectedCategory = listView.getSelectionModel().getSelectedItem();
            if (selectedCategory != null) {
                listView.getItems().remove(selectedCategory);
            }
        });

        addCategoryButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add New Category");
            dialog.setHeaderText(null);
            dialog.setContentText("Please enter your new category:");
            String newCategory = dialog.showAndWait().orElse("");

            if (!newCategory.isEmpty()) {
                App.getUser().getList().add(new TaskList(newCategory));
                try {
                    items.add(newCategory);
                    WriteData.writeListDataToFile();
                    listView.refresh();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedCategory = listView.getSelectionModel().getSelectedItem();
                App.currentCategory = selectedCategory;
                App.setRoot("../fxml/TodoItem");
            }
        });

    }

    public void addCategoryToDatabase(String title) {

    }

    public void loadList() throws IOException {
        // retrieve list data from data file.
        File file = new File(filePath);
        input = new Scanner(file);

        while (input.hasNextLine()) {
            String[] listStrArr = input.nextLine().split(",");
            if (listStrArr[0].equals(App.getUser().getName())) {
                listArr = listStrArr;
                break;
            }
        }

        for (int i = 1; i < listArr.length; i++) {
            items.add(listArr[i]);
            App.getUser().getList().add(new TaskList(listArr[i]));
        }
        listView.setItems(items);
    }

}