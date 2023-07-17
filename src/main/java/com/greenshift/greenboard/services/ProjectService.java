package com.greenshift.greenboard.services;

import com.google.gson.reflect.TypeToken;
import com.greenshift.greenboard.models.entities.Project;
import com.greenshift.greenboard.models.entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectService extends BaseCrudService<Project> {

    public ProjectService() {
        super("http://localhost:3000/api/v1/projects");
    }

    public static void main(String[] args) {
        ProjectService projectService = new ProjectService();
        UserService userService = new UserService();
        String projectId = "9e908fcf-e5b8-435e-bef5-3964ffdce07d";
        List<User> allUsers = Arrays.stream(userService.getAll()).toList();

        Project project = projectService.getById(projectId);
        System.out.println("Project: " + project);

        project.setName("Capture des bijuu (encore une fois)");
        project.setDescription("L'equipe dans Naruto la");
        project.setColor("#000000");
        project.setPinnedUsers(allUsers);
        Project updatedProject = projectService.update(project);
        System.out.println("Updated Project: " + updatedProject);

        project.setName("Akatsuki");
        project.setDescription("Association de malfaiteurs");
        project.setId(null);
        Project newProject = projectService.create(project);
        System.out.println("New Project: " + newProject);

        Project[] projects = projectService.getAll();
        System.out.println("All Projects: " + Arrays.toString(projects));

        Project deletedProject = projectService.delete(newProject.getId());
        System.out.println("Deleted Project: " + deletedProject);
    }

    @Override
    protected String getEntityId(Project entity) {
        return entity.getId();
    }

    @Override
    protected TypeToken<Project> getTypeToken() {
        return new TypeToken<>() {
        };
    }

    @Override
    protected TypeToken<Project[]> getArrayTypeToken() {
        return new TypeToken<>() {
        };
    }
}
