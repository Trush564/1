package org.example.demo11;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class OtherLabs {

    @FXML
    private Button lab5Button;

    @FXML
    private Button lab6Button;

    @FXML
    private Button lab7Button;

    @FXML
    private Button lab8Button;

    @FXML
    private void openLab5(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lab5.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            
            Stage stage = new Stage();
            stage.setTitle("Лабораторна робота 5");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openLab6(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lab6.fxml"));
            Scene scene = new Scene(loader.load(), 1400, 800);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            
            Stage stage = new Stage();
            stage.setTitle("Лабораторна робота 6");
            stage.setResizable(true);
            stage.setMinWidth(1200);
            stage.setMinHeight(700);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openLab7(ActionEvent event) {
        showLabInfo("Лабораторна робота 7", "Тут буде реалізація лабораторної роботи 7");
    }

    @FXML
    private void openLab8(ActionEvent event) {
        showLabInfo("Лабораторна робота 8", "Тут буде реалізація лабораторної роботи 8");
    }

    private void showLabInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
