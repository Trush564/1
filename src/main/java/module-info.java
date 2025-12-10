module org.example.demo11 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens org.example.demo11 to javafx.fxml;
    exports org.example.demo11;
}