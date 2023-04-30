module com.greenboard {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.greenboard to javafx.fxml;
    exports com.greenboard;

    opens com.greenboard.auth to javafx.fxml;
    exports com.greenboard.auth;

    // postgresql
    requires java.sql;

}