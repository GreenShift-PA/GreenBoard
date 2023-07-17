package com.greenshift.greenboard.models.entities;

import com.greenshift.greenboard.interfaces.IVisitor;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class Role extends BaseEntity {
    private String name;
    private String description;
    private List<User> users;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                '}';
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    // Constructor

    // Getters and Setters (not generated)
}
