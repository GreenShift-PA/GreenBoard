package com.greenboard.singletons;

import com.greenboard.db.PostgreSQL;
import com.greenboard.models.Task;
import com.greenboard.models.User;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static Application instance = null;

    private User currentUser;
    private List<Task> tasks = null;

    private Application() {
        tasks = new ArrayList<>();
    }

    public static synchronized Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    public void init() {
        if(currentUser == null) {
            return;
        }
    }

    public List<Task> getTasks() {
        this.tasks = PostgreSQL.getTasksFromUserId(currentUser.getUserId());
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public User getCurrentUser() {
        if(currentUser == null) {
            return null;
        }
        this.tasks = PostgreSQL.getTasksFromUserId(currentUser.getUserId());
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        if(currentUser == null) {
            return;
        }
        this.tasks = PostgreSQL.getTasksFromUserId(currentUser.getUserId());
    }


}
