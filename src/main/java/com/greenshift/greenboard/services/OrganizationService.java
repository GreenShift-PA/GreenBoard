package com.greenshift.greenboard.services;

import com.greenshift.greenboard.models.entities.Organization;
import com.greenshift.greenboard.models.entities.Project;
import com.greenshift.greenboard.models.entities.Team;
import com.greenshift.greenboard.models.entities.User;

import java.util.Arrays;
import java.util.List;

public class OrganizationService extends BaseCrudService<Organization> {

    public OrganizationService(String baseUrl) {
        super(baseUrl);
    }

    public static void main(String[] args) {
        OrganizationService organizationService = new OrganizationService("http://localhost:3000/api/v1/organizations");
        TeamService teamService = new TeamService("http://localhost:3000/api/v1/teams");
        ProjectService projectService = new ProjectService("http://localhost:3000/api/v1/projects");

        List<Team> allTeams = Arrays.stream(teamService.getAll(Team[].class)).toList();
        List<Project> allProjects = Arrays.stream(projectService.getAll(Project[].class)).toList();

        System.out.println("All Teams: " + allTeams);
        System.out.println("All Projects: " + allProjects);

        String organizationId = "c62d9247-8d2e-4664-8c78-48d4ea26ff3d";

        Organization organization = organizationService.getById(organizationId, Organization.class);
        System.out.println("Organization: " + organization);

        String metadata = gson.toJson("{ \"key\": \"value\" }");

        organization.setName("Dark Bulls");
        organization.setDescription("L'equipe dans Naruto la");
        organization.setIcon("fas-fa-icon");
        organization.setMetadata(metadata);
        organization.setTeams(allTeams);
        organization.setProjects(allProjects);
        Organization updatedOrganization = organizationService.update(organization, Organization.class);
        System.out.println("Updated Organization: " + updatedOrganization);

        organization.setName("Silver Wings");
        organization.setDescription("Village caché des feuilles");
        organization.setId(null);
        Organization newOrganization = organizationService.create(organization, Organization.class);
        System.out.println("New Organization: " + newOrganization);

        Organization[] organizations = organizationService.getAll(Organization[].class);
        System.out.println("All Organizations: " + Arrays.toString(organizations));

        Organization deletedOrganization = organizationService.delete(newOrganization.getId(), Organization.class);
        System.out.println("Deleted Organization: " + deletedOrganization);
    }

    @Override
    protected String getEntityId(Organization entity) {
        return entity.getId();
    }
}
