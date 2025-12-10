package org.example.demo11;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class Controller {
    private  CollectionAddressBook addressBookImpl =new CollectionAddressBook();
    @FXML
    private Button binSearch;
    @FXML
    private Button binDelete;
    @FXML
    private Button binAdd;
    @FXML
    private Button binEdit;

    @FXML
    private TableColumn<Person, String> columnPIP;
    @FXML
    private TableView<Person> tableAddressBook;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label labelCount;
    @FXML
    private TableColumn<Person, String> columnPhone;
    @FXML
    public void  initialize(){
        columnPIP.setCellValueFactory(new PropertyValueFactory<Person,String>("PIP"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person,String >("Phone"));
        addressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });
        addressBookImpl.fillTestData();
        tableAddressBook.setItems(addressBookImpl.getPersonList());
    }

    private void updateCountLabel(){
        labelCount.setText("Кількість записів: " + addressBookImpl.getPersonList().size());
    }
    @FXML
    private void showDialog(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
            stage.setTitle("Редагування запису");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL );
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
