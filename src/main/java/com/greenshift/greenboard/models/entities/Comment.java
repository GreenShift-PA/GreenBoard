package com.greenshift.greenboard.models.entities;

import java.time.LocalDateTime;
import java.util.List;

public class Comment {
    private String id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User author;
    private String authorId;
    private Task task;
    private String taskId;
    private List<User> mentions;
    private Comment answerTo;
    private String answerToId;
    private List<Comment> answers;
    private List<User> pinnedUsers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public Comment getAnswerTo() {
        return answerTo;
    }

    public void setAnswerTo(Comment answerTo) {
        this.answerTo = answerTo;
    }

    public String getAnswerToId() {
        return answerToId;
    }

    public void setAnswerToId(String answerToId) {
        this.answerToId = answerToId;
    }

    public List<Comment> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Comment> answers) {
        this.answers = answers;
    }

    public List<User> getPinnedUsers() {
        return pinnedUsers;
    }

    public void setPinnedUsers(List<User> pinnedUsers) {
        this.pinnedUsers = pinnedUsers;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", author=" + author +
                ", authorId='" + authorId + '\'' +
                ", task=" + task +
                ", taskId='" + taskId + '\'' +
                ", mentions=" + mentions +
                ", answerTo=" + answerTo +
                ", answerToId='" + answerToId + '\'' +
                ", answers=" + answers +
                ", pinnedUsers=" + pinnedUsers +
                '}';
    }

    // Constructor

    // Getters and Setters (not generated)
}
