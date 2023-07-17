package com.greenshift.greenboard.models.entities;

import com.greenshift.greenboard.interfaces.IVisitor;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDateTime;

@XmlRootElement

public class Trash extends BaseEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String metadata;

    public Trash() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    // Constructor

    // Getters and Setters (not generated)
}