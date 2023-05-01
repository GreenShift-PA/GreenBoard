package com.greenboard.components.tasks;

import com.greenboard.models.Task;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class TaskGridPane extends GridPane {
    public TaskGridPane() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10));
    }

    public void addTask(Task task, int column, int row) {
        TaskCard taskCard = new TaskCard(task);
        add(taskCard, column, row);
    }
}