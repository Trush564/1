package org.example.demo11;

import javafx.fxml.FXML;
import javafx.util.Duration;

import java.awt.event.ActionEvent;

public class MediaPlayer {
    @FXML
    void playButton_method(ActionEvent event) { // реалізація кнопки Play
        mediaPlayer.play();
    }
    @FXML
    void pauseButton_method(ActionEvent event) { // реалізація кнопки Pause
        mediaPlayer.pause();
    }
    @FXML
    void resetButton_method(ActionEvent event) { // реалізація кнопки Reset
        if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
            mediaPlayer.play();
        }
    }
}
