package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
   
    @FXML
    public void initialize() {
    // add items to the listview
    listView.getItems().addAll("Personal", "Work", "School", "Other");
    
    // edit button action
    editButton.setOnAction(event -> {
        String selectedCategory = listView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Edit Category");
        alert.setHeaderText(null);
        alert.setContentText("You selected " + selectedCategory);
        alert.showAndWait();
    
    }); 

    deleteButton.setOnAction(event -> {
        String selectedCategory = listView.getSelectionModel().getSelectedItem();
        if(selectedCategory != null) {
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
            listView.getItems().add(newCategory);
        }
    });

    listView.setOnMouseClicked(event -> {
       if(event.getClickCount() == 2) {
           String selectedCategory = listView.getSelectionModel().getSelectedItem();
           App.currentCategory = selectedCategory;
           App.setRoot("../fxml/TodoItem");
       }
    });







}
}