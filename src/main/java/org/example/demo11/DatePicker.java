package org.example.demo11;

import javafx.fxml.FXML;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePicker {
    @FXML
    private Label dateLabel;

    @FXML
    private DatePicker myDatePicker;

    @FXML
    void getDate(ActionEvent event) {
        LocalDate myDate = myDatePicker.getValue();
        System.out.println(myDate.toString());
        dateLabel.setText(myDate.toString());
    }
    @FXML
    void getDate(ActionEvent event) {
        LocalDate myDate = myDatePicker.getValue();
        System.out.println(myDate.toString());
        String dateFormatter =
                myDate.format(DateTimeFormatter.ofPattern("dd.MM.yyy"));
        dateLabel.setText(dateFormatter);
    }
}
