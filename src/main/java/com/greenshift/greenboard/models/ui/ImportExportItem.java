package com.greenshift.greenboard.models.ui;

import com.greenshift.greenboard.interfaces.IDumper;

public class ImportExportItem {
    private String name;
    private String description;
    private String icon;
    private String color;

    private IDumper dumper;

    public ImportExportItem(String name, String description, String icon, String color, IDumper dumper) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.color = color;
        this.dumper = dumper;
    }

    public ImportExportItem(String name, String description, String icon, String color) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.color = color;
        this.dumper = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public IDumper getDumper() {
        return dumper;
    }

    public void setDumper(IDumper dumper) {
        this.dumper = dumper;
    }

    @Override
    public String toString() {
        return "ImportExportItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", color='" + color + '\'' +
                ", dumper=" + dumper +
                '}';
    }
}
