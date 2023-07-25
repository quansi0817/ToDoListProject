package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class ToDoItemController {
    @FXML
    private Button addItemButton;

    @FXML
    private Button deleteItemButton;

    @FXML
    private Button editItemButton;

    @FXML
    private ListView<String> itemView;

    private List<String> items = new ArrayList<>();

    @FXML
    public void initialize() {

        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");

        itemView.getItems().addAll(items);

        // add button action
        addItemButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add New Item");
            dialog.setHeaderText(null);
            dialog.setContentText("Please enter your new item:");

            dialog.initModality(Modality.APPLICATION_MODAL);
            String newItem = dialog.showAndWait().orElse("");
            if (!newItem.isEmpty()) {
                itemView.getItems().add(newItem);
            }
        });
        // delete button action
        deleteItemButton.setOnAction(event -> {
            String selectedItem = itemView.getSelectionModel().getSelectedItem();
            itemView.getItems().remove(selectedItem);
        });
        // edit button action
        editItemButton.setOnAction(event -> {
            String selectedItem = itemView.getSelectionModel().getSelectedItem();

            TextInputDialog dialog = new TextInputDialog(selectedItem);
            dialog.setTitle("Edit Item");
            dialog.setHeaderText(null);
            dialog.setContentText("Please edit your item:");

            String newItem = dialog.showAndWait().orElse("");
            if (!newItem.isEmpty()) {
                int selectedIndex = itemView.getSelectionModel().getSelectedIndex();
                itemView.getItems().set(selectedIndex, newItem);
            }

        });

    }
}
