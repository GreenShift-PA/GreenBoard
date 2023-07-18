package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.converters.ProjectComboBoxConverter;
import com.greenshift.greenboard.converters.UserCheckComboBoxConverter;
import com.greenshift.greenboard.models.entities.*;
import com.greenshift.greenboard.models.entities.Task.TaskBuilder;
import com.greenshift.greenboard.services.TaskService;
import com.greenshift.greenboard.services.UserService;
import com.greenshift.greenboard.singletons.SessionManager;
import javafx.collections.ListChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.controlsfx.control.CheckComboBox;

import java.util.ArrayList;
import java.util.Arrays;
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

    public CheckComboBox<User> assignCheckComboBox = new CheckComboBox<>();
    public DatePicker dueDatePicker = new DatePicker();
    public ComboBox<Project> projectComboBox = new ComboBox<>();
    TaskService taskService = new TaskService();
    List<Project> projects = new ArrayList<>();
    List<User> users = new ArrayList<>();
    private Task task;

    public void initialize() {
        UserService userService = new UserService();
        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser == null)
            return;

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
            users = Arrays.stream(userService.getAll()).toList();
            List<String> assignedUserIds = task.getAssignedUsers().stream().map(User::getId).toList();

            assignHBox.getChildren().clear();
            assignCheckComboBox.getItems().clear();
            assignCheckComboBox.setConverter(new UserCheckComboBoxConverter());
            assignCheckComboBox.getItems().addAll(users);


            // pre-check the users that are already assigned to the task
            for (User user : users) {
                if (assignedUserIds.contains(user.getId())) {
                    assignCheckComboBox.getCheckModel().check(user);
                }
            }

            assignCheckComboBox.getCheckModel().getCheckedItems().addListener((ListChangeListener<User>) c -> {
                List<User> checkedUsers = assignCheckComboBox.getCheckModel().getCheckedItems();
                task.setAssignedUsers(checkedUsers);
                task = taskService.update(task);

                // Code to set the assignees of the task
                assignHBox.getChildren().clear();
                assignLabel.setText(formatAssignees(checkedUsers));
                assignHBox.getChildren().add(assignLabel);
            });
            assignHBox.getChildren().add(assignCheckComboBox);
        });

        dueHBox.setOnMouseClicked(event -> {
            dueHBox.getChildren().clear();
            dueDatePicker.setOnAction(e -> {
                // Code to set the due date of the task

                task.setDueDate(dueDatePicker.getValue().atStartOfDay());
                task = taskService.update(task);

                dueHBox.getChildren().clear();
                dueLabel.setText(dueDatePicker.getValue().toString());
                dueHBox.getChildren().add(dueLabel);
            });
            dueHBox.getChildren().add(dueDatePicker);
        });

        projectHBox.setOnMouseClicked(event -> {
            projectHBox.getChildren().clear();
            if(currentUser.getLastOrganization() != null) {
                projects = currentUser.getLastOrganization().getProjects();
            } else {
                projects = currentUser.getTeams().stream().map(Team::getProjects).flatMap(List::stream).toList();
            }


            projectComboBox.getItems().addAll(projects);
            projectComboBox.setConverter(new ProjectComboBoxConverter());
            projectComboBox.setOnAction(e -> {
                task.setProject(projectComboBox.getValue());
                task = taskService.update(task);

                projectHBox.getChildren().clear();
                projectLabel.setText(projectComboBox.getValue().getName());
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
            assignLabel.setText(formatAssignees(task.getAssignedUsers()));
        }

        if (task.getCreatedAt() != null)
            createdAtLabel.setText(task.getCreatedAt().toString());

        if (task.getProject() != null)
            projectLabel.setText(task.getProject().getName());

        statusLabel.setText(task.getStatus().getName());
        statusHBox.setStyle("-fx-background-color: " + task.getStatus().getColor());
    }

    public String formatAssignees(List<User> users) {
        if (users.size() == 0)
            return "Empty";
        else if (users.size() == 1)
            return shortUsername(users.get(0).getUsername());
        else if (users.size() == 2)
            return shortUsername(users.get(0).getUsername()) + ", " + shortUsername(users.get(1).getUsername());
        else
            return  shortUsername(users.get(0).getUsername()) + ", " + shortUsername(users.get(1).getUsername()) + ", +" + (users.size() - 2);
    }

    public String shortUsername(String username) {
        // if name is longer than 10 characters, return the first 10 characters and ellipsis
        if (username.length() > 10)
            return username.substring(0, 10) + "...";
        else
            return username;
    }
}
