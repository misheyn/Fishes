module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.company.JavaFish to javafx.fxml;
    exports com.company.JavaFish;
    exports com.company.JavaFish.StartWindow;
    opens com.company.JavaFish.StartWindow to javafx.fxml;
    exports com.company.JavaFish.Models;
    opens com.company.JavaFish.Models to javafx.fxml;
    exports com.company.JavaFish.ThreadsAI;
    opens com.company.JavaFish.ThreadsAI to javafx.fxml;
    exports com.company.JavaFish.FishLogWindow;
    opens com.company.JavaFish.FishLogWindow to javafx.fxml;
    exports com.company.JavaFish.ResultWindow;
    opens com.company.JavaFish.ResultWindow to javafx.fxml;
    exports com.company.JavaFish.MainWindow;
    opens com.company.JavaFish.MainWindow to javafx.fxml;
}