package com.greenboard.models;

import com.greenboard.enums.TaskPriority;
import com.greenboard.enums.TaskStatus;
import javafx.beans.value.ObservableValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Task {
    private String id;
    private String name;
    private String description;
    private List<User> users = new ArrayList<User>();
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDate created_date;
    private LocalDate due_date;

    public Task()
    {
        this.id = "";
        this.name = "New Task";
        this.description = "";
        this.users = new ArrayList<User>();
        this.status = TaskStatus.TODO;
        this.priority = TaskPriority.LOW;
        this.created_date = LocalDate.now();
        this.due_date = LocalDate.now();
    }

    public Task(String id, String name, String description, List<User> users, TaskStatus status, TaskPriority priority, LocalDate created_date, LocalDate due_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
        this.status = status;
        this.priority = priority;
        this.created_date = created_date;
        this.due_date = due_date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public void setDueDate(LocalDate parse) {
        this.due_date = parse;
    }

    public void setDueDate(ObservableValue<LocalDate> value) {
        this.due_date = value.getValue();
    }

    public LocalDate getDueDate() {
        return this.due_date;
    }

    public boolean assignUser(User user) {
        if (this.users.contains(user)) {
            return false;
        } else {
            this.users.add(user);
            return true;
        }
    }

    public boolean unassignUser(User user) {
        if (this.users.contains(user)) {
            this.users.remove(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean isAssigned(User user) {
        return this.users.contains(user);
    }

    public boolean isAssigned(String userId) {
        for (User user : this.users) {
            if (user.getUserId().toString().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                ", status=" + status +
                ", priority=" + priority +
                ", created_date=" + created_date +
                ", due_date=" + due_date +
                '}';
    }

    public LocalDate getCreatedDate() {
        return this.created_date;
    }
}
