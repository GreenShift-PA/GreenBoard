package com.greenshift.greenboard.models.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class User {
    private String id;
    private String firebaseId;
    private boolean active;
    private boolean confirmed;
    private String email;
    private String firstName;
    private String lastName;
    private String passwordHash;
    private String avatar;
    private String username;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Team> teams;
    private List<Task> assignedTasks;
    private List<Task> createdTasks;
    private List<Team> createdTeams;
    private List<Token> tokens;
    private List<Comment> comments;
    private List<Comment> mentionedComments;
    private List<Notification> notifications;
    private List<Organization> createdOrganizations;
    private List<Project> pinnedProjects;
    private List<Team> pinnedTeams;
    private List<Task> pinnedTasks;
    private List<Comment> pinnedComments;
    private List<Organization> pinnedOrganizations;

    public User(String email, String firstName, String lastName) {
        this.id = UUID.randomUUID().toString();
        this.active = true;
        this.confirmed = false;
        this.firebaseId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public List<Task> getCreatedTasks() {
        return createdTasks;
    }

    public void setCreatedTasks(List<Task> createdTasks) {
        this.createdTasks = createdTasks;
    }

    public List<Team> getCreatedTeams() {
        return createdTeams;
    }

    public void setCreatedTeams(List<Team> createdTeams) {
        this.createdTeams = createdTeams;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getMentionedComments() {
        return mentionedComments;
    }

    public void setMentionedComments(List<Comment> mentionedComments) {
        this.mentionedComments = mentionedComments;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Organization> getCreatedOrganizations() {
        return createdOrganizations;
    }

    public void setCreatedOrganizations(List<Organization> createdOrganizations) {
        this.createdOrganizations = createdOrganizations;
    }

    public List<Project> getPinnedProjects() {
        return pinnedProjects;
    }

    public void setPinnedProjects(List<Project> pinnedProjects) {
        this.pinnedProjects = pinnedProjects;
    }

    public List<Team> getPinnedTeams() {
        return pinnedTeams;
    }

    public void setPinnedTeams(List<Team> pinnedTeams) {
        this.pinnedTeams = pinnedTeams;
    }

    public List<Task> getPinnedTasks() {
        return pinnedTasks;
    }

    public void setPinnedTasks(List<Task> pinnedTasks) {
        this.pinnedTasks = pinnedTasks;
    }

    public List<Comment> getPinnedComments() {
        return pinnedComments;
    }

    public void setPinnedComments(List<Comment> pinnedComments) {
        this.pinnedComments = pinnedComments;
    }

    public List<Organization> getPinnedOrganizations() {
        return pinnedOrganizations;
    }

    public void setPinnedOrganizations(List<Organization> pinnedOrganizations) {
        this.pinnedOrganizations = pinnedOrganizations;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    private List<Activity> activities;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firebaseId='" + firebaseId + '\'' +
                ", active=" + active +
                ", confirmed=" + confirmed +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", avatar='" + avatar + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", teams=" + teams +
                ", assignedTasks=" + assignedTasks +
                ", createdTasks=" + createdTasks +
                ", createdTeams=" + createdTeams +
                ", tokens=" + tokens +
                ", comments=" + comments +
                ", mentionedComments=" + mentionedComments +
                ", notifications=" + notifications +
                ", createdOrganizations=" + createdOrganizations +
                ", pinnedProjects=" + pinnedProjects +
                ", pinnedTeams=" + pinnedTeams +
                ", pinnedTasks=" + pinnedTasks +
                ", pinnedComments=" + pinnedComments +
                ", pinnedOrganizations=" + pinnedOrganizations +
                ", activities=" + activities +
                '}';
    }
}
