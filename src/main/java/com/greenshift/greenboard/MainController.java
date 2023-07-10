package com.greenshift.greenboard;

import com.greenshift.greenboard.factories.impl.LeftMenuItemCellImpl;
import com.greenshift.greenboard.models.ui.LeftMenuItem;
import com.jfoenix.controls.JFXTreeView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainController {
    @FXML
    public VBox teamTreeViewVBox;
    @FXML
    public VBox projectTreeViewVBox;
    @FXML
    public JFXTreeView<LeftMenuItem> teamTreeView;
    public JFXTreeView<LeftMenuItem> projectTreeView;

    @FXML
    private Label welcomeText;
    @FXML
    private SplitPane split;

    private boolean isLeftMenuOpen = false;

    @FXML
    private TopBarController topBarController;

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

        initTeamTreeView();
        initProjectTreeView();
    }

    private void initTeamTreeView() {

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

        teamTreeView.setRoot(rootItem);
        teamTreeView.setEditable(true);
        teamTreeView.setCellFactory(p -> new LeftMenuItemCellImpl());
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
        projectTreeView.setCellFactory(p -> new LeftMenuItemCellImpl());
    }


}