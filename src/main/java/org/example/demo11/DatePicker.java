package org.example.demo11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePicker {
    @FXML
    private javafx.scene.control.DatePicker datePicker;

    @FXML
    private Label dateLabel;

    @FXML
    void getDate(ActionEvent event) {
        LocalDate myDate = datePicker.getValue();
        if (myDate == null) {
            dateLabel.setText("Оберіть дату");
            return;
        }
        String formatted = myDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        dateLabel.setText(formatted);
    }
}

