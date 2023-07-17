package com.greenshift.greenboard.models.entities;

import com.greenshift.greenboard.interfaces.IVisitor;

import java.time.LocalDateTime;
import java.util.List;

public class Task extends BaseEntity {
    private String name;
    private String description;
    private String icon;
    private String color;
    private Project project;
    private String projectId;
    private Team team;
    private String teamId;
    private TaskStatus status;
    private String priority;
    private String type;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<User> assignedUsers;
    private User author;
    private String authorId;
    private List<Comment> comments;
    private List<Tag> tags;
    private List<TaskAttachment> attachments;
    private List<Task> subTasks;
    private Task parentTask;
    private String parentTaskId;
    private List<User> pinnedUsers;

    public Task(String id, String name, String description, String icon, String color, Project project, String projectId, Team team, String teamId, TaskStatus status, String priority, String type, LocalDateTime dueDate, LocalDateTime createdAt, LocalDateTime updatedAt, List<User> assignedUsers, User author, String authorId, List<Comment> comments, List<Tag> tags, List<TaskAttachment> attachments, List<Task> subTasks, Task parentTask, String parentTaskId, List<User> pinnedUsers) {
        super(id);
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.color = color;
        this.project = project;
        this.projectId = projectId;
        this.team = team;
        this.teamId = teamId;
        this.status = status;
        this.priority = priority;
        this.type = type;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.assignedUsers = assignedUsers;
        this.author = author;
        this.authorId = authorId;
        this.comments = comments;
        this.tags = tags;
        this.attachments = attachments;
        this.subTasks = subTasks;
        this.parentTask = parentTask;
        this.parentTaskId = parentTaskId;
        this.pinnedUsers = pinnedUsers;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
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

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<TaskAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<TaskAttachment> attachments) {
        this.attachments = attachments;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public List<User> getPinnedUsers() {
        return pinnedUsers;
    }

    public void setPinnedUsers(List<User> pinnedUsers) {
        this.pinnedUsers = pinnedUsers;
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
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", color='" + color + '\'' +
                ", project=" + project +
                ", projectId='" + projectId + '\'' +
                ", team=" + team +
                ", teamId='" + teamId + '\'' +
                ", status=" + status +
                ", priority='" + priority + '\'' +
                ", type='" + type + '\'' +
                ", dueDate=" + dueDate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", assignedUsers=" + assignedUsers +
                ", author=" + author +
                ", authorId='" + authorId + '\'' +
                ", comments=" + comments +
                ", tags=" + tags +
                ", attachments=" + attachments +
                ", subTasks=" + subTasks +
                ", parentTask=" + parentTask +
                ", parentTaskId='" + parentTaskId + '\'' +
                ", pinnedUsers=" + pinnedUsers +
                '}';
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    // taskBuilder
    public static class TaskBuilder {
        private String id;
        private String name;
        private String description;
        private String icon;
        private String color;
        private Project project;
        private String projectId;
        private Team team;
        private String teamId;
        private TaskStatus status;
        private String priority;
        private String type;
        private LocalDateTime dueDate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<User> assignedUsers;
        private User author;
        private String authorId;
        private List<Comment> comments;
        private List<Tag> tags;
        private List<TaskAttachment> attachments;
        private List<Task> subTasks;
        private Task parentTask;
        private String parentTaskId;
        private List<User> pinnedUsers;

        public TaskBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public TaskBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TaskBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public TaskBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public TaskBuilder setProject(Project project) {
            this.project = project;
            return this;
        }

        public TaskBuilder setProjectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public TaskBuilder setTeam(Team team) {
            this.team = team;
            return this;
        }

        public TaskBuilder setTeamId(String teamId) {
            this.teamId = teamId;
            return this;
        }

        public TaskBuilder setStatus(TaskStatus status) {
            this.status = status;
            return this;
        }

        public TaskBuilder setPriority(String priority) {
            this.priority = priority;
            return this;
        }

        public TaskBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public TaskBuilder setDueDate(LocalDateTime dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public TaskBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TaskBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public TaskBuilder setAssignedUsers(List<User> assignedUsers) {
            this.assignedUsers = assignedUsers;
            return this;
        }

        public TaskBuilder setAuthor(User author) {
            this.author = author;
            return this;
        }

        public TaskBuilder setAuthorId(String authorId) {
            this.authorId = authorId;
            return this;
        }

        public TaskBuilder setComments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }

        public TaskBuilder setTags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public TaskBuilder setAttachments(List<TaskAttachment> attachments) {
            this.attachments = attachments;
            return this;
        }

        public TaskBuilder setSubTasks(List<Task> subTasks) {
            this.subTasks = subTasks;
            return this;
        }

        public TaskBuilder setParentTask(Task parentTask) {
            this.parentTask = parentTask;
            return this;
        }

        public TaskBuilder setParentTaskId(String parentTaskId) {
            this.parentTaskId = parentTaskId;
            return this;
        }

        public TaskBuilder setPinnedUsers(List<User> pinnedUsers) {
            this.pinnedUsers = pinnedUsers;
            return this;
        }

        public Task build() {
            return new Task(id, name, description, icon, color, project, projectId, team, teamId, status, priority, type, dueDate, createdAt, updatedAt, assignedUsers, author, authorId, comments, tags, attachments, subTasks, parentTask, parentTaskId, pinnedUsers);
        }

    }

}