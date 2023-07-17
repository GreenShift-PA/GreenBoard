package com.greenshift.greenboard.models.entities;

import com.greenshift.greenboard.interfaces.IVisitor;

import java.time.LocalDateTime;

public class TaskAttachment extends BaseEntity {
    private String name;
    private String url;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String metadata;
    private Task task;
    private String taskId;

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    // Constructor

    // Getters and Setters (not generated)
}
