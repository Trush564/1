package org.example.demo11;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class AudioController implements Initializable {

    @FXML
    private Slider volume;

    @FXML
    private ProgressBar audioProgress;

    @FXML
    private Label label_audio;

    private File file;
    private File[] files;

    private Media media;
    private MediaPlayer mediaPlayer;

    private ArrayList<File> songs;
    private int songNumber = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        file = new File("music");
        files = file.listFiles();

        songs = new ArrayList<>();

        if (files != null) {
            for (File f : files) {
                songs.add(f);
                System.out.println(f);
            }
        }

        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        volume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue,
                                Number number, Number t1) {

                mediaPlayer.setVolume(volume.getValue() * 0.01);
            }
        });
    }

    @FXML
    void play(ActionEvent event) {
        mediaPlayer.play();
        label_audio.setText(songs.get(songNumber).getName());
    }

    @FXML
    void pause(ActionEvent event) {
        mediaPlayer.pause();
        label_audio.setText(songs.get(songNumber).getName());
    }

    @FXML
    void next(ActionEvent event) {
        if (songNumber < songs.size() - 1) {
            songNumber++;
        } else {
            songNumber = 0;
        }

        mediaPlayer.stop();
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
        label_audio.setText(songs.get(songNumber).getName());
    }

    @FXML
    void previous(ActionEvent event) {
        if (songNumber > 0) {
            songNumber--;
        } else {
            songNumber = songs.size() - 1;
        }

        mediaPlayer.stop();
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
        label_audio.setText(songs.get(songNumber).getName());
    }
}

