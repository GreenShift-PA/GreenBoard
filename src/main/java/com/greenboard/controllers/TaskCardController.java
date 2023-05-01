package com.greenboard.controllers;

import com.greenboard.models.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class TaskCardController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label statusLabel;

    @FXML
    private Label usersLabel;

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final StringProperty users = new SimpleStringProperty();

    public TaskCardController() {
        name.addListener((observable, oldValue, newValue) -> nameLabel.setText(newValue));
        description.addListener((observable, oldValue, newValue) -> descriptionLabel.setText(newValue));
        status.addListener((observable, oldValue, newValue) -> statusLabel.setText(newValue));
        users.addListener((observable, oldValue, newValue) -> usersLabel.setText(newValue));
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

    public void setUsers(List<User> users) {
        this.users.set(users.stream().map(User::getUsername).reduce("", (acc, user) -> acc + user + ", "));
    }

}
