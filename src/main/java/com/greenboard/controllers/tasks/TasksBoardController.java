package com.greenboard.controllers.tasks;

import com.greenboard.enums.TaskStatus;
import com.greenboard.factories.TaskCardFactory;
import com.greenboard.factories.TaskCellFactory;
import com.greenboard.models.Task;
import com.greenboard.models.User;
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

        User user1 = new User();
        user1.setUsername("user1");

        User user2 = new User();
        user2.setUsername("user2");

        User user3 = new User();
        user3.setUsername("user3");

        // create some sample tasks
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            /*Task(int id, String name, String description, List<User> users, TaskStatus status, LocalDate created_date, LocalDate due_date)*/
            Task task = new Task(
                    i,
                    "Task " + i,
                    "Description for task " + i,
                    List.of(user1, user2, user3),
                    TaskStatus.TODO,
                    LocalDate.now(),
                    LocalDate.now().plusDays(5)
            );

            tasks.add(task);
        }

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
