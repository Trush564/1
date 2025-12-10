package org.example.demo11;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class Lab7SliderController {
    @FXML
    private Slider scoreSlider;

    @FXML
    private Label sliderLabel;

    public void initialize() {
        sliderLabel.setText((int) scoreSlider.getValue() + " балів");
        scoreSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                sliderLabel.setText(newValue.intValue() + " балів"));
    }
}

