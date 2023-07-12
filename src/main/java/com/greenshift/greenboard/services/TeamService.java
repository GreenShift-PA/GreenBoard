package com.greenshift.greenboard.services;

import com.greenshift.greenboard.models.entities.Team;
import com.greenshift.greenboard.models.entities.User;

import java.util.Arrays;
import java.util.List;

public class TeamService extends BaseCrudService<Team> {

    public TeamService(String baseUrl) {
        super(baseUrl);
    }

    public static void main(String[] args) {
        TeamService teamService = new TeamService("http://localhost:3000/api/v1/teams");
        UserService userService = new UserService("http://localhost:3000/api/v1/users");
        String teamId = "1df95aaa-34d4-4483-a5d1-ccf850de94fc";
        List<User> allUsers = Arrays.stream(userService.getAll(User[].class)).toList();

        Team team = teamService.getById(teamId, Team.class);
        System.out.println("Team: " + team);

        team.setName("Equipe 7");
        team.setDescription("L'equipe dans Naruto la");
        team.setMembers(allUsers);
        Team updatedTeam = teamService.update(team, Team.class);
        System.out.println("Updated Team: " + updatedTeam);

        team.setName("Akatsuki");
        team.setDescription("Association de malfaiteurs");
        team.setId(null);
        Team newTeam = teamService.create(team, Team.class);
        System.out.println("New Team: " + newTeam);

        Team[] teams = teamService.getAll(Team[].class);
        System.out.println("All Teams: " + Arrays.toString(teams));

        Team deletedTeam = teamService.delete(newTeam.getId(), Team.class);
        System.out.println("Deleted Team: " + deletedTeam);
    }

    @Override
    protected String getEntityId(Team entity) {
        return entity.getId();
    }
}
