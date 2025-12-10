package org.example.demo11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Lab7FileController {

    @FXML
    private ImageView imagePreview;

    @FXML
    private void openImageFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Обрати зображення");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Зображення", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        Stage stage = (Stage) imagePreview.getScene().getWindow();
        File selected = chooser.showOpenDialog(stage);
        if (selected != null) {
            imagePreview.setImage(new Image(selected.toURI().toString()));
        }
    }
}

