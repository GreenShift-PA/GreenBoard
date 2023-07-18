package com.greenshift.greenboard.services;

import com.google.gson.reflect.TypeToken;
import com.greenshift.greenboard.models.entities.Task;
import com.greenshift.greenboard.models.entities.TaskStatus;

import java.util.Arrays;

public class TaskStatusService extends BaseCrudService<TaskStatus> {

    public TaskStatusService() {
        super("http://localhost:3000/api/v1/task-status");
    }


    public static void main(String[] args) {

    }

    @Override
    protected String getEntityId(TaskStatus entity) {
        return entity.getId();
    }

    @Override
    protected TypeToken<TaskStatus> getTypeToken() {
        return new TypeToken<>() {
        };
    }

    @Override
    protected TypeToken<TaskStatus[]> getArrayTypeToken() {
        return new TypeToken<>() {
        };
    }

    public TaskStatus getTaskStatusByName(String todo) {
        TaskStatus[] taskStatuses = getAll();
        return Arrays.stream(taskStatuses).filter(taskStatus -> taskStatus.getName().equals(todo)).findFirst().orElse(null);
    }
}
