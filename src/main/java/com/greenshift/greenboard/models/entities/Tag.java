package com.greenshift.greenboard.models.entities;

import com.greenshift.greenboard.interfaces.IVisitor;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDateTime;
import java.util.List;

@XmlRootElement
public class Tag extends BaseEntity {
    private String name;
    private String color;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Task> tasks;
    private List<Team> teams;
    private List<Project> projects;
    private List<Organization> organizations;

    public Tag() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", tasks=" + tasks +
                ", teams=" + teams +
                ", projects=" + projects +
                ", organizations=" + organizations +
                '}';
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    // Constructor

    // Getters and Setters (not generated)
}
