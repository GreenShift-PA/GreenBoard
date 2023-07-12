package com.greenshift.greenboard.services;

import com.greenshift.greenboard.models.entities.Project;
import com.greenshift.greenboard.models.entities.User;

import java.util.Arrays;
import java.util.List;

public class ProjectService extends BaseCrudService<Project> {

    public ProjectService(String baseUrl) {
        super(baseUrl);
    }

    public static void main(String[] args) {
        ProjectService projectService = new ProjectService("http://localhost:3000/api/v1/projects");
        UserService userService = new UserService("http://localhost:3000/api/v1/users");
        String projectId = "9e908fcf-e5b8-435e-bef5-3964ffdce07d";
        List<User> allUsers = Arrays.stream(userService.getAll(User[].class)).toList();

        Project project = projectService.getById(projectId, Project.class);
        System.out.println("Project: " + project);

        project.setName("Capture des bijuu (encore une fois)");
        project.setDescription("L'equipe dans Naruto la");
        project.setColor("#000000");
        project.setPinnedUsers(allUsers);
        Project updatedProject = projectService.update(project, Project.class);
        System.out.println("Updated Project: " + updatedProject);

        project.setName("Akatsuki");
        project.setDescription("Association de malfaiteurs");
        project.setId(null);
        Project newProject = projectService.create(project, Project.class);
        System.out.println("New Project: " + newProject);

        Project[] projects = projectService.getAll(Project[].class);
        System.out.println("All Projects: " + Arrays.toString(projects));

        Project deletedProject = projectService.delete(newProject.getId(), Project.class);
        System.out.println("Deleted Project: " + deletedProject);
    }

    @Override
    protected String getEntityId(Project entity) {
        return entity.getId();
    }
}
