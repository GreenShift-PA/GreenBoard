package com.greenshift.greenboard.models.ui;

import java.util.List;
import java.util.UUID;

public class KanbanGroup {
    private String id;
    private String title;
    private String icon;
    private String color;
    private List<KanbanItem> items;

    public KanbanGroup(String title, String icon) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.icon = icon;
    }

    public KanbanGroup(String id, String title, String icon) {
        this.id = id;
        this.title = title;
        this.icon = icon;
    }

    public KanbanGroup(String id, String title, String icon, List<KanbanItem> items) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.items = items;
    }

    public KanbanGroup(String id, String title, String icon, String color, List<KanbanItem> items) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.color = color;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<KanbanItem> getItems() {
        return items;
    }

    public void setItems(List<KanbanItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "KanbanGroup{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", color='" + color + '\'' +
                ", items=" + items +
                '}';
    }
}
