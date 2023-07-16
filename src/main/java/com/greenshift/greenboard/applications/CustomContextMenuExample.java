package com.greenshift.greenboard.applications;

import com.greenshift.greenboard.cells.LeftMenuItemCell;
import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.models.ui.LeftMenuItem;
import com.greenshift.greenboard.singletons.SessionManager;
import com.jfoenix.controls.JFXTreeView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class CustomContextMenuExample extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox(10);
        Scene scene = new Scene(vbox, 1320, 800);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/hierarchy.css")).toExternalForm());

        JFXTreeView<LeftMenuItem> treeView = new JFXTreeView<>();

        TreeItem<LeftMenuItem> root = new TreeItem<>(new LeftMenuItem("Graph Flow", "mdi2g-graph-outline"));
        root.getChildren().add(new TreeItem<>(new LeftMenuItem("Teamspace Home", "fas-compass")));
        root.getChildren().add(new TreeItem<>(new LeftMenuItem("Wiki", "mdi2b-book-open-variant")));

        TreeItem<LeftMenuItem> docs = new TreeItem<>(new LeftMenuItem("Docs", "mdi2f-file-document-outline"));
        docs.getChildren().add(new TreeItem<>(new LeftMenuItem("Recently edited", null)));
        docs.getChildren().add(new TreeItem<>(new LeftMenuItem("Table by Category", null)));
        docs.getChildren().add(new TreeItem<>(new LeftMenuItem("All", null)));
        docs.getChildren().add(new TreeItem<>(new LeftMenuItem("Mine", null,
                (item) -> System.out.println("Clicked on menu item: " + item.getName()))));

        SessionManager.getInstance().useDummyUser();
        User currentUser = SessionManager.getInstance().getCurrentUser();

        if (currentUser != null) {
            currentUser.getTeams().forEach(team -> {
                TreeItem<LeftMenuItem> teamItem = new TreeItem<>(new LeftMenuItem(team.getName(), null));
                team.getMembers().forEach(member -> {
                    TreeItem<LeftMenuItem> memberItem = new TreeItem<>(new LeftMenuItem(member.getFirstName(), null));
                    teamItem.getChildren().add(memberItem);
                });
                root.getChildren().add(teamItem);
            });
        }

        root.getChildren().add(docs);

        treeView.setRoot(root);
        treeView.setEditable(true);
        treeView.setCellFactory(p -> new LeftMenuItemCell());

        scene.setRoot(treeView);

        stage.setTitle("Hierarchy !");
        stage.setScene(scene);
        stage.show();
    }
}