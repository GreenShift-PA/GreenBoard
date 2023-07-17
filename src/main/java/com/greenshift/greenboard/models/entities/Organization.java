package com.greenshift.greenboard.models.entities;

import com.google.gson.JsonObject;
import com.greenshift.greenboard.interfaces.IVisitor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Organization extends BaseEntity {
    private String name;
    private String description;
    private String icon;
    private String plan;
    private String type;
    private String color;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Project> projects;
    private List<Team> teams;
    private List<Tag> tags;
    private User createdBy;
    private String createdById;
    private JsonObject metadata;
    private List<User> pinnedUsers;

    public Organization(String name, String description, String icon, String color) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.color = color;
        this.teams = new ArrayList<>();
    }

    public Organization() {
        this.teams = new ArrayList<>();
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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

    public JsonObject getMetadata() {
        return metadata;
    }

    public void setMetadata(JsonObject metadata) {
        this.metadata = metadata;
    }

    public List<User> getPinnedUsers() {
        return pinnedUsers;
    }

    public void setPinnedUsers(List<User> pinnedUsers) {
        this.pinnedUsers = pinnedUsers;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", plan='" + plan + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", projects=" + projects +
                ", teams=" + teams +
                ", tags=" + tags +
                ", createdBy=" + createdBy +
                ", createdById='" + createdById + '\'' +
                ", metadata='" + metadata + '\'' +
                ", pinnedUsers=" + pinnedUsers +
                '}';
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

}
