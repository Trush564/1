package org.example.demo11;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Lab7AudioController {

    @FXML
    private Label audioLabel;

    @FXML
    private Slider volumeSlider;

    @FXML
    private ProgressBar audioProgress;

    @FXML
    private Slider seekSlider;

    @FXML
    private Label speedLabel;

    private final List<File> songs = new ArrayList<>();
    private MediaPlayer audioPlayer;
    private int songIndex = 0;
    private boolean isSeeking = false;
    private double currentRate = 1.0;

    public void initialize() {
        loadSongs();
        if (songs.isEmpty()) {
            audioLabel.setText("Папка music порожня");
            return;
        }

        startAudioPlayer(songs.get(songIndex));
        volumeSlider.setValue(50);
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (audioPlayer != null) {
                    audioPlayer.setVolume(newValue.doubleValue() / 100.0);
                }
            }
        });
        
        // Налаштування seek slider для перемотування
        if (seekSlider != null) {
            seekSlider.setMin(0);
            seekSlider.setMax(100);
            seekSlider.setValue(0);
            
            seekSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (isSeeking && audioPlayer != null) {
                    Duration total = audioPlayer.getTotalDuration();
                    if (total != null && total.toMillis() > 0) {
                        double seekTime = (newVal.doubleValue() / 100.0) * total.toMillis();
                        audioPlayer.seek(Duration.millis(seekTime));
                    }
                }
            });
            
            seekSlider.setOnMousePressed(e -> isSeeking = true);
            seekSlider.setOnMouseReleased(e -> isSeeking = false);
        }
        
        updateSpeedLabel();
    }

    private void loadSongs() {
        Path musicDir = Paths.get("music");
        if (Files.isDirectory(musicDir)) {
            File[] files = musicDir.toFile().listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".mp3") || name.toLowerCase().endsWith(".wav"));
            if (files != null) {
                for (File file : files) {
                    songs.add(file);
                }
            }
        }
    }

    private void startAudioPlayer(File file) {
        if (audioPlayer != null) {
            audioPlayer.stop();
        }
        Media media = new Media(file.toURI().toString());
        audioPlayer = new MediaPlayer(media);
        audioLabel.setText(file.getName());
        
        // Скидаємо progress bar та seek slider до 0
        audioProgress.setProgress(0);
        if (seekSlider != null) {
            seekSlider.setValue(0);
        }

        // Додаємо listener для progress bar та seek slider
        audioPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!isSeeking && audioPlayer != null) {
                Duration total = audioPlayer.getTotalDuration();
                if (total != null && total.toMillis() > 0) {
                    double progress = newTime.toMillis() / total.toMillis();
                    audioProgress.setProgress(progress);
                    if (seekSlider != null) {
                        seekSlider.setValue(progress * 100);
                    }
                }
            }
        });
        
        // Встановлюємо гучність та швидкість для нового програвача
        if (volumeSlider != null) {
            audioPlayer.setVolume(volumeSlider.getValue() / 100.0);
        }
        audioPlayer.setRate(currentRate);
    }

    @FXML
    private void playAudio(ActionEvent event) {
        if (audioPlayer != null) {
            audioPlayer.play();
        }
    }

    @FXML
    private void pauseAudio(ActionEvent event) {
        if (audioPlayer != null) {
            audioPlayer.pause();
        }
    }

    @FXML
    private void nextAudio(ActionEvent event) {
        if (songs.isEmpty()) {
            return;
        }
        songIndex = (songIndex + 1) % songs.size();
        startAudioPlayer(songs.get(songIndex));
        audioPlayer.play();
    }

    @FXML
    private void previousAudio(ActionEvent event) {
        if (songs.isEmpty()) {
            return;
        }
        songIndex = (songIndex - 1 + songs.size()) % songs.size();
        startAudioPlayer(songs.get(songIndex));
        audioPlayer.play();
    }
    
    @FXML
    private void setSpeed05x(ActionEvent event) {
        setPlaybackRate(0.5);
    }
    
    @FXML
    private void setSpeed1x(ActionEvent event) {
        setPlaybackRate(1.0);
    }
    
    @FXML
    private void setSpeed15x(ActionEvent event) {
        setPlaybackRate(1.5);
    }
    
    @FXML
    private void setSpeed2x(ActionEvent event) {
        setPlaybackRate(2.0);
    }
    
    private void setPlaybackRate(double rate) {
        currentRate = rate;
        if (audioPlayer != null) {
            audioPlayer.setRate(rate);
            updateSpeedLabel();
        }
    }
    
    private void updateSpeedLabel() {
        if (speedLabel != null) {
            speedLabel.setText("Швидкість: " + currentRate + "x");
        }
    }
}

