package com.greenboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        String css = Main.class.getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("GreenBoard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}