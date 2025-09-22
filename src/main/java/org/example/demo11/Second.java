package org.example.demo11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Second extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Second.class.getResource("second-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        stage.setTitle("Друга сторінка");
        stage.setScene(scene);
        stage.setMinWidth(700);
        stage.setMinHeight(100);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

