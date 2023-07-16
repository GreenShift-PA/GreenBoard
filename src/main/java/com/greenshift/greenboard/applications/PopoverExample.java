package com.greenshift.greenboard.applications;

import com.greenshift.greenboard.singletons.SessionManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.Objects;

public class PopoverExample extends Application {

    private Popup popover;
    private boolean isPopoverOpen;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SessionManager.getInstance().useDummyUser();

        Button userProfileButton = new Button("Profile Picture");
        userProfileButton.setOnAction(event -> togglePopover(userProfileButton));

        VBox root = new VBox(userProfileButton);
        root.setAlignment(Pos.TOP_LEFT);
        root.setSpacing(10);

        Scene scene = new Scene(root, 400, 300);
        scene.setOnMouseClicked(this::handleSceneClicked);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/popover.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/hierarchy.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/kanban.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/organization.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void togglePopover(Button userProfileButton) {
        if (isPopoverOpen) {
            closePopover();
        } else {
            openPopover(userProfileButton);
        }
    }

    private void openPopover(Button userProfileButton) {
        popover = new Popup();
        popover.setAutoHide(true);
        popover.setAutoFix(true);

        Bounds buttonBounds = userProfileButton.localToScreen(userProfileButton.getBoundsInLocal());
        double popoverX = buttonBounds.getMinX();
        double popoverY = buttonBounds.getMaxY();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/profile-popover.fxml"));
            AnchorPane root = fxmlLoader.load();
            popover.getContent().add(root);
            popover.show(userProfileButton, popoverX, popoverY);
            isPopoverOpen = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void closePopover() {
        popover.hide();
        isPopoverOpen = false;
    }

    private void handleSceneClicked(MouseEvent event) {
        if (isPopoverOpen) {
            closePopover();
        }
    }
}
