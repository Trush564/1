package org.example.demo11;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    
    @FXML
    private TableView<Person> tableAddressBook;
    
    @FXML
    private TableColumn<Person, String> columnPIP;
    
    @FXML
    private TableColumn<Person, String> columnPhone;
    
    @FXML
    private Label labelCount;
    
    @FXML
    private TextField txtSearch;
    
    @FXML
    private Button btnSearch;
    
    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();
    private FilteredList<Person> filteredList;
    
    private Stage editDialogStage;
    private Parent root;
    private SecondController editController;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    private void showDialog() {
        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редагування запису");
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(root));
            Stage parentStage = (Stage) tableAddressBook.getScene().getWindow();
            editDialogStage.initOwner(parentStage);
        }
        editDialogStage.showAndWait();
    }

    @FXML
    private void openWindow(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();
        editController.setCollectionAddressBook(addressBookImpl);
        
        switch (clickedButton.getId()) {
            case "addButton":
                Person newPerson = new Person();
                editController.setPerson(newPerson);
                showDialog();
                if (newPerson != null && newPerson.getPip() != null && !newPerson.getPip().trim().isEmpty()) {
                    addressBookImpl.add(newPerson);
                }
                break;
            case "editButton":
                Person selectedPerson = tableAddressBook.getSelectionModel().getSelectedItem();
                if (selectedPerson != null) {
                    editController.setPerson(selectedPerson);
                    showDialog();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Попередження");
                    alert.setHeaderText("Не вибрано запис");
                    alert.setContentText("Будь ласка, виберіть запис для редагування.");
                    alert.showAndWait();
                }
                break;
            case "deleteButton":
                Person personToDelete = tableAddressBook.getSelectionModel().getSelectedItem();
                if (personToDelete != null) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Видалення");
                    alert.setHeaderText("Підтвердження видалення");
                    alert.setContentText("Ви дійсно хочете видалити контакт?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            addressBookImpl.delete(personToDelete);
                        }
                    });
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Попередження");
                    alert.setHeaderText("Не вибрано запис");
                    alert.setContentText("Будь ласка, виберіть запис для видалення.");
                    alert.showAndWait();
                }
                break;
        }
    }
    @FXML
    private javafx.scene.control.Button btnOther;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnOther.setOnAction(actionEvent -> openOtherLabs());
        
        columnPIP.setCellValueFactory(new PropertyValueFactory<>("pip"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        filteredList = new FilteredList<>(addressBookImpl.getPersonList(), p -> true);
        tableAddressBook.setItems(filteredList);
        
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue);
        });
        
        updateCountLabel();
        
        addressBookImpl.getPersonList().addListener((ListChangeListener<Person>) c -> {
            updateCountLabel();
            filterTable(txtSearch.getText());
        });
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("second-view.fxml"));
            root = fxmlLoader.load();
            editController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void searchPerson(ActionEvent event) {
        filterTable(txtSearch.getText());
    }
    
    private void filterTable(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            filteredList.setPredicate(person -> true);
        } else {
            String lowerCaseFilter = searchText.toLowerCase();
            filteredList.setPredicate(person -> {
                boolean matchesPip = person.getPip() != null && 
                    person.getPip().toLowerCase().contains(lowerCaseFilter);
                boolean matchesPhone = person.getPhone() != null && 
                    person.getPhone().toLowerCase().contains(lowerCaseFilter);
                return matchesPip || matchesPhone;
            });
        }
        updateCountLabel();
    }
    
    private void updateCountLabel() {
        int totalCount = addressBookImpl.getPersonList().size();
        int filteredCount = filteredList.size();
        if (txtSearch != null && txtSearch.getText() != null && !txtSearch.getText().isEmpty()) {
            labelCount.setText("Кількість записів: " + filteredCount + " (з " + totalCount + ")");
        } else {
            labelCount.setText("Кількість записів: " + totalCount);
        }
    }

    @FXML
    private void openOtherLabs() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("other.fxml"));
            Scene scene = new Scene(loader.load(), 600, 700);

            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            Stage stage = new Stage();
            stage.setTitle("Інші лабораторні роботи");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}