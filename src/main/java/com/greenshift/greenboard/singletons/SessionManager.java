package com.greenshift.greenboard.singletons;

import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.services.AuthService;

import java.util.Objects;

public class SessionManager {
    private static SessionManager instance;
    private User currentUser;
    private String token;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public static void main(String[] args) {
        SessionManager sessionManager = SessionManager.getInstance();
        sessionManager.useDummyUser();
        System.out.println("SessionManager.main: " + sessionManager);
        System.out.println("SessionManager.main: " + sessionManager.isAuthenticated());
        System.out.println("SessionManager.main: " + sessionManager.getCurrentUser());
        sessionManager.logout();
        System.out.println("SessionManager.main: " + sessionManager.isAuthenticated());
        System.out.println("SessionManager.main: " + sessionManager.getCurrentUser());
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

    public void refetchCurrentUser() {
        if (currentUser == null) {
            return;
        }
        currentUser = AuthService.fetchUser(this.token);

        if (currentUser == null) {
            logout();
        }
    }

    public void useDummyUser() {
        String token = AuthService.authenticate("admin@mail.com", "Respons11");
        User user = AuthService.fetchUser(token);
        setCurrentUser(user);
    }

    public void logout() {
        AuthService.logout(token);
        currentUser = null;
        SceneManager.getInstance().switchToScene("/fxml/auth-view.fxml", null, null, scene -> {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
        });

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SessionManager{" +
                "currentUser=" + currentUser +
                ", token='" + token + '\'' +
                '}';
    }
}
