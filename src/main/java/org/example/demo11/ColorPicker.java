package org.example.demo11;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ColorPicker {
    @FXML
    private ColorPicker myColorPicker;

    @FXML
    private AnchorPane scenePane;

    @FXML
    void changeColor(ActionEvent event) {
        Color myColor = myColorPicker.getValue();
        scenePane.setBackground(
                new Background(new BackgroundFill(myColor, null, null))
        );
    }

}
