package com.greenshift.greenboard.models.entities;

import com.greenshift.greenboard.interfaces.IVisitor;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDateTime;

@XmlRootElement

public class Token extends BaseEntity {
    private String token;
    private String context;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;
    private String userId;

    public Token() {
    }

    @Override
    public void accept(IVisitor visitor) {
       visitor.visit(this);
    }

    // Constructor

    // Getters and Setters (not generated)
}