package com.greenshift.greenboard.models.entities;

import java.time.LocalDateTime;
import java.util.List;

public class Organization {
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Project> projects;
    private List<Team> teams;
    private List<Tag> tags;
    private User createdBy;
    private String createdById;
    private String metadata;
    private List<User> pinnedUsers;

    // Constructor

    // Getters and Setters (not generated)
}
