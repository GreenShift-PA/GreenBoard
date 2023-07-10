package com.greenshift.greenboard.builders;

import com.greenshift.greenboard.models.ui.CustomContextMenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CustomContextMenuBuilder {
    private String id = null;
    private String label = "Item";
    private String leftIcon = null;
    private String rightIcon = null;
    private boolean active = true;
    private String category = null;
    private Function<CustomContextMenuItem, Void> action = null;

    private boolean separator = false;


    private List<CustomContextMenuItem> subMenuItems = new ArrayList<>();

    public CustomContextMenuBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public CustomContextMenuBuilder setLabel(String label) {
        this.label = label;
        return this;
    }

    public CustomContextMenuBuilder setLeftIcon(String leftIcon) {
        this.leftIcon = leftIcon;
        return this;
    }

    public CustomContextMenuBuilder setRightIcon(String rightIcon) {
        this.rightIcon = rightIcon;
        return this;
    }

    public CustomContextMenuBuilder setActive(boolean active) {
        this.active = active;
        return this;
    }

    public CustomContextMenuBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public CustomContextMenuBuilder setAction(Function<CustomContextMenuItem, Void> action) {
        this.action = action;
        return this;
    }

    public CustomContextMenuBuilder setSeparator(boolean separator) {
        this.separator = separator;
        return this;
    }

    public CustomContextMenuBuilder addSubMenuItem(CustomContextMenuItem subMenuItem) {
        this.subMenuItems.add(subMenuItem);
        return this;
    }


    public List<CustomContextMenuItem> getSubMenuItems() {
        return subMenuItems;
    }

    public void setSubMenuItems(List<CustomContextMenuItem> subMenuItems) {
        this.subMenuItems = subMenuItems;
    }

    public CustomContextMenuItem build() {
        return new CustomContextMenuItem(id, label, leftIcon, rightIcon, active, category, action, subMenuItems, separator);
    }
}
