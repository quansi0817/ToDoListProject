package tool;

import controller.App;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoadDialog {
    private static Stage dialogStage = new Stage();

    public static String newDialog(String msg) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Completed");
        dialog.setHeaderText(null);
        dialog.setContentText(msg);
        String newCategory = dialog.showAndWait().orElse("");
        return newCategory;
    }

    public static String newDialog(String msg, String oldContent) {
        TextInputDialog dialog = new TextInputDialog(oldContent);
        dialog.setTitle("Completed");
        dialog.setHeaderText(null);
        dialog.setContentText(msg);
        String newCategory = dialog.showAndWait().orElse("");
        return newCategory;
    }

}
