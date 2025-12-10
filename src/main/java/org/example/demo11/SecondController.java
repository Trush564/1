package org.example.demo11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class SecondController {
    @FXML
    private TextField txtPip;
    
    @FXML
    private TextField txtPhone;
    
    private Person person;
    private Person originalPerson;
    private CollectionAddressBook collectionAddressBook;
    private boolean isEditMode = false;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        if (person != null) {
            txtPip.setText(person.getPip());
            txtPhone.setText(person.getPhone());
            if (person.getId() != null) {
                isEditMode = true;
                originalPerson = new Person(person.getId(), person.getPip(), person.getPhone());
            } else {
                isEditMode = false;
                originalPerson = null;
            }
        }
    }

    public void setCollectionAddressBook(CollectionAddressBook collectionAddressBook) {
        this.collectionAddressBook = collectionAddressBook;
    }

    @FXML
    void actionSave(ActionEvent event) {
        if (person != null) {
            String oldPip = person.getPip();
            String oldPhone = person.getPhone();
            
            person.setPip(txtPip.getText());
            person.setPhone(txtPhone.getText());
            
            if (collectionAddressBook != null) {
                try {
                    if (isEditMode && originalPerson != null) {
                        collectionAddressBook.updatePersonInBD(person, originalPerson.getPip(), originalPerson.getPhone());
                    } else {
                        collectionAddressBook.addPersonToBD(person);
                    }
                } catch (SQLException e) {
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                    alert.setTitle("Помилка збереження");
                    alert.setHeaderText("Не вдалося зберегти дані в базу даних");
                    alert.setContentText("Помилка: " + e.getMessage() + "\n\nДані збережено локально.");
                    alert.showAndWait();
                }
            }
        }
        actionClose(event);
    }

    @FXML
    void actionClose(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }
}
