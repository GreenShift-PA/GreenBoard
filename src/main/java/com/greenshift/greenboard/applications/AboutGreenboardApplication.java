package com.greenshift.greenboard.applications;

import javafx.application.Application;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class AboutGreenboardApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Main Application");

        Button btn = new Button();
        btn.setText("About");
        btn.setOnAction(event -> showAbout());

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private void showAbout() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(null);

        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is an application."));
        dialogVbox.getChildren().add(new Text("Version 1.0.0"));
        dialogVbox.getChildren().add(new Text("Copyright (c) 2023"));

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
