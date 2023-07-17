package com.greenshift.greenboard.models.entities;

import com.greenshift.greenboard.interfaces.IVisitor;

import java.time.LocalDateTime;

public class Trash extends BaseEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String metadata;

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    // Constructor

    // Getters and Setters (not generated)
}