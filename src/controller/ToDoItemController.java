package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.scene.control.Button;
import dataClass.TaskList;
import dataClass.Item;

public class ToDoItemController {
    @FXML
    private Button addItemButton;

    @FXML
    private Button deleteItemButton;

    @FXML
    private Button editItemButton;

    @FXML
    private ListView<Item> itemView;

    private TaskList list;

    @FXML
    public void initialize() {

        list = new TaskList("1", "My List");

        list.addItem(new Item("1", "Item 1", "title 1"));
        list.addItem(new Item("2", "Item 2", "title 2"));
        list.addItem(new Item("3", "Item 3", "title 3"));

        itemView.getItems().addAll(list.getItems());

        // add button action
        addItemButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add New Item");
            dialog.setHeaderText(null);
            dialog.setContentText("Please enter your new item:");

            dialog.initModality(Modality.APPLICATION_MODAL);
            String newItemDescription = dialog.showAndWait().orElse("");
            if (!newItemDescription.isEmpty()) {
                Item newItem = new Item("id", newItemDescription, "title");
                list.addItem(newItem);
                itemView.getItems().add(newItem);
            }
        });
        // delete button action
        deleteItemButton.setOnAction(event -> {
            Item selectedItem = itemView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                list.removeItem(selectedItem);
                itemView.getItems().remove(selectedItem);
            }
        });
        // edit button action
        editItemButton.setOnAction(event -> {
            Item selectedItem = itemView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                TextInputDialog dialog = new TextInputDialog(selectedItem.getTitle());
                dialog.setTitle("Edit Item");
                dialog.setHeaderText(null);
                dialog.setContentText("Please edit your item:");

                String newTitle = dialog.showAndWait().orElse("");
                if (!newTitle.isEmpty()) {
                    selectedItem.setTitle(newTitle);
                    itemView.refresh();
                }
            }
        });
    }
}
