package com.greenshift.greenboard.singletons;

import com.greenshift.greenboard.models.entities.User;

public class UserSession {
    @Override
    public String toString() {
        return "UserSession{" +
                "currentUser=" + currentUser +
                '}';
    }

    private static UserSession instance;
    private User currentUser;

    private UserSession() {
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public boolean isAuthenticated() {
        return currentUser != null;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }
}
