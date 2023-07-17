package com.greenshift.greenboard.services;

import com.google.gson.reflect.TypeToken;
import com.greenshift.greenboard.models.entities.Task;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskService extends BaseCrudService<Task> {

    public TaskService() {
        super("http://localhost:3000/api/v1/tasks");
    }


    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        String taskId = "e69ca3e6-b6df-4741-a470-ea175ad09cc2";

        Task task = taskService.getById(taskId);
        System.out.println("Task: " + task);

        task.setName("Updated Task Name");
        task.setDescription("Updated Task Description");
        Task updatedTask = taskService.update(task);
        System.out.println("Updated Task: " + updatedTask);

        task.setName("New Task Name");
        task.setDescription("New Task Description");
        task.setId(null);
        task.setTeam(null);
        Task newtask = taskService.create(task);
        System.out.println("New Task: " + newtask);

        Task[] tasks = taskService.getAll();
        System.out.println("All Tasks: " + Arrays.toString(tasks));

        Task deletedTask = taskService.delete(newtask.getId());
        System.out.println("Deleted Task: " + deletedTask);
    }

    @Override
    protected String getEntityId(Task entity) {
        return entity.getId();
    }

    @Override
    protected TypeToken<Task> getTypeToken() {
        return new TypeToken<>() {
        };
    }

    @Override
    protected TypeToken<Task[]> getArrayTypeToken() {
        return new TypeToken<>() {
        };
    }
}
