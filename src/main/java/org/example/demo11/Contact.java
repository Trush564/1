package org.example.demo11;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {
    private StringProperty name;
    private StringProperty phone;
    public Contact() {this("",""); }
    public Contact(String name, String phone) {
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
    }
    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public  StringProperty nameProperty() { return name; }

    public String getPhone() { return phone.get(); }
    public void setPhone(String phone) { this.phone.set(phone); }
    public  StringProperty phoneProperty() { return phone; }

}
