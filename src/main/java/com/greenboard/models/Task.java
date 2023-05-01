package com.greenboard.models;

import com.greenboard.enums.TaskStatus;

import java.time.LocalDate;

public class Task {
    private int id;
    private String name;
    private String description;
    private int user_id;
    private TaskStatus status;
    private LocalDate created_date;
    private LocalDate due_date;

    public Task(int id, String name, String description, int user_id, TaskStatus status, LocalDate created_date, LocalDate due_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user_id = user_id;
        this.status = status;
        this.created_date = created_date;
        this.due_date = due_date;
    }

    public int getId() {
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getUserAvatar() {
        return "https://avatars.dicebear.com/api/avataaars/" + this.user_id + ".svg";
    }
}
