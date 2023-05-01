package com.greenboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        String css = Main.class.getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        this.stage = stage;

        this.stage.setTitle("GreenBoard");
        this.stage.setScene(scene);
        this.stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}