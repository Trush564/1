package org.example.demo11;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private Integer id;
    private SimpleStringProperty pip = new SimpleStringProperty("");
    private SimpleStringProperty phone = new SimpleStringProperty("");

    public Person() {
        this("", "");
    }

    public Person(String pip, String phone) {
        this.pip = new SimpleStringProperty(pip);
        this.phone = new SimpleStringProperty(phone);
    }

    public Person(Integer id, String pip, String phone) {
        this.id = id;
        this.pip = new SimpleStringProperty(pip);
        this.phone = new SimpleStringProperty(phone);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPip() {
        return pip.get();
    }

    public StringProperty pipProperty() {
        return pip;
    }

    public void setPip(String pip) {
        this.pip.set(pip);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getPIP() {
        return getPip();
    }

    public String getPHONE() {
        return getPhone();
    }

    public void setPIP(String pip) {
        setPip(pip);
    }

    public void setPHONE(String phone) {
        setPhone(phone);
    }

    @Override
    public String toString() {
        return "Person{" +
                "pip=" + pip +
                ", phone=" + phone +
                '}';
    }
}
