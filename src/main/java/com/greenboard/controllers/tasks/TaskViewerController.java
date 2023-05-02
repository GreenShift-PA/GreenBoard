package com.greenboard.controllers.tasks;

import com.greenboard.db.PostgreSQL;
import com.greenboard.enums.TaskPriority;
import com.greenboard.enums.TaskStatus;
import com.greenboard.models.Task;
import com.greenboard.models.User;
import com.greenboard.singletons.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskViewerController {
    public Label taskUsers;
    @FXML
    private Label taskName;

    @FXML
    private TextArea taskDescription;

    @FXML
    private Label taskStatus;

    @FXML
    private Label taskPriority;

    @FXML
    private DatePicker taskDueDate;

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty priority = new SimpleStringProperty();
    private final StringProperty dueDate = new SimpleStringProperty();

    private boolean isEditMode = false;

    public void initialize() {
        name.addListener((observable, oldValue, newValue) -> taskName.setText(newValue));
        description.addListener((observable, oldValue, newValue) -> taskDescription.setText(newValue));
        status.addListener((observable, oldValue, newValue) -> taskStatus.setText(newValue));
        priority.addListener((observable, oldValue, newValue) -> taskPriority.setText(newValue));
        dueDate.addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            taskDueDate.setValue(taskDueDate.getConverter().fromString(newValue));
        });
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

    public void setDueDate(String value) {
        dueDate.set(value);
    }

    public String getDueDate() {
        return dueDate.get();
    }

    public void onCancel(ActionEvent actionEvent) {
        // close the window
        taskName.getScene().getWindow().hide();
    }

    @FXML
    public void onSave(ActionEvent actionEvent) {
        saveTask();
    }

    public void saveTask() {
        if (!isEditMode()) {
            // Create a new task
            Task task = new Task();
            task.setName(getName());
            task.setDescription(taskDescription.getText());
            task.setStatus(TaskStatus.fromString(getStatus()));
            task.setPriority(TaskPriority.fromString(getPriority()));
            task.setDueDate(LocalDate.parse(taskDueDate.getValue().toString()));

            // Set the users of the task (assuming taskUsers is a comma-separated string of usernames)
            String[] usernames = taskUsers.getText().split(", ");
            List<User> users = new ArrayList<>();
            for (String username : usernames) {
                User user = PostgreSQL.getUserByUsername(username);
                if(user != null)
                    users.add(user);
            }
            task.setUsers(users);

            // Save the task to the database
            if(PostgreSQL.createTask(task) != null) {
                System.out.println("Task created successfully");
                Application.getInstance().getTasks();
            } else {
                System.out.println("Task creation failed");
            }
        }

        // Close the window
        taskName.getScene().getWindow().hide();
    }


    public boolean isEditMode() {
        return isEditMode;
    }

    public void setEditMode(boolean editMode) {
        isEditMode = editMode;
    }
}
