package com.greenshift.greenboard.services;

import com.greenshift.greenboard.models.entities.Task;

import java.util.Arrays;

public class TaskService extends BaseCrudService<Task> {

    public TaskService(String baseUrl) {
        super(baseUrl);
    }

    public static void main(String[] args) {
        TaskService taskService = new TaskService("http://localhost:3000/api/v1/tasks");
        String taskId = "024f1d21-e3e6-4b69-80fe-ec73c8721b5a";

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
