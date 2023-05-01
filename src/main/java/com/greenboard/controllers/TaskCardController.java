package com.greenboard.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TaskCardController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label statusLabel;

    private StringProperty name = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();

    public TaskCardController() {
        name.addListener((observable, oldValue, newValue) -> nameLabel.setText(newValue));
        description.addListener((observable, oldValue, newValue) -> descriptionLabel.setText(newValue));
        status.addListener((observable, oldValue, newValue) -> statusLabel.setText(newValue));
    }

    public void setName(String value) {
        name.set(value);
    }

    public String getName() {
        return name.get();
    }

    public void setDescription(String value) {
        description.set(value);
    }

    public String getDescription() {
        return description.get();
    }

    public void setStatus(String value) {
        status.set(value);
    }

    public String getStatus() {
        return status.get();
    }
}
