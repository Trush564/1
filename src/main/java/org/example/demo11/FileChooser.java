package org.example.demo11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

public class FileChooser {
    @FXML
    private ImageView imagePreview;

    @FXML
    void openImageFile(ActionEvent event) {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Get image");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("image", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        Stage stage = (Stage) imagePreview.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            imagePreview.setImage(new Image(file.toURI().toString()));
        }
    }
}

