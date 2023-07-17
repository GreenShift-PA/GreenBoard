package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.models.entities.*;
import com.greenshift.greenboard.models.entities.Task.TaskBuilder;
import com.greenshift.greenboard.services.TaskService;
import com.greenshift.greenboard.singletons.SessionManager;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class TaskViewController {
    public HBox statusHBox;
    public TextField nameTextField;
    public Label statusLabel;
    public Label assignLabel;
    public Label dueLabel;
    public Label createdAtLabel;
    public Label projectLabel;
    public TextArea descriptionTextArea;
    public HBox assignHBox;
    public HBox dueHBox;
    public HBox projectHBox;
    public ComboBox<User> assignComboBox = new ComboBox<>();
    public DatePicker dueDatePicker = new DatePicker();
    public ComboBox<Project> projectComboBox = new ComboBox<>();
    TaskService taskService = new TaskService();
    List<Project> projects = new ArrayList<>();
    List<User> users = new ArrayList<>();
    private Task task;

    public void initialize() {

        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser == null)
            return;

        System.out.println("Current user: " + currentUser.getTeams().stream().map(Team::getProjects).toList());
        projects = currentUser.getTeams().stream().map(Team::getProjects).flatMap(List::stream).toList();
        if (currentUser.getLastTeam() != null)
            users = currentUser.getLastTeam().getMembers();
        else
            users = currentUser.getTeams().stream().map(Team::getMembers).flatMap(List::stream).toList();

        nameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String nameValue = nameTextField.getText().isEmpty() ? "Untitled" : nameTextField.getText();
                task.setName(nameValue);
                task = taskService.update(task);
            }
        });

        descriptionTextArea.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                task.setDescription(descriptionTextArea.getText());
                task = taskService.update(task);
            }
        });

        assignHBox.setOnMouseClicked(event -> {
            assignHBox.getChildren().clear();
            assignComboBox.getItems().addAll(users);
            assignComboBox.setOnAction(e -> {
                // Code to set the assignees of the task
                assignHBox.getChildren().clear();
                assignHBox.getChildren().add(assignLabel);
            });
            assignHBox.getChildren().add(assignComboBox);
        });

        dueHBox.setOnMouseClicked(event -> {
            dueHBox.getChildren().clear();
            dueDatePicker.setOnAction(e -> {
                // Code to set the due date of the task

                task.setDueDate(dueDatePicker.getValue().atStartOfDay());
                task = taskService.update(task);

                dueHBox.getChildren().clear();
                dueHBox.getChildren().add(dueLabel);
            });
            dueHBox.getChildren().add(dueDatePicker);
        });

        projectHBox.setOnMouseClicked(event -> {
            projectHBox.getChildren().clear();

            projectComboBox.getItems().addAll(projects);
            projectComboBox.setOnAction(e -> {
                // Code to set the project of the task
                projectHBox.getChildren().clear();
                projectHBox.getChildren().add(projectLabel);
            });
            projectHBox.getChildren().add(projectComboBox);
        });

    }

    public void init(TaskStatus taskStatus) {

        User currentUser = SessionManager.getInstance().getCurrentUser();

        if (currentUser == null) {
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


        task = taskService.create(task);
    }

    public void initWithTask(Task task) {
        this.task = task;
        nameTextField.setText(task.getName());
        descriptionTextArea.setText(task.getDescription());

        if (task.getDueDate() != null)
            dueLabel.setText(task.getDueDate().toString());

        if (task.getAssignedUsers().size() > 0) {
            String assignees = task.getAssignedUsers().stream().map(User::getUsername).reduce((a, b) -> a + ", " + b).get();
            assignLabel.setText(assignees);
        }

        if (task.getCreatedAt() != null)
            createdAtLabel.setText(task.getCreatedAt().toString());

        if (task.getProject() != null)
            projectLabel.setText(task.getProject().getName());

        statusLabel.setText(task.getStatus().getName());
        statusHBox.setStyle("-fx-background-color: " + task.getStatus().getColor());
    }
}
