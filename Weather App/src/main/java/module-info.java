module org.example.weather_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens org.example.weather_app to javafx.fxml;
    exports org.example.weather_app;
}