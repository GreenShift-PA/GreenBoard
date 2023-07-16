package com.greenshift.greenboard.services;

import com.greenshift.greenboard.models.entities.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class TaskService extends BaseCrudService<Task> {

    public TaskService(String baseUrl) {
        super(baseUrl);
    }


    public static void main(String[] args) {
        TaskService taskService = new TaskService("http://localhost:3000/api/v1/tasks");
        String taskId = "e69ca3e6-b6df-4741-a470-ea175ad09cc2";

        Task task = taskService.getById(taskId, Task.class);
        System.out.println("Task: " + task);

        task.setName("Updated Task Name");
        task.setDescription("Updated Task Description");
        Task updatedTask = taskService.update(task, Task.class);
        System.out.println("Updated Task: " + updatedTask);

        task.setName("New Task Name");
        task.setDescription("New Task Description");
        task.setId(null);
        task.setTeam(null);
        Task newtask = taskService.create(task, Task.class);
        System.out.println("New Task: " + newtask);

        Task[] tasks = taskService.getAll(Task[].class);
        System.out.println("All Tasks: " + Arrays.toString(tasks));

        Task deletedTask = taskService.delete(newtask.getId(), Task.class);
        System.out.println("Deleted Task: " + deletedTask);
    }

    @Override
    protected String getEntityId(Task entity) {
        return entity.getId();
    }
}
