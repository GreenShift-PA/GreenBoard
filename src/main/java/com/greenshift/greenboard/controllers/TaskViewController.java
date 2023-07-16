package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.models.entities.Task;
import com.greenshift.greenboard.models.entities.Task.TaskBuilder;
import com.greenshift.greenboard.models.entities.TaskStatus;
import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.services.TaskService;
import com.greenshift.greenboard.singletons.SessionManager;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TaskViewController {
    public HBox statusHBox;
    public TextField nameTextField;
    public Label statusLabel;
    public Label assignLabel;
    public Label dueLabel;
    public Label createdAtLabel;
    public Label projectLabel;
    public TextArea descriptionTextArea;

    private Task task;

    TaskService taskService = new TaskService("http://localhost:3000/api/v1/tasks");


    public void initialize() {

        nameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                String nameValue = nameTextField.getText().isEmpty() ? "Untitled" : nameTextField.getText();
                task.setName(nameValue);
                task = taskService.update(task, Task.class);
            }
        });

        descriptionTextArea.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                task.setDescription(descriptionTextArea.getText());
                task = taskService.update(task, Task.class);
            }
        });
    }

    public void init(TaskStatus taskStatus) {

        User currentUser = SessionManager.getInstance().getCurrentUser();

        if(currentUser == null) {
            System.out.println("User is null");
            return;
        }

        statusLabel.setText(taskStatus.getName());
        statusHBox.setStyle("-fx-background-color: " + taskStatus.getColor());

        task = new TaskBuilder()
                .setName("Untitled")
                .setStatus(taskStatus)
                .setPriority("LOW")
                .setProjectId(currentUser.getLastProjectId())
                .setAuthorId(currentUser.getId())
                .setTeamId(currentUser.getLastTeamId())
                .build();


        task = taskService.create(task, Task.class);
    }

    public void initWithTask(Task task) {
        this.task = task;
        nameTextField.setText(task.getName());
        descriptionTextArea.setText(task.getDescription());
        statusLabel.setText(task.getStatus().getName());
        statusHBox.setStyle("-fx-background-color: " + task.getStatus().getColor());
    }
}
