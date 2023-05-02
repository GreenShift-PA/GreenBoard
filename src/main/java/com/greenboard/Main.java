package com.greenboard;

import com.greenboard.db.PostgreSQL;
import com.greenboard.enums.TaskStatus;
import com.greenboard.models.Task;
import com.greenboard.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        // com.greenboard.singletons.Application.getInstance().setCurrentUser(PostgreSQL.getUserByEmail("user1@localhost.com"));

        boolean isLoggedIn = com.greenboard.singletons.Application.getInstance().getCurrentUser() != null;

        FXMLLoader fxmlLoader = null;
        if(isLoggedIn) {
            fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
        }else{
            fxmlLoader = new FXMLLoader(Main.class.getResource("auth.fxml"));
        }

        String css = Objects.requireNonNull(Main.class.getResource("styles.css")).toExternalForm();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(css);

        stage.setTitle("GreenBoard");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        com.greenboard.singletons.Application.getInstance().init();
        launch();
/*
        User user = PostgreSQL.getUserById(UUID.fromString("7fe8ef96-bd8f-4ec9-afd0-82d2eaa0c0c4"));
        System.out.println(user);

        List<Task> tasks = PostgreSQL.getTasksFromUserId(UUID.fromString("7fe8ef96-bd8f-4ec9-afd0-82d2eaa0c0c4"));
        System.out.println(tasks);*/

    }
}