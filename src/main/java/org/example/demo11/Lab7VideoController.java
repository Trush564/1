package org.example.demo11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lab7VideoController {

    @FXML
    private MediaView mediaView;

    @FXML
    private Button videoPlayButton;

    @FXML
    private Button videoPauseButton;

    @FXML
    private Button videoResetButton;

    private MediaPlayer videoPlayer;

    public void initialize() {
        setupVideoPlayer();
    }

    private void setupVideoPlayer() {
        File videoFile = findFileNearProjectRoot("video.mp4");
        if (videoFile != null) {
            Media media = new Media(videoFile.toURI().toString());
            videoPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(videoPlayer);
        } else {
            disableVideoControls();
        }
    }

    @FXML
    private void playVideo(ActionEvent event) {
        if (videoPlayer != null) {
            videoPlayer.play();
        }
    }

    @FXML
    private void pauseVideo(ActionEvent event) {
        if (videoPlayer != null) {
            videoPlayer.pause();
        }
    }

    @FXML
    private void resetVideo(ActionEvent event) {
        if (videoPlayer != null && videoPlayer.getStatus() != MediaPlayer.Status.READY) {
            videoPlayer.seek(Duration.ZERO);
            videoPlayer.play();
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
        videoPlayButton.setDisable(true);
        videoPauseButton.setDisable(true);
        videoResetButton.setDisable(true);
        mediaView.setDisable(true);
    }
}

