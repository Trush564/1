package org.example.demo11;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ColorPicker implements Initializable {
    @FXML
    private javafx.scene.control.ColorPicker colorPicker;

    @FXML
    private AnchorPane colorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorPicker.valueProperty().addListener(new ChangeListener<Color>() {
            @Override
            public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
                try {
                    if (newValue != null && colorPane != null) {
                        colorPane.setBackground(new Background(new BackgroundFill(newValue, null, null)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

