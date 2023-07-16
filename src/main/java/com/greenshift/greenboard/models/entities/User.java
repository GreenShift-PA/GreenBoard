package com.greenshift.greenboard.models.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class User {
    private String id;
    private String firebaseId;
    private LocalDateTime lastLogin;
    private boolean active;
    private boolean confirmed;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String passwordHash;
    private String avatar;
    private String username;
    private Role role;
    private Team lastTeam;
    private String lastTeamId;
    private Project lastProject;
    private String lastProjectId;
    private Organization lastOrganization;
    private String lastOrganizationId;
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
    private List<Activity> activities;

    public User(String email, String firstName, String lastName) {
        this.id = UUID.randomUUID().toString();
        this.active = true;
        this.confirmed = false;
        this.firebaseId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.lastLogin = LocalDateTime.now();

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teams = List.of();
        this.assignedTasks = List.of();
        this.createdTasks = List.of();
        this.createdTeams = List.of();
        this.tokens = List.of();
        this.comments = List.of();
        this.notifications = List.of();
        this.createdOrganizations = List.of();
        this.activities = List.of();
        this.pinnedProjects = List.of();
        this.pinnedTeams = List.of();
        this.pinnedTasks = List.of();
        this.pinnedComments = List.of();
        this.pinnedOrganizations = List.of();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Team getLastTeam() {
        return lastTeam;
    }

    public void setlastTeam(Team lastTeam) {
        this.lastTeam = lastTeam;
    }

    public String getlastTeamId() {
        return lastTeamId;
    }

    public void setlastTeamId(String lastTeamId) {
        this.lastTeamId = lastTeamId;
    }

    public void setLastTeam(Team lastTeam) {
        this.lastTeam = lastTeam;
    }

    public String getLastTeamId() {
        return lastTeamId;
    }

    public void setLastTeamId(String lastTeamId) {
        this.lastTeamId = lastTeamId;
    }

    public Project getLastProject() {
        return lastProject;
    }

    public void setLastProject(Project lastProject) {
        this.lastProject = lastProject;
    }

    public String getLastProjectId() {
        return lastProjectId;
    }

    public void setLastProjectId(String lastProjectId) {
        this.lastProjectId = lastProjectId;
    }

    public Organization getLastOrganization() {
        return lastOrganization;
    }

    public void setLastOrganization(Organization lastOrganization) {
        this.lastOrganization = lastOrganization;
    }

    public String getLastOrganizationId() {
        return lastOrganizationId;
    }

    public void setLastOrganizationId(String lastOrganizationId) {
        this.lastOrganizationId = lastOrganizationId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firebaseId='" + firebaseId + '\'' +
                ", lastLogin=" + lastLogin +
                ", active=" + active +
                ", confirmed=" + confirmed +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", avatar='" + avatar + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", lastTeam=" + lastTeam +
                ", lastTeamId='" + lastTeamId + '\'' +
                ", lastProject=" + lastProject +
                ", lastProjectId='" + lastProjectId + '\'' +
                ", lastOrganization=" + lastOrganization +
                ", lastOrganizationId='" + lastOrganizationId + '\'' +
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
