package com.greenshift.greenboard.singletons;

import com.greenshift.greenboard.models.entities.Role;
import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.services.UserService;

public class SessionManager {
    private static SessionManager instance;
    private User currentUser;
    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "currentUser=" + currentUser +
                '}';
    }

    public boolean isAuthenticated() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {

        System.out.println("UserSession.setCurrentUser: " + user);
        this.currentUser = user;
    }

    public void useDummyUser() {
        UserService userService = new UserService("http://localhost:3000/api/v1/users");
        this.currentUser = userService.getById("180d6478-50c0-42d6-9dda-4b941b90551b", User.class);
    }

    public void logout() {
        currentUser = null;
    }
}
