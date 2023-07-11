package com.greenshift.greenboard.models.ui;

import com.greenshift.greenboard.models.entities.Project;
import com.greenshift.greenboard.models.entities.User;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class KanbanItem extends ReadOnlyObjectWrapper<KanbanItem> {
    private String id;
    private Integer order;
    private String title;
    private String icon;
    private LocalDateTime dueDate;
    private Project project;
    private List<User> users;
    private KanbanGroup group;

    public KanbanItem(String title, String icon, LocalDateTime dueDate, Project project) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.icon = icon;
        this.dueDate = dueDate;
        this.project = project;
        this.users = new ArrayList<>();
    }

    public KanbanItem(String title, String icon, LocalDateTime dueDate, Project project, List<User> users) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.icon = icon;
        this.dueDate = dueDate;
        this.project = project;
        this.users = users;
    }

    public KanbanItem(String title, String icon, List<User> users) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.icon = icon;
        this.users = users;
    }

    public KanbanItem(String title, String icon) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.icon = icon;
        this.users = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public KanbanGroup getGroup() {
        return group;
    }

    public void setGroup(KanbanGroup group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KanbanItem)) return false;
        KanbanItem that = (KanbanItem) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "KanbanItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", dueDate=" + dueDate +
                ", project=" + project +
                ", users=" + users +
                '}';
    }

    @Override
    public KanbanItem getValue() {
        return this;
    }

    @Override
    public ReadOnlyObjectProperty<KanbanItem> getReadOnlyProperty() {
        return this;
    }
}
