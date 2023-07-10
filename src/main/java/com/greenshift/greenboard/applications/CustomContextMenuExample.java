package com.greenshift.greenboard.applications;

import com.greenshift.greenboard.factories.impl.LeftMenuItemCellImpl;
import com.greenshift.greenboard.models.ui.LeftMenuItem;
import com.jfoenix.controls.JFXTreeView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CustomContextMenuExample extends Application {
    @Override
    public void start(Stage stage) throws IOException {
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
                (item) -> {
                    System.out.println("Clicked on menu item: " + item.getName());
                })));

        root.getChildren().add(docs);

        treeView.setRoot(root);
        treeView.setEditable(true);
        treeView.setCellFactory(p -> new LeftMenuItemCellImpl());

        scene.setRoot(treeView);

        stage.setTitle("Hierarchy !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}