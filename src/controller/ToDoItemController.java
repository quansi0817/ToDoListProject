package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import tool.WriteTaskData;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import dataClass.TaskList;
import javafx.scene.control.Alert;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dataClass.Item;

public class ToDoItemController {
    private String currentUserName;
    private String currentListTitle;
    
    @FXML
    private Button addItemButton;

    @FXML
    private Button deleteItemButton;

    @FXML
    private Button editItemButton;

    @FXML
    private Button completeItemButton;

    @FXML
    private Button toHomepageButton;

    @FXML
    private ListView<Item> itemView;

    private TaskList list;
    private String filePath = "src/data/task.csv";

    public ToDoItemController() {
        this.currentUserName = "DefaultUserName";
        this.currentListTitle = "DefaultListTitle";
    }

    public void setCurrentUserAndList(String userName, String listTitle) {
        this.currentUserName = userName; // in which username
        this.currentListTitle = listTitle; // in which list
    }

    @FXML
    public void initialize() throws IOException {
        loadTasks();
        addItemButton.setOnAction(event -> addTask());
        deleteItemButton.setOnAction(event -> deleteTask());
        completeItemButton.setOnAction(event -> deleteTask());
        editItemButton.setOnAction(event -> editTask());
        toHomepageButton.setOnAction(event ->  App.setRoot("../fxml/TodoList"));
    }

    private void loadTasks() throws IOException { // load tasks from file
        String username = App.getUser().getName(); // get username from App
        String listname = ToDoListController.selectedList; 
        this.list = new TaskList(listname);  
        File file = new File(filePath);

        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split(",");
                if (parts.length < 5) {
                    continue;
                }
                String fileUsername = parts[0].trim();
                String fileListname = parts[1].trim();
                if (fileUsername.equals(username) && fileListname.equals(listname)) {
                    String itemId = parts[2].trim();
                    String itemTitle = parts[3].trim();
                    String itemDescription = parts[4].trim();
                    Item item = new Item(itemId, itemTitle, itemDescription);
                    list.addItem(item);
                }
            }
        }
        itemView.getItems().addAll(list.getItems());
    }

    private void addTask() {
        TextInputDialog titleDialog = new TextInputDialog();
        titleDialog.setTitle("Edit Item Title");
        titleDialog.setHeaderText(null);
        titleDialog.setContentText("Please edit your item's title:");
        String newTitle = titleDialog.showAndWait().orElse("");

        TextInputDialog descriptionDialog = new TextInputDialog();
        descriptionDialog.setTitle("Edit Item Description");
        descriptionDialog.setHeaderText(null);
        descriptionDialog.setContentText("Please edit your item's description:");
        String newItemDescription = descriptionDialog.showAndWait().orElse("");

        if (!newTitle.isEmpty() && !newItemDescription.isEmpty()) {
            String uniqueID = String.valueOf(System.currentTimeMillis());
            Item newItem = new Item(uniqueID, newTitle, newItemDescription);  // Corrected order of parameters
            list.addItem(newItem);
            itemView.getItems().add(newItem);
            itemView.refresh();  // Refresh the view after adding an item

            try {
                WriteTaskData.addTask(App.getUser().getName(), list.getTitle(), newItem);
            } catch (IOException e) {
                e.printStackTrace();
                showError("Error adding task: " + e.getMessage());
            }
        }
    }

    private void deleteTask() {
        Item selectedItem = itemView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            list.removeItem(selectedItem);
            itemView.getItems().remove(selectedItem);
            
            try {
                WriteTaskData.deleteTask(App.getUser().getName(), list.getTitle(),selectedItem.getId());
            } catch (IOException e) {
                showError("Error deleting task.");
            }
        }
    }

    private void editTask() {
    Item selectedItem = itemView.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
        TextInputDialog titleDialog = new TextInputDialog(selectedItem.getTitle());
        titleDialog.setTitle("Edit Item Title");
        titleDialog.setHeaderText(null);
        titleDialog.setContentText("Please edit your item's title:");
        
        String newTitle = titleDialog.showAndWait().orElse("");

        TextInputDialog descriptionDialog = new TextInputDialog(selectedItem.getDescription());
        descriptionDialog.setTitle("Edit Item Description");
        descriptionDialog.setHeaderText(null);
        descriptionDialog.setContentText("Please edit your item's description:");
        
        String newDescription = descriptionDialog.showAndWait().orElse("");
        
        if (!newTitle.isEmpty() && !newDescription.isEmpty()) {
            Item editedItem = new Item(selectedItem.getId(), newTitle, newDescription);
            
            try {
                WriteTaskData.editTask(App.getUser().getName(), list.getTitle(), selectedItem, editedItem);
            } catch (IOException e) {
                showError("Error editing task.");
            }
            
            selectedItem.setTitle(newTitle);
            selectedItem.setDescription(newDescription);
            itemView.refresh();
        }
    }
}


    private void showError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

}
