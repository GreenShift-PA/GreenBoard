package com.greenshift.greenboard.models.entities;

import java.time.LocalDateTime;
import java.util.List;

public class Project {
    private String id;
    private String name;
    private String description;
    private List<Team> teams;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Task> tasks;
    private List<Tag> tags;
    private Organization organization;
    private List<User> pinnedUsers;

    // Constructor

    // Getters and Setters (not generated)
}
