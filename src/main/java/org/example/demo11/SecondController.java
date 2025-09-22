package org.example.demo11;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SecondController {
    @FXML
    private Label secondLabel;

    @FXML
    protected void showMessage() {
        secondLabel.setText("Це друга сторінка!");
    }
}
