package com.greenshift.greenboard.models.entities;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private String id;
    private String name;
    private String description;
    private String icon;
    private String color;
    private List<Project> projects = new ArrayList<>();
    private List<User> members = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Task> tasks = new ArrayList<>();
    private User createdBy;
    private String createdById;
    private List<Tag> tags;
    private List<User> pinnedUsers;
    private Organization organization;
    private String organizationId;

    public Team(String name, String description, String icon, String color) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.color = color;

        this.projects = new ArrayList<>();
        this.members = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.pinnedUsers = new ArrayList<>();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<User> getPinnedUsers() {
        return pinnedUsers;
    }

    public void setPinnedUsers(List<User> pinnedUsers) {
        this.pinnedUsers = pinnedUsers;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", color='" + color + '\'' +
                ", projects=" + projects +
                ", members=" + members +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", tasks=" + tasks +
                ", createdBy=" + createdBy +
                ", createdById='" + createdById + '\'' +
                ", tags=" + tags +
                ", pinnedUsers=" + pinnedUsers +
                ", organization=" + organization +
                ", organizationId='" + organizationId + '\'' +
                '}';
    }

    // Constructor

    // Getters and Setters (not generated)
}