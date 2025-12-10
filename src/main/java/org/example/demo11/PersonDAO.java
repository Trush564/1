package org.example.demo11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class PersonDAO {
    
    public ObservableList<Person> getPersons() {
        ObservableList<Person> personObservableList = FXCollections.observableArrayList();
        String query = "SELECT * FROM addressbook";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                Person person = new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("pip"),
                    resultSet.getString("phone")
                );
                personObservableList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return personObservableList;
    }
    
    public void savePerson(Person person) throws SQLException {
        String sql = "INSERT INTO addressbook (pip, phone) VALUES (?,?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            if (connection.getAutoCommit() == false) {
                connection.setAutoCommit(true);
            }
            
            statement.setString(1, person.getPip());
            statement.setString(2, person.getPhone());
            
            System.out.println("Executing SQL: " + sql);
            System.out.println("PIP: " + person.getPip() + ", Phone: " + person.getPhone());
            
            statement.executeUpdate();
        }
    }
    
    public void updatePerson(Person person, String oldPip, String oldPhone) throws SQLException {
        String sql = "UPDATE addressbook SET pip = ?, phone = ? WHERE pip = ? AND phone = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, person.getPip());
            statement.setString(2, person.getPhone());
            statement.setString(3, oldPip);
            statement.setString(4, oldPhone);
            
            statement.executeUpdate();
        }
    }
    
    public void deletePerson(Person person) throws SQLException {
        String sql = "DELETE FROM addressbook WHERE pip = ? AND phone = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, person.getPip());
            statement.setString(2, person.getPhone());
            
            statement.executeUpdate();
        }
    }
}

