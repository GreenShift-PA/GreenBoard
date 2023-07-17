package com.greenshift.greenboard.models.entities;

import com.greenshift.greenboard.interfaces.IVisitor;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDateTime;

@XmlRootElement
public class Activity extends BaseEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String metadata;
    private String type;
    private User user;
    private String userId;

    public Activity(String id) {
        super(id);
    }

    public Activity() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    // Constructor

    // Getters and Setters (not generated)
}
