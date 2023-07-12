package com.greenshift.greenboard;

import com.greenshift.greenboard.controllers.MainController;
import com.greenshift.greenboard.singletons.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/main-view.fxml"));
        StackPane root = fxmlLoader.load();
        MainController controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(MainApplication.class.getResource("/css/styles.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(MainApplication.class.getResource("/css/hierarchy.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(MainApplication.class.getResource("/css/kanban.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.UNIFIED);
        stage.setResizable(false);
        stage.setScene(scene);

        SceneManager.getInstance().init(stage);
        SceneManager.getInstance().setMainController(controller);

        stage.show();
    }
}