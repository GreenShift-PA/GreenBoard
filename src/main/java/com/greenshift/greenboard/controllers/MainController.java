package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.TopBarController;
import com.greenshift.greenboard.cells.LeftMenuItemCell;
import com.greenshift.greenboard.interfaces.IContentLoadedCallback;
import com.greenshift.greenboard.models.ui.LeftMenuItem;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTreeView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {

    @FXML
    public JFXDialog dialog;

    @FXML
    public StackPane root;

    public StackPane context;

    @FXML
    public VBox teamTreeViewVBox;
    @FXML
    public VBox projectTreeViewVBox;
    @FXML
    public JFXTreeView<LeftMenuItem> teamTreeView;
    public JFXTreeView<LeftMenuItem> projectTreeView;

    @FXML
    public AnchorPane rightContent;

    @FXML
    private Label welcomeText;
    @FXML
    private SplitPane split;

    private boolean isLeftMenuOpen = false;

    @FXML
    private TopBarController topBarController;

    private String currentRightContent = "";

    @FXML
    protected void toggleLeftMenu() {
        if (isLeftMenuOpen) {
            split.setDividerPositions(0.0);
            isLeftMenuOpen = false;
        } else {
            split.setDividerPositions(0.2);
            isLeftMenuOpen = true;
        }
    }

    /* print a message on startup */
    public void initialize() {
        System.out.println("Application started!");
        split.setMinSize(0, 0);

        context = root;
        root.getChildren().remove(dialog);
        dialog.getContent().setPadding(new javafx.geometry.Insets(0));


        initTeamTreeView();
        initProjectTreeView();
    }

    private void initTeamTreeView() {

        TreeItem<LeftMenuItem> rootItem = new TreeItem<>(new LeftMenuItem("Graph Flow", "mdi2g-graph-outline"));
        rootItem.getChildren().add(new TreeItem<>(new LeftMenuItem("Teamspace Home", "fas-compass")));
        rootItem.getChildren().add(new TreeItem<>(new LeftMenuItem("Wiki", "mdi2b-book-open-variant")));

        TreeItem<LeftMenuItem> tasks = new TreeItem<>(new LeftMenuItem("Tasks", "mdi2f-file-document-outline", (item) -> {
            loadContent("/fxml/kanban.fxml", (node) -> {
                System.out.println("Loaded kanban");
            });
        }));

        tasks.getChildren().add(new TreeItem<>(new LeftMenuItem("Not Started", null)));
        tasks.getChildren().add(new TreeItem<>(new LeftMenuItem("In Progress", null)));
        tasks.getChildren().add(new TreeItem<>(new LeftMenuItem("Done", null)));


        TreeItem<LeftMenuItem> docs = new TreeItem<>(new LeftMenuItem("Docs", "mdi2f-file-document-outline"));
        docs.getChildren().add(new TreeItem<>(new LeftMenuItem("Recently edited", null)));
        docs.getChildren().add(new TreeItem<>(new LeftMenuItem("Table by Category", null)));
        docs.getChildren().add(new TreeItem<>(new LeftMenuItem("All", null)));
        docs.getChildren().add(new TreeItem<>(new LeftMenuItem("Mine", null,
                (item) -> {
                    System.out.println("Clicked on menu item: " + item.getName());
                })));

        rootItem.getChildren().add(docs);
        rootItem.getChildren().add(tasks);

        teamTreeView.setRoot(rootItem);
        teamTreeView.setEditable(true);
        teamTreeView.setCellFactory(p -> new LeftMenuItemCell());
    }

    private void initProjectTreeView() {

        TreeItem<LeftMenuItem> rootItem = new TreeItem<>(new LeftMenuItem("Graph Flow", "mdi2g-graph-outline"));
        rootItem.getChildren().add(new TreeItem<>(new LeftMenuItem("Teamspace Home", "fas-compass")));
        rootItem.getChildren().add(new TreeItem<>(new LeftMenuItem("Wiki", "mdi2b-book-open-variant")));

        TreeItem<LeftMenuItem> docs = new TreeItem<>(new LeftMenuItem("Docs", "mdi2f-file-document-outline"));
        docs.getChildren().add(new TreeItem<>(new LeftMenuItem("Recently edited", null)));
        docs.getChildren().add(new TreeItem<>(new LeftMenuItem("Table by Category", null)));
        docs.getChildren().add(new TreeItem<>(new LeftMenuItem("All", null)));
        docs.getChildren().add(new TreeItem<>(new LeftMenuItem("Mine", null,
                (item) -> {
                    System.out.println("Clicked on menu item: " + item.getName());
                })));

        rootItem.getChildren().add(docs);

        projectTreeView.setRoot(rootItem);
        projectTreeView.setEditable(true);
        projectTreeView.setCellFactory(p -> new LeftMenuItemCell());
    }


    public void openSettings(MouseEvent mouseEvent) {
        System.out.println("Clicked on settings");
        dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
        dialog.show(context);
    }

    public void loadContent(String fxmlFile, IContentLoadedCallback callback) {
        if (fxmlFile.equals(currentRightContent)) {
            System.out.println("Already loaded");
            return;
        }
        currentRightContent = fxmlFile;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Node content = loader.load();
            rightContent.getChildren().clear();
            rightContent.getChildren().add(content);
            if (callback != null) {
                callback.onContentLoaded(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}