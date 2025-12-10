package org.example.demo11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Допоміжний контролер для простого відеоплеєра (альтернативний варіант).
 * Використовується той самий файл video.mp4 поряд із проєктом.
 */
public class MediaPlayer {

    private javafx.scene.media.MediaPlayer mediaPlayer;

    public void initialize() {
        File file = findFileNearProjectRoot("video.mp4");
        if (file != null) {
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new javafx.scene.media.MediaPlayer(media);
        }
    }

    @FXML
    void playButton_method(ActionEvent event) { // реалізація кнопки Play
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    @FXML
    void pauseButton_method(ActionEvent event) { // реалізація кнопки Pause
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    void resetButton_method(ActionEvent event) { // реалізація кнопки Reset
        if (mediaPlayer != null && mediaPlayer.getStatus() != javafx.scene.media.MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
            mediaPlayer.play();
        }
    }

    private File findFileNearProjectRoot(String fileName) {
        Path projectRoot = Paths.get("").toAbsolutePath();
        Path candidate = projectRoot.resolve(fileName);
        if (Files.exists(candidate)) {
            return candidate.toFile();
        }
        return null;
    }
}

