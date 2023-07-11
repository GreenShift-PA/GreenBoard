package com.greenshift.greenboard.singletons;

import com.greenshift.greenboard.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class SceneManager {

    private static SceneManager instance;
    private Stage primaryStage;
    private Scene previousScene;


    private MainController mainController;

    private SceneManager() {
        this.primaryStage = new Stage();
        this.previousScene = null;
    }

    public void init(Stage stage) {
        this.primaryStage = stage;
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void switchToScene(String fxmlPath) {
        switchToScene(fxmlPath, null, null);
    }

    public void switchToScene(String fxmlPath, Consumer<Stage> stageConsumer) {
        switchToScene(fxmlPath, stageConsumer, null);
    }

    public void switchToScene(String fxmlPath, Consumer<Stage> stageConsumer, Consumer<Scene> sceneConsumer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            if (sceneConsumer != null) {
                sceneConsumer.accept(scene);
            }

            setPreviousScene(primaryStage.getScene()); // Store the previous scene
            primaryStage.setScene(scene);

            if (stageConsumer != null) {
                stageConsumer.accept(primaryStage);
            } else {
                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Scene getPreviousScene() {
        return previousScene;
    }

    private void setPreviousScene(Scene scene) {
        previousScene = scene;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
