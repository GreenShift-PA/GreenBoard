package com.greenshift.greenboard.models.ui;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class CustomContextMenuItem {
    private String id;
    private String label;
    private String leftIcon;
    private String rightIcon;
    private boolean active = true;
    private String category = null;
    private Function<CustomContextMenuItem, Void> action;
    private List<CustomContextMenuItem> subMenuItems;
    private boolean separator = false;


    public CustomContextMenuItem(String id, String label, String leftIcon, String rightIcon, Function<CustomContextMenuItem, Void> action, List<CustomContextMenuItem> subMenuItems) {
        this.id = id;
        this.label = label;
        this.leftIcon = leftIcon;
        this.rightIcon = rightIcon;
        this.action = action;
        this.subMenuItems = subMenuItems;
    }

    public CustomContextMenuItem(String label, String leftIcon, String rightIcon, Function<CustomContextMenuItem, Void> action, List<CustomContextMenuItem> subMenuItems) {
        this.id = this.label = label;
        this.leftIcon = leftIcon;
        this.rightIcon = rightIcon;
        this.action = action;
        this.subMenuItems = subMenuItems;
    }

    public CustomContextMenuItem(String id, String label, String leftIcon, String rightIcon, boolean active, String category, Function<CustomContextMenuItem, Void> action, List<CustomContextMenuItem> subMenuItems, boolean separator) {
        this.id = id;
        this.label = label;
        this.leftIcon = leftIcon;
        this.rightIcon = rightIcon;
        this.active = active;
        this.category = category;
        this.action = action;
        this.subMenuItems = subMenuItems;
        this.separator = separator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLeftIcon() {
        return leftIcon;
    }

    public void setLeftIcon(String leftIcon) {
        this.leftIcon = leftIcon;
    }

    public String getRightIcon() {
        return rightIcon;
    }

    public void setRightIcon(String rightIcon) {
        this.rightIcon = rightIcon;
    }

    public Function<CustomContextMenuItem, Void> getAction() {
        return action;
    }

    public void setAction(Function<CustomContextMenuItem, Void> action) {
        this.action = action;
    }

    public List<CustomContextMenuItem> getSubMenuItems() {
        return subMenuItems;
    }

    public void setSubMenuItems(List<CustomContextMenuItem> subMenuItems) {
        this.subMenuItems = subMenuItems;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean hasSeparator() {
        return separator;
    }

    public void setSeparator(boolean separator) {
        this.separator = separator;
    }


    @Override
    public String toString() {
        return "ContextualMenuItem{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", leftIcon='" + leftIcon + '\'' +
                ", rightIcon='" + rightIcon + '\'' +
                ", action=" + action +
                ", subMenuItems=" + subMenuItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomContextMenuItem that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
