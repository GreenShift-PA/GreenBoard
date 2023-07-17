package com.greenshift.greenboard.models.entities;

import com.greenshift.greenboard.interfaces.IVisitor;

import java.time.LocalDateTime;
import java.util.List;

public class Project extends BaseEntity {
    private String name;
    private String color;
    private String icon;
    private String description;
    private List<Team> teams;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Task> tasks;
    private List<Tag> tags;
    private Organization organization;
    private List<User> pinnedUsers;

    public Project(String id, String name, String color, String description, List<Team> teams, LocalDateTime createdAt, LocalDateTime updatedAt, List<Task> tasks, List<Tag> tags, Organization organization, List<User> pinnedUsers) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.description = description;
        this.teams = teams;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tasks = tasks;
        this.tags = tags;
        this.organization = organization;
        this.pinnedUsers = pinnedUsers;
    }

    public Project(String name, String description, String color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    public Project(String name, String description, String icon, String color) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.color = color;
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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<User> getPinnedUsers() {
        return pinnedUsers;
    }

    public void setPinnedUsers(List<User> pinnedUsers) {
        this.pinnedUsers = pinnedUsers;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", teams=" + teams +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", tasks=" + tasks +
                ", tags=" + tags +
                ", organization=" + organization +
                ", pinnedUsers=" + pinnedUsers +
                '}';
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    // Constructor

    // Getters and Setters (not generated)
}
