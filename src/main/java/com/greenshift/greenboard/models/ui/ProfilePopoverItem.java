package com.greenshift.greenboard.models.ui;

import com.greenshift.greenboard.models.entities.Organization;
import javafx.beans.property.ReadOnlyObjectWrapper;

import java.util.Objects;

public class ProfilePopoverItem extends ReadOnlyObjectWrapper<ProfilePopoverItem> {
    private String id;
    private Integer order;
    private String icon;
    private String name;
    private String info;
    private Organization organization;
    private String color;

    public ProfilePopoverItem(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public ProfilePopoverItem(Organization organization) {
        if(organization == null) {
            return;
        }
        this.id = organization.getId();
        this.icon = organization.getIcon();
        this.name = organization.getName();
        this.organization = organization;
        this.color = organization.getColor();
        Integer members = organization.getTeams().stream().reduce(0, (subtotal, team) -> subtotal + team.getMembers().size(), Integer::sum);
        this.info = String.format("%s - %d Teams", organization.getType(), organization.getTeams().size());
    }

    public ProfilePopoverItem(String icon, String name, String info) {
        this.icon = icon;
        this.name = name;
        this.info = info;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfilePopoverItem that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "ProfilePopoverItem{" +
                "id='" + id + '\'' +
                ", order=" + order +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
