package org.example.demo11;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void handleOpenDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("second-view.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Модальне вікно");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            // отримуємо доступ до головного вікна
            Stage parentStage = (Stage) welcomeText.getScene().getWindow();
            dialogStage.initOwner(parentStage);

            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEdit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Редагування");
        alert.setHeaderText("Функція редагування");
        alert.setContentText("");
        alert.showAndWait();
    }

    @FXML
    private void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Видалення");
        alert.setHeaderText("Підтвердження видалення");
        alert.setContentText("Ви дійсно хочете видалити контакт?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.out.println("Контакт видалено!");
            }
        });
    }
}