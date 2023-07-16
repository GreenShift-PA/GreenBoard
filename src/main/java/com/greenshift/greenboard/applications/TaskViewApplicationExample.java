package com.greenshift.greenboard.applications;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TaskViewApplicationExample extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 1320, 800);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/task-view.fxml"));
        VBox taskView = loader.load();

        root.getChildren().add(taskView);

        stage.setTitle("Greenboard - Task View");
        stage.setScene(scene);
        stage.show();
    }
}