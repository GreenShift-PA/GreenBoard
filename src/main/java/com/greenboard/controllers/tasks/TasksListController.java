package com.greenboard.controllers.tasks;

import com.greenboard.enums.TaskPriority;
import com.greenboard.enums.TaskStatus;
import com.greenboard.factories.TaskCardFactory;
import com.greenboard.models.Task;
import com.greenboard.models.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TasksListController implements Initializable {
    @FXML
    public TableView<Task> tasksTable;
    @FXML
    public TableColumn<Task, String> nameColumn;
    @FXML
    public TableColumn<Task, String> statusColumn;
    @FXML
    public TableColumn<Task, String> usersColumn;
    @FXML
    public TableColumn<Task, String> priorityColumn;

    private StringProperty nameColumnProperty = new SimpleStringProperty("");
    private StringProperty statusColumnProperty = new SimpleStringProperty("");
    private StringProperty usersColumnProperty = new SimpleStringProperty("");
    private StringProperty priorityColumnProperty = new SimpleStringProperty("");

    public void setTasks(List<Task> tasks) {
        tasksTable.setItems((ObservableList<Task>) tasks);
    }

    public void setNameColumnProperty(String nameColumnProperty) {
        this.nameColumnProperty.set(nameColumnProperty);
    }

    public void setStatusColumnProperty(String statusColumnProperty) {
        this.statusColumnProperty.set(statusColumnProperty);
    }

    public void setUsersColumnProperty(String usersColumnProperty) {
        this.usersColumnProperty.set(usersColumnProperty);
    }

    public void setPriorityColumnProperty(String priorityColumnProperty) {
        this.priorityColumnProperty.set(priorityColumnProperty);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(cellData -> nameColumnProperty);
        statusColumn.setCellValueFactory(cellData -> statusColumnProperty);
        usersColumn.setCellValueFactory(cellData -> usersColumnProperty);
        priorityColumn.setCellValueFactory(cellData -> priorityColumnProperty);

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
            TaskStatus randomStatus = TaskStatus.values()[(int) (Math.random() * TaskStatus.values().length)];
            TaskPriority randomPriority = TaskPriority.values()[(int) (Math.random() * TaskPriority.values().length)];

            Task task = new Task(
                    i,
                    "Task " + i,
                    "Description for task " + i,
                    List.of(user1, user2, user3),
                    randomStatus,
                    randomPriority,
                    LocalDate.now(),
                    LocalDate.now().plusDays(5)
            );

            tasks.add(task);
        }

        // create data for the table
        ObservableList<Task> tasksObservableList = FXCollections.observableArrayList(tasks);

        // set data to the table
        tasksTable.setItems(tasksObservableList);
    }
}
