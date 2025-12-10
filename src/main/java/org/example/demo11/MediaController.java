package org.example.demo11;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Duration;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MediaController implements Initializable {
    @FXML
    private Button pauseButton;

    @FXML
    private MediaController mediaView;

    @FXML
    private Button playButton;

    @FXML
    private Button resetButton;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        file = new File("video.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);
    }

    @FXML
    void playButton_method(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    void pauseButton_method(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    void resetButton_method(ActionEvent event) {
        if (mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
            mediaPlayer.play();
        }
    }
}