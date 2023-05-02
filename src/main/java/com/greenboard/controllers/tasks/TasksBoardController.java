package com.greenboard.controllers.tasks;

import com.greenboard.enums.TaskPriority;
import com.greenboard.enums.TaskStatus;
import com.greenboard.factories.TaskCardFactory;
import com.greenboard.factories.TaskCellFactory;
import com.greenboard.models.Task;
import com.greenboard.models.User;
import com.greenboard.singletons.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TasksBoardController implements Initializable {
    @FXML
    private ListView<Task> todoListView;

    @FXML
    private ListView<Task> inProgressListView;

    @FXML
    private ListView<Task> doneListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Task> tasks = Application.getInstance().getTasks();

        // group tasks by status
        Map<TaskStatus, List<Task>> tasksByStatus = tasks.stream().collect(Collectors.groupingBy(Task::getStatus));


        // set the cell factories for each ListView
        todoListView.setCellFactory(listView -> new TaskCardFactory());
        inProgressListView.setCellFactory(listView -> new TaskCardFactory());
        doneListView.setCellFactory(listView -> new TaskCardFactory());

        if (tasksByStatus.get(TaskStatus.TODO) == null) {
            todoListView.setItems(FXCollections.observableArrayList());
        } else {
            todoListView.setItems(FXCollections.observableArrayList(tasksByStatus.get(TaskStatus.TODO)));
        }

        if (tasksByStatus.get(TaskStatus.IN_PROGRESS) == null) {
            inProgressListView.setItems(FXCollections.observableArrayList());
        } else {
            inProgressListView.setItems(FXCollections.observableArrayList(tasksByStatus.get(TaskStatus.IN_PROGRESS)));
        }

        if (tasksByStatus.get(TaskStatus.DONE) == null) {
            doneListView.setItems(FXCollections.observableArrayList());
        } else {
            doneListView.setItems(FXCollections.observableArrayList(tasksByStatus.get(TaskStatus.DONE)));
        }
    }
}
