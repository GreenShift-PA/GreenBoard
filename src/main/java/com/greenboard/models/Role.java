package com.greenboard.models;

import java.time.LocalDateTime;

public class Role {
    private int id;
    private String roleName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Role(int id, String roleName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.roleName = roleName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Role valueOf(String role) {
        return new Role(0, role, LocalDateTime.now(), LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    // Add validation methods here, such as validateRoleName()

    // Add CRUD methods here, such as save(), findById(), etc.
}
