package org.example.demo11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class Lab7ColorController {
    @FXML
    private ColorPicker colorPicker;

    @FXML
    private AnchorPane colorPane;

    @FXML
    private void changeColor(ActionEvent event) {
        Color chosen = colorPicker.getValue();
        if (chosen != null) {
            colorPane.setBackground(new Background(new BackgroundFill(chosen, null, null)));
        }
    }
}

