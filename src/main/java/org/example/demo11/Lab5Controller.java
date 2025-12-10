package org.example.demo11;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

public class Lab5Controller {

    @FXML
    private Button choose;

    @FXML
    private Label text;

    @FXML
    private ImageView imageView;

    private final Image image = new Image(getClass().getResourceAsStream("image2.jpg"));

    @FXML
    private void setChangeButton(ActionEvent event) {
        imageView.setImage(image);
        text.setText("Зображення успішно змінено!");
        choose.setDisable(true);
    }
}





