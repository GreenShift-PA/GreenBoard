package com.greenshift.greenboard.models.entities;

import com.greenshift.greenboard.interfaces.IVisitor;

import java.time.LocalDateTime;

public class Notification extends BaseEntity {
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;
    private String userId;

    public Notification(String id) {
        super(id);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    // Constructor

    // Getters and Setters (not generated)
}