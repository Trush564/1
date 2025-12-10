package org.example.demo11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Lab7DateController {
    @FXML
    private DatePicker datePicker;

    @FXML
    private Label dateLabel;

    @FXML
    private void showSelectedDate(ActionEvent event) {
        LocalDate selected = datePicker.getValue();
        if (selected == null) {
            dateLabel.setText("Оберіть дату");
            return;
        }
        String formatted = selected.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        dateLabel.setText(formatted);
    }
}

