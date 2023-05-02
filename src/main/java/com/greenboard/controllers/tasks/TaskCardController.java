package com.greenboard.controllers.tasks;

import com.greenboard.models.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TaskCardController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label statusLabel;

    @FXML
    private Label priorityLabel;

    @FXML
    private Label usersLabel;


    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty priority = new SimpleStringProperty();
    private final StringProperty users = new SimpleStringProperty();

    public TaskCardController() {
        name.addListener((observable, oldValue, newValue) -> nameLabel.setText(newValue));
        description.addListener((observable, oldValue, newValue) -> descriptionLabel.setText(newValue));
        status.addListener((observable, oldValue, newValue) -> statusLabel.setText(newValue));
        priority.addListener((observable, oldValue, newValue) -> priorityLabel.setText(newValue));
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

    public void setPriority(String value) {
        priority.set(value);
    }

    public String getPriority() {
        return priority.get();
    }

    public void setUsers(List<User> users) {
        this.users.set(users.stream().map(User::getUsername).reduce("", (acc, user) -> acc + user + ", "));
    }

    @FXML
    private void handleTaskClicked() throws IOException {
        // open a modal with the task details
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/greenboard/task-viewer.fxml"));
        Parent root = fxmlLoader.load();


        Stage stage = new Stage();
        stage.setTitle("Task Details");
        stage.setScene(new Scene(root));

        TaskViewerController controller = fxmlLoader.getController();
        controller.setName(getName());
        controller.setDescription(getDescription());
        controller.setStatus(getStatus());
        controller.setPriority(getPriority());
        controller.setEditMode(true);

        stage.show();
    }
}
