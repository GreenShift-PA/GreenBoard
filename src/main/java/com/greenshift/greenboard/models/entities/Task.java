package com.greenshift.greenboard.models.entities;

import java.time.LocalDateTime;
import java.util.List;

public class Task {
    private String id;
    private String name;
    private String description;
    private Project project;
    private String projectId;
    private Team team;
    private String teamId;
    private String status;
    private String priority;
    private String type;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<User> assignedUsers;
    private User author;
    private String authorId;
    private List<Comment> comments;
    private List<Tag> tags;
    private List<TaskAttachment> attachments;
    private List<Task> subTasks;
    private Task parentTask;
    private String parentTaskId;
    private List<User> pinnedUsers;

    // Constructor

    // Getters and Setters (not generated)
}