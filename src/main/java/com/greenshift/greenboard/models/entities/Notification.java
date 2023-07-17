package com.greenshift.greenboard.models.entities;

import com.greenshift.greenboard.interfaces.IVisitor;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDateTime;

@XmlRootElement
public class Notification extends BaseEntity {
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;
    private String userId;

    public Notification(String id) {
        super(id);
    }

    public Notification() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    // Constructor

    // Getters and Setters (not generated)
}