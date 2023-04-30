module com.greenboard.greenboard {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.greenboard.greenboard to javafx.fxml;
    exports com.greenboard.greenboard;
}