package org.example.demo11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Lab6Controller implements Initializable {

    @FXML
    private CheckBox ChBPogod;

    @FXML
    private CheckBox ChBGnuch;

    @FXML
    private CheckBox ChBDruz;

    @FXML
    private CheckBox ChBSklad;

    @FXML
    private Label lblTrueAnswer;

    @FXML
    private ChoiceBox<String> choiceB;

    @FXML
    private Label lblAnwerChoice;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Label lblAnwerCombo;

    @FXML
    private RadioButton radioProp;

    @FXML
    private RadioButton radioLay;

    @FXML
    private RadioButton radioCode;

    @FXML
    private RadioButton radioHier;

    @FXML
    private Label lblRadio;

    @FXML
    private Label progressLabel;

    @FXML
    private AnchorPane scenePane;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private CheckMenuItem showDetailsMenuItem;

    private ToggleGroup radiotoggleGroup;
    
    private boolean checkBoxCorrect = false;
    private boolean choiceBoxCorrect = false;
    private boolean comboBoxCorrect = false;
    private boolean radioButtonCorrect = false;
    
    private static final int TOTAL_QUESTIONS = 4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTrueAnswer.setText("");
        lblAnwerChoice.setText("");
        lblAnwerCombo.setText("");
        lblRadio.setText("");
        updateResult();

        choiceB.getItems().addAll("Правильно", "Неправильно");

        comboBox.getItems().addAll("BorderPane", "AncorePane", "VBox", "HBox");
        comboBox.setOnAction(this::comboBoxPressed);

        radiotoggleGroup = new ToggleGroup();
        radioProp.setToggleGroup(radiotoggleGroup);
        radioLay.setToggleGroup(radiotoggleGroup);
        radioCode.setToggleGroup(radiotoggleGroup);
        radioHier.setToggleGroup(radiotoggleGroup);

        radiotoggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioAnswer(null);
            }
        });
        
        // Додаємо гарячу клавішу Ctrl+Q для Exit
        exitMenuItem.setAccelerator(new KeyCodeCombination(javafx.scene.input.KeyCode.Q, KeyCombination.CONTROL_DOWN));
    }
    
    private void updateResult() {
        int correct = 0;
        if (checkBoxCorrect) correct++;
        if (choiceBoxCorrect) correct++;
        if (comboBoxCorrect) correct++;
        if (radioButtonCorrect) correct++;
        
        progressLabel.setText("Результат: " + correct + "/" + TOTAL_QUESTIONS);
    }

    @FXML
    void checkBoxAnswer(ActionEvent event) {
        String answer = "Ваша відповідь:";
        if (ChBPogod.isSelected())
            answer += "\nпогодженість";
        if (ChBGnuch.isSelected())
            answer += "\nгнучкість";
        if (ChBDruz.isSelected())
            answer += "\nдружність";
        if (ChBSklad.isSelected())
            answer += "\nскладність";

        boolean isCorrect = ChBPogod.isSelected() && 
                           ChBGnuch.isSelected() && 
                           ChBDruz.isSelected() && 
                           !ChBSklad.isSelected();
        
        if (isCorrect) {
            answer += "\n\n✓ Правильно!";
            lblTrueAnswer.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        } else {
            answer += "\n\n✗ Неправильно";
            lblTrueAnswer.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        }

        lblTrueAnswer.setText(answer);
        
        checkBoxCorrect = isCorrect;
        updateResult();
    }

    @FXML
    void choiceBoxAnswer(ActionEvent event) {
        if (choiceB.getValue() != null) {
            String selectedValue = choiceB.getValue();
            boolean isCorrect = "Неправильно".equals(selectedValue);
            
            String answer = "Відповідь: " + selectedValue;
            if (isCorrect) {
                answer += "\n✓ Правильно!";
                lblAnwerChoice.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            } else {
                answer += "\n✗ Неправильно";
                lblAnwerChoice.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            }
            
            lblAnwerChoice.setText(answer);
            
            choiceBoxCorrect = isCorrect;
            updateResult();
        }
    }

    @FXML
    void comboBoxPressed(ActionEvent event) {
        if (comboBox.getValue() != null) {
            String selectedValue = comboBox.getValue();
            boolean isCorrect = "BorderPane".equals(selectedValue);
            
            String answer = "Відповідь: " + selectedValue;
            if (isCorrect) {
                answer += "\n✓ Правильно!";
                lblAnwerCombo.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            } else {
                answer += "\n✗ Неправильно";
                lblAnwerCombo.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            }
            
            lblAnwerCombo.setText(answer);
            
            comboBoxCorrect = isCorrect;
            updateResult();
        }
    }

    @FXML
    void RadioAnswer(ActionEvent event) {
        Toggle selected = radiotoggleGroup.getSelectedToggle();
        if (selected != null) {
            String answer = "";
            boolean isCorrect = false;
            
            if (selected.equals(radioCode)) {
                answer = "Відповідь: Code";
                isCorrect = false;
            } else if (selected.equals(radioHier)) {
                answer = "Відповідь: Hierarchy";
                isCorrect = false;
            } else if (selected.equals(radioLay)) {
                answer = "Відповідь: Layout";
                isCorrect = false;
            } else if (selected.equals(radioProp)) {
                answer = "Відповідь: Properties";
                isCorrect = true;
            }
            
            if (isCorrect) {
                answer += "\n✓ Правильно!";
                lblRadio.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            } else {
                answer += "\n✗ Неправильно";
                lblRadio.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            }
            
            lblRadio.setText(answer);
            
            radioButtonCorrect = isCorrect;
            updateResult();
        }
    }

    @FXML
    void exitButton(ActionEvent event) {
        Stage stage = (Stage) scenePane.getScene().getWindow();
        System.out.println("Success");
        stage.close();
    }

    @FXML
    void newFile(ActionEvent event) {
        System.out.println("New file");
    }

    @FXML
    void newFromTemplate(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        System.out.println("New from template: " + source.getText());
    }

    @FXML
    void toggleShowDetails(ActionEvent event) {
        System.out.println("Show Details: " + showDetailsMenuItem.isSelected());
    }
}

