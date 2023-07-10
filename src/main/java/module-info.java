module com.greenshift.greenboard.greenboard {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires net.synedra.validatorfx;

    opens com.greenshift.greenboard to javafx.fxml;
    exports com.greenshift.greenboard;

    opens com.greenshift.greenboard.applications to javafx.fxml;
    exports com.greenshift.greenboard.applications;

    opens com.greenshift.greenboard.controllers to javafx.fxml;
    exports com.greenshift.greenboard.controllers;
}