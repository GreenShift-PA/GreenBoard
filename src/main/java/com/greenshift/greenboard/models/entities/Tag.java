package com.greenshift.greenboard.models.entities;

import java.time.LocalDateTime;
import java.util.List;

public class Tag {
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Task> tasks;
    private List<Team> teams;
    private List<Project> projects;
    private List<Organization> organizations;

    // Constructor

    // Getters and Setters (not generated)
}
