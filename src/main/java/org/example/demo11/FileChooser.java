package org.example.demo11;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileChooser {
    @FXML
    private ImageView myImageView;

    @FXML
    private Button chooseButton;

    @FXML
    void filechooser(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Get image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("image", "*.png")
        );

        Stage stage = (Stage) scenePane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            URL url = file.toURI();
            Image image = new Image(url.toString());
            imgView.setImage(image);
        }
    }

}
