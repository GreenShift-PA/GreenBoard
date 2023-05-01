package com.greenboard.components.tasks;

import com.greenboard.models.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class TaskCard extends BorderPane {
    private final Task task;
    private final Label taskNameLabel;

    public TaskCard(Task task) {
        this.task = task;
        this.taskNameLabel = new Label(task.getName());
        setTop(taskNameLabel);
        setCenter(new Label(task.getDescription()));
    }

    public Task getTask() {
        return task;
    }

    public Label getTaskNameLabel() {
        return taskNameLabel;
    }
}

