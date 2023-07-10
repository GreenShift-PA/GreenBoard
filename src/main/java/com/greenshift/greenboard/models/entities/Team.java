package com.greenshift.greenboard.models.entities;


import java.time.LocalDateTime;
import java.util.List;

public class Team {
    private String id;
    private String name;
    private String description;
    private List<Project> projects;
    private List<User> members;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Task> tasks;
    private User createdBy;
    private String createdById;
    private List<Tag> tags;
    private List<User> pinnedUsers;
    private Organization organization;
    private String organizationId;

    // Constructor

    // Getters and Setters (not generated)
}