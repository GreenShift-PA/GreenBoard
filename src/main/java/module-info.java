module com.greenboard {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.greenboard to javafx.fxml;
    opens com.greenboard.controllers to javafx.fxml;
    opens com.greenboard.controllers.tasks to javafx.fxml;
    exports com.greenboard.controllers;
    exports com.greenboard.controllers.tasks;
    exports com.greenboard.models;
    exports com.greenboard.enums;
    exports com.greenboard.factories;
    exports com.greenboard;

    opens com.greenboard.auth to javafx.fxml;
    exports com.greenboard.auth;

    // postgresql
    requires java.sql;
}