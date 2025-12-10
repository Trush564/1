package org.example.demo11;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import java.awt.*;

public class Slider {
    @FXML
    private Slider mySlider;

    @FXML
    private Label sliderLabel;

    private int score;

    public void initialize() {
        mySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {

                score = (int) mySlider.getValue();
                sliderLabel.setText(score + " балів");
            }
        });
    }

}
