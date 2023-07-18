package com.greenshift.greenboard.converters;

import com.greenshift.greenboard.models.entities.Project;

public class ProjectComboBoxConverter extends javafx.util.StringConverter<Project> {
    @Override
    public String toString(Project project) {
        if (project != null)
            return project.getName();
        return "Empty";
    }

    @Override
    public Project fromString(String s) {
        return null;
    }
}
