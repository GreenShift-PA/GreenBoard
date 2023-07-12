package com.greenshift.greenboard.singletons;

import com.greenshift.greenboard.models.entities.User;

public class UserSession {
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
        this.currentUser = user;
    }

    public void logout() {
        currentUser = null;
    }
}
