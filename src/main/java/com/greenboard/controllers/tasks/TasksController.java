package com.greenboard.controllers.tasks;

import com.greenboard.enums.TaskStatus;
import com.greenboard.factories.TaskCellFactory;
import com.greenboard.models.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class TasksController {

    public void initialize() {

    }

    public void onAddNewTask(ActionEvent actionEvent) throws IOException {
        // create new task and open new window, task-viewer.fxml

        Task task = new Task();

        // open a modal with the task details
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/greenboard/task-viewer.fxml"));
        Parent root = fxmlLoader.load();


        Stage stage = new Stage();
        stage.setTitle("Task Details");
        stage.setScene(new Scene(root));

        TaskViewerController controller = fxmlLoader.getController();
        controller.setName(task.getName());
        controller.setDescription(task.getDescription());
        controller.setStatus(task.getStatus().toString());
        controller.setPriority(task.getPriority().toString());
        controller.setEditMode(false);

        stage.show();
    }
}