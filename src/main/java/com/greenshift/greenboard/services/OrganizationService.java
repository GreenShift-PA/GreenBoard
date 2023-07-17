package com.greenshift.greenboard.services;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.greenshift.greenboard.models.entities.Organization;
import com.greenshift.greenboard.models.entities.Project;
import com.greenshift.greenboard.models.entities.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrganizationService extends BaseCrudService<Organization> {

    public OrganizationService() {
        super("http://localhost:3000/api/v1/organizations");
    }

    public static void main(String[] args) {
        OrganizationService organizationService = new OrganizationService();
        TeamService teamService = new TeamService();
        ProjectService projectService = new ProjectService();

        List<Team> allTeams = Arrays.stream(teamService.getAll()).toList();
        List<Project> allProjects = Arrays.stream(projectService.getAll()).toList();

        System.out.println("All Teams: " + allTeams);
        System.out.println("All Projects: " + allProjects);

        String organizationId = "c62d9247-8d2e-4664-8c78-48d4ea26ff3d";

        Organization organization = organizationService.getById(organizationId);
        System.out.println("Organization: " + organization);

        JsonObject metadata = new JsonObject();
        metadata.add("key", new JsonObject());
        metadata.get("key").getAsJsonObject().addProperty("value", "value");

        organization.setName("Dark Bulls");
        organization.setDescription("L'equipe dans Naruto la");
        organization.setIcon("fas-fa-icon");
        organization.setMetadata(metadata);
        organization.setTeams(allTeams);
        organization.setProjects(allProjects);
        Organization updatedOrganization = organizationService.update(organization);
        System.out.println("Updated Organization: " + updatedOrganization);

        organization.setName("Silver Wings");
        organization.setDescription("Village caché des feuilles");
        organization.setId(null);
        Organization newOrganization = organizationService.create(organization);
        System.out.println("New Organization: " + newOrganization);

        Organization[] organizations = organizationService.getAll();
        System.out.println("All Organizations: " + Arrays.toString(organizations));

        Organization deletedOrganization = organizationService.delete(newOrganization.getId());
        System.out.println("Deleted Organization: " + deletedOrganization);
    }

    @Override
    protected String getEntityId(Organization entity) {
        return entity.getId();
    }

    @Override
    protected TypeToken<Organization> getTypeToken() {
        return new TypeToken<>() {
        };
    }

    @Override
    protected TypeToken<Organization[]> getArrayTypeToken() {
        return new TypeToken<>() {
        };
    }
}
