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

    // Constructor

    // Getters and Setters (not generated)
}
