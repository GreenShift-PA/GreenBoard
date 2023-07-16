package com.greenshift.greenboard.services;

import com.greenshift.greenboard.models.entities.Task;
import com.greenshift.greenboard.models.entities.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class TeamService extends BaseCrudService<Team> {

    public TeamService(String baseUrl) {
        super(baseUrl);
    }

    public static void main(String[] args) {
        TeamService teamService = new TeamService("http://localhost:3000/api/v1/teams");
        String teamId = "fd8e89d8-e33b-457b-8550-cfd026bd9fdc";

        List<Task> tasks = Arrays.stream(teamService.getTasks(teamId)).toList();
        System.out.println("Tasks: " + tasks);
    }

    public Task[] getTasks(String teamId) {
        try {
            URL url = new URL(BASE_URL + "/" + teamId + "/tasks");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                return gson.fromJson(response.toString(), Task[].class);
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        return null;
    }

    @Override
    protected String getEntityId(Team entity) {
        return entity.getId();
    }
}
