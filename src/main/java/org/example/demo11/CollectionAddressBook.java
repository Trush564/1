package org.example.demo11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CollectionAddressBook implements AddressBook {
    private PersonDAO personDAO = new PersonDAO();
    private ObservableList<Person> personList = FXCollections.observableArrayList();
    private boolean databaseAvailable = false;
    
    public CollectionAddressBook() {
        fillTestData();
        
        try {
            ObservableList<Person> dbPersons = personDAO.getPersons();
            if (dbPersons != null && !dbPersons.isEmpty()) {
                personList.addAll(dbPersons);
                databaseAvailable = true;
                System.out.println("База даних підключена успішно. Додано " + dbPersons.size() + " записів з БД.");
            } else {
                databaseAvailable = true;
                System.out.println("База даних підключена успішно, але таблиця порожня.");
            }
        } catch (Exception e) {
            System.err.println("Помилка підключення до бази даних: " + e.getMessage());
            System.out.println("Перевірте налаштування в DatabaseConnection.java (USER, PASSWORD)");
            System.out.println("Використовуються тільки тестові дані");
            databaseAvailable = false;
        }
    }
    
    public boolean isDatabaseAvailable() {
        return databaseAvailable;
    }
    
    @Override
    public void add(Person person) {
        personList.add(person);
    }
    
    public void addPersonToBD(Person person) throws SQLException {
        if (databaseAvailable) {
            personDAO.savePerson(person);
        } else {
            throw new SQLException("База даних недоступна");
        }
    }
    
    @Override
    public void edit(Person person) {
    }
    
    public void updatePersonInBD(Person person, String oldPip, String oldPhone) throws SQLException {
        if (databaseAvailable) {
            personDAO.updatePerson(person, oldPip, oldPhone);
        } else {
            throw new SQLException("База даних недоступна");
        }
    }
    
    @Override
    public void delete(Person person) {
        if (databaseAvailable) {
            try {
                personDAO.deletePerson(person);
            } catch (SQLException e) {
                System.out.println("Помилка видалення з БД: " + e.getMessage());
                e.printStackTrace();
            }
        }
        personList.remove(person);
    }
    
    public ObservableList<Person> getPersonList() {
        return personList;
    }
    
    public void print() {
        int number = 0;
        System.out.println();
        for (Person person : personList) {
            number++;
            System.out.println(number + ") ПІП: " + person.getPIP() + "; Телефон:" + person.getPHONE());
        }
    }
    
    public void fillTestData() {
        personList.add(new Person("Yulia", "12231"));
        personList.add(new Person("Oksana", "02365"));
        personList.add(new Person("Petro", "465875"));
    }
}
