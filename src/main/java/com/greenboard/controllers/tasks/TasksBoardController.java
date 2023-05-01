package com.greenboard.controllers.tasks;

import com.greenboard.enums.TaskStatus;
import com.greenboard.factories.TaskCellFactory;
import com.greenboard.models.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TasksBoardController {
    @FXML
    private ListView<Task> todoListView;
    @FXML
    private ListView<Task> inProgressListView;
    @FXML
    private ListView<Task> doneListView;

    public void initialize() {
        // create some sample tasks
        Task task1 = new Task(1, "Task 1", "Description 1", 1, TaskStatus.TODO, LocalDate.now(), LocalDate.now().plusDays(1));
        Task task2 = new Task(2, "Task 2", "Description 2", 2, TaskStatus.IN_PROGRESS, LocalDate.now().minusDays(1), LocalDate.now().plusDays(2));
        Task task3 = new Task(3, "Task 3", "Description 3", 3, TaskStatus.DONE, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));
        Task task4 = new Task(4, "Task 4", "Description 4", 4, TaskStatus.TODO, LocalDate.now(), LocalDate.now().plusDays(2));
        Task task5 = new Task(5, "Task 5", "Description 5", 5, TaskStatus.IN_PROGRESS, LocalDate.now().plusDays(1), LocalDate.now().plusDays(4));
        Task task6 = new Task(6, "Task 6", "Description 6", 6, TaskStatus.DONE, LocalDate.now().minusDays(1), LocalDate.now().plusDays(5));
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        tasks.add(task5);
        tasks.add(task6);

        // group tasks by status
        var tasksByStatus = tasks.stream().collect(Collectors.groupingBy(Task::getStatus));

        // set the cell factories for each ListView
        todoListView.setCellFactory(new TaskCellFactory());
        inProgressListView.setCellFactory(new TaskCellFactory());
        doneListView.setCellFactory(new TaskCellFactory());

        // populate each ListView with the appropriate tasks
        todoListView.setItems(FXCollections.observableList(tasksByStatus.get(TaskStatus.TODO)));
        inProgressListView.setItems(FXCollections.observableList(tasksByStatus.get(TaskStatus.IN_PROGRESS)));
        doneListView.setItems(FXCollections.observableList(tasksByStatus.get(TaskStatus.DONE)));

        // set selection models to allow multiple selections
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        inProgressListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        doneListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}