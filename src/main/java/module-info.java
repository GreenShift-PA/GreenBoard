module com.greenshift.greenboard {
    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    // Controls and Libraries
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    // Icons and Fonts
    requires org.kordamp.ikonli.javafx;
    requires de.jensd.fx.glyphs.fontawesome;

    // Ikonli Packs
    requires org.kordamp.ikonli.antdesignicons;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.materialdesign2;

    // Third-party Libraries
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.google.gson;
    requires com.jfoenix;
    requires com.google.api.services.translate;
    requires java.xml.bind;
    requires com.sun.xml.bind;
    requires java.activation;
    requires jakarta.xml.bind;
    requires com.sun.xml.bind.core;

    opens com.greenshift.greenboard to javafx.fxml;
    opens com.greenshift.greenboard.applications to javafx.fxml;
    opens com.greenshift.greenboard.controllers to javafx.fxml;
    opens com.greenshift.greenboard.models to com.google.gson, jakarta.xml.bind;
    opens com.greenshift.greenboard.models.entities to com.google.gson, jakarta.xml.bind;
    opens com.greenshift.greenboard.services to com.google.gson;


    exports com.greenshift.greenboard;
    exports com.greenshift.greenboard.applications;
    exports com.greenshift.greenboard.controllers;
    exports com.greenshift.greenboard.controllers.settings;
    exports com.greenshift.greenboard.models;
    exports com.greenshift.greenboard.services;
    exports com.greenshift.greenboard.converters;
    opens com.greenshift.greenboard.converters to javafx.fxml;
    exports com.greenshift.greenboard.utils;
    opens com.greenshift.greenboard.controllers.settings to javafx.fxml;


}

