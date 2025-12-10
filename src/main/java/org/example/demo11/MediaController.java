package org.example.demo11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MediaController {

    @FXML
    private MediaView mediaView;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private Button resetButton;

    private javafx.scene.media.MediaPlayer mediaPlayer;

    public void initialize() {
        setupVideo();
    }

    private void setupVideo() {
        File file = findFileNearProjectRoot("video.mp4");
        if (file != null) {
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new javafx.scene.media.MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
        } else {
            disableVideoControls();
        }
    }

    @FXML
    void playButton_method(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    @FXML
    void pauseButton_method(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    void resetButton_method(ActionEvent event) {
        if (mediaPlayer != null && mediaPlayer.getStatus() != javafx.scene.media.MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.ZERO);
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

    private void disableVideoControls() {
        playButton.setDisable(true);
        pauseButton.setDisable(true);
        resetButton.setDisable(true);
        mediaView.setDisable(true);
    }
}

