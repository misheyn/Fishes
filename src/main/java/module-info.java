module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.company.JavaFish to javafx.fxml;
    exports com.company.JavaFish;
}