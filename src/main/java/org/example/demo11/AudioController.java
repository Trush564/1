package org.example.demo11;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AudioController {

    @FXML
    private Slider volumeSlider;

    @FXML
    private ProgressBar audioProgress;

    @FXML
    private Slider seekSlider;

    @FXML
    private Label audioLabel;

    @FXML
    private Label speedLabel;

    private final List<File> songs = new ArrayList<>();
    private javafx.scene.media.MediaPlayer mediaPlayer;
    private int songNumber = 0;
    private boolean isSeeking = false;
    private double currentRate = 1.0;

    public void initialize() {

        loadSongs();

        if (songs.isEmpty()) {
            audioLabel.setText("Папка music порожня");
            return;
        }

        mediaPlayer = new javafx.scene.media.MediaPlayer(new Media(songs.get(songNumber).toURI().toString()));

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (mediaPlayer != null) {
                    mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
                }
            }
        });

        // Налаштування seek slider для перемотування
        if (seekSlider != null) {
            seekSlider.setMin(0);
            seekSlider.setMax(100);
            seekSlider.setValue(0);
            
            seekSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (isSeeking && mediaPlayer != null) {
                    Duration total = mediaPlayer.getTotalDuration();
                    if (total != null && total.toMillis() > 0) {
                        double seekTime = (newVal.doubleValue() / 100.0) * total.toMillis();
                        mediaPlayer.seek(Duration.millis(seekTime));
                    }
                }
            });
            
            seekSlider.setOnMousePressed(e -> isSeeking = true);
            seekSlider.setOnMouseReleased(e -> isSeeking = false);
        }

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!isSeeking && mediaPlayer != null) {
                Duration total = mediaPlayer.getTotalDuration();
                if (total != null && total.toMillis() > 0) {
                    double progress = newTime.toMillis() / total.toMillis();
                    audioProgress.setProgress(progress);
                    if (seekSlider != null) {
                        seekSlider.setValue(progress * 100);
                    }
                }
            }
        });
        
        updateSpeedLabel();
    }

    private void loadSongs() {
        Path musicDir = Paths.get("music");
        if (Files.isDirectory(musicDir)) {
            File[] files = musicDir.toFile().listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".mp3") || name.toLowerCase().endsWith(".wav"));
            if (files != null) {
                for (File f : files) {
                    songs.add(f);
                }
            }
        }
    }

    @FXML
    void playAudio(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.play();
            audioLabel.setText(songs.get(songNumber).getName());
        }
    }

    @FXML
    void pauseAudio(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            audioLabel.setText(songs.get(songNumber).getName());
        }
    }

    @FXML
    void nextAudio(ActionEvent event) {
        if (songs.isEmpty()) return;

        if (songNumber < songs.size() - 1) {
            songNumber++;
        } else {
            songNumber = 0;
        }

        mediaPlayer.stop();
        mediaPlayer = new javafx.scene.media.MediaPlayer(new Media(songs.get(songNumber).toURI().toString()));
        audioLabel.setText(songs.get(songNumber).getName());
        
        setupMediaPlayerListeners();
        
        mediaPlayer.play();
    }

    @FXML
    void previousAudio(ActionEvent event) {
        if (songs.isEmpty()) return;

        if (songNumber > 0) {
            songNumber--;
        } else {
            songNumber = songs.size() - 1;
        }

        mediaPlayer.stop();
        mediaPlayer = new javafx.scene.media.MediaPlayer(new Media(songs.get(songNumber).toURI().toString()));
        audioLabel.setText(songs.get(songNumber).getName());
        
        setupMediaPlayerListeners();
        
        mediaPlayer.play();
    }
    
    private void setupMediaPlayerListeners() {
        // Скидаємо progress bar та seek slider до 0
        audioProgress.setProgress(0);
        if (seekSlider != null) {
            seekSlider.setValue(0);
        }
        
        // Встановлюємо гучність та швидкість для нового програвача
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        mediaPlayer.setRate(currentRate);
        
        // Додаємо listener для progress bar та seek slider
        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!isSeeking && mediaPlayer != null) {
                Duration total = mediaPlayer.getTotalDuration();
                if (total != null && total.toMillis() > 0) {
                    double progress = newTime.toMillis() / total.toMillis();
                    audioProgress.setProgress(progress);
                    if (seekSlider != null) {
                        seekSlider.setValue(progress * 100);
                    }
                }
            }
        });
    }
    
    @FXML
    void setSpeed05x(ActionEvent event) {
        setPlaybackRate(0.5);
    }
    
    @FXML
    void setSpeed1x(ActionEvent event) {
        setPlaybackRate(1.0);
    }
    
    @FXML
    void setSpeed15x(ActionEvent event) {
        setPlaybackRate(1.5);
    }
    
    @FXML
    void setSpeed2x(ActionEvent event) {
        setPlaybackRate(2.0);
    }
    
    private void setPlaybackRate(double rate) {
        currentRate = rate;
        if (mediaPlayer != null) {
            mediaPlayer.setRate(rate);
            updateSpeedLabel();
        }
    }
    
    private void updateSpeedLabel() {
        if (speedLabel != null) {
            speedLabel.setText("Швидкість: " + currentRate + "x");
        }
    }
}

