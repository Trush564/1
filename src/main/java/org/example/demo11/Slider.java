package org.example.demo11;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Slider {
    @FXML
    private javafx.scene.control.Slider mySlider;

    @FXML
    private Label sliderLabel;

    private int score;

    public void initialize() {
        sliderLabel.setText((int) mySlider.getValue() + " балів");
        mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            score = newValue.intValue();
            sliderLabel.setText(score + " балів");
        });
    }
}

