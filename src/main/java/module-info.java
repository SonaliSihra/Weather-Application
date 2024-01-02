module com.example.weatherapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens com.example.weatherapplication to javafx.fxml;
    exports com.example.weatherapplication;
}