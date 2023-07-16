package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.TopBarController;
import com.greenshift.greenboard.cells.LeftMenuItemCell;
import com.greenshift.greenboard.interfaces.IContentLoadedCallback;
import com.greenshift.greenboard.models.entities.Organization;
import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.models.ui.LeftMenuItem;
import com.greenshift.greenboard.services.UserService;
import com.greenshift.greenboard.singletons.SceneManager;
import com.greenshift.greenboard.singletons.SessionManager;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTreeView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    @FXML
    public JFXDialog dialog;

    @FXML
    public StackPane root;

    public StackPane context;

    public HBox profileMenuButton;
    @FXML
    public VBox teamTreeViewVBox;
    @FXML
    public VBox projectTreeViewVBox;
    @FXML
    public JFXTreeView<LeftMenuItem> teamTreeView;
    public JFXTreeView<LeftMenuItem> projectTreeView;

    @FXML
    public AnchorPane rightContent;
    public FontAwesomeIconView teamMenuButton;
    public FontIcon createNewTeamButton;
    public FontAwesomeIconView projectMenuButton;
    public FontIcon createNewProjectButton;
    public FontIcon organizationIcon;
    public FontAwesomeIconView topBarHamburger;
    public HBox leftMenuSettingsButton;
    public HBox leftMenuNewOrganizationButton;
    public VBox organizationIconVBox;
    public Label organizationNameLabel;
    public HBox leftMenuSearchButton;
    public HBox leftMenuHistoryButton;
    public HBox leftMenuAllTeams;
    public ScrollPane kanban;
    @FXML
    private SplitPane split;

    private Popup popover;
    private boolean isPopoverOpen;


    private boolean isLeftMenuOpen = false;

    @FXML
    private TopBarController topBarController;

    private String currentRightContent = "";

    private User currentUser = null;

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

        SceneManager.getInstance().setMainController(this);

        currentUser = SessionManager.getInstance().getCurrentUser();

        if (currentUser != null) {
            if (currentUser.getLastTeam() != null && currentUser.getLastTeam().getOrganization() != null) {
                Organization organization = currentUser.getLastTeam().getOrganization();
                if (organization.getIcon() != null && !organization.getIcon().isEmpty()){
                    organizationIcon.setIconLiteral(organization.getIcon());
                    if(organization.getColor() != null && !organization.getColor().isEmpty())
                        organizationIcon.setIconColor(Paint.valueOf(organization.getColor()));
                }

                organizationNameLabel.setText(currentUser.getLastTeam().getOrganization().getName());
            }
        }

        context = root;
        root.getChildren().remove(dialog);
        dialog.getContent().setPadding(new javafx.geometry.Insets(0));

        SceneManager.getInstance().setContext(root);

        initTeamTreeView();
        initProjectTreeView();

        profileMenuButton.setOnMouseClicked(event -> togglePopover(profileMenuButton));
    }

    private void initTeamTreeView() {

        if (currentUser == null)
            return;

        TreeItem<LeftMenuItem> rootItem = new TreeItem<>(new LeftMenuItem("Teams", "mdi2g-graph-outline"));
        teamTreeView.setRoot(rootItem);

        currentUser.getTeams().forEach(team -> {
            TreeItem<LeftMenuItem> teamItem = new TreeItem<>(new LeftMenuItem(team.getName(), "mdi2f-file-document-outline"));
            teamItem.getChildren().add(new TreeItem<>(
                    new LeftMenuItem("Tasks", "mdi2f-file-document-outline", (item) -> {

                        currentUser.setlastTeam(team);
                        currentUser.setlastTeamId(team.getId());
                        UserService userService = new UserService("http://localhost:3000/api/v1/users");
                        User updatedUser = userService.update(currentUser, User.class);
                        if(updatedUser != null)
                        {
                            currentUser = updatedUser;
                            SessionManager.getInstance().refetchCurrentUser();
                        }else {
                            System.out.println("Failed to update user");
                        }

                        loadContent("/fxml/kanban.fxml", (node) -> {
                            System.out.println("Loaded kanban");
                        });
                    })));
            teamTreeView.getRoot().getChildren().add(teamItem);
        });

        teamTreeView.setEditable(true);
        teamTreeView.setCellFactory(p -> new LeftMenuItemCell());
    }

    private void initProjectTreeView() {

        if (currentUser == null)
            return;

        TreeItem<LeftMenuItem> rootItem = new TreeItem<>(new LeftMenuItem("Projects", "mdi2g-graph-outline"));
        teamTreeView.setRoot(rootItem);

        if(currentUser.getLastTeam() != null) {
            currentUser.getLastTeam().getProjects().forEach(team -> {
                TreeItem<LeftMenuItem> teamItem = new TreeItem<>(new LeftMenuItem(team.getName(), "mdi2f-file-document-outline"));
                teamItem.getChildren().add(new TreeItem<>(
                        new LeftMenuItem("Tasks", "mdi2f-file-document-outline", (item) -> {
                            UserService userService = new UserService("http://localhost:3000/api/v1/users");
                            User updatedUser = userService.update(currentUser, User.class);
                            if(updatedUser != null)
                            {
                                currentUser = updatedUser;
                                SessionManager.getInstance().setCurrentUser(currentUser);
                            }else {
                                System.out.println("Failed to update user");
                            }

                            loadContent("/fxml/kanban.fxml", (node) -> {
                                System.out.println("Loaded kanban");
                            });
                        })));
                teamTreeView.getRoot().getChildren().add(teamItem);
            });
        }

        teamTreeView.setEditable(true);
        teamTreeView.setCellFactory(p -> new LeftMenuItemCell());
    }

    public void openSettings(MouseEvent mouseEvent) {
        openModal("/fxml/settings-view.fxml");
    }

    public void openNewOrganization(MouseEvent mouseEvent) {
        SceneManager.getInstance().switchToScene("/fxml/create-new-organization.fxml", null, null, scene -> {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/organization.css")).toExternalForm());
        });
    }

    public void openNewTeam(MouseEvent mouseEvent) {
        openModal("/fxml/team-create-view.fxml");
    }

    public void openNewProject(MouseEvent mouseEvent) {
        openModal("/fxml/project-create-view.fxml");
    }

    public void openModal(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            dialog.setContent(loader.load());
            dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialog.show(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadContent(String fxmlFile, IContentLoadedCallback callback) {
/*
        if (fxmlFile.equals(currentRightContent)) {
            System.out.println("Already loaded");
            return;
        }
*/
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

    private void togglePopover(HBox anchor) {
        if (isPopoverOpen) {
            closePopover();
        } else {
            openPopover(anchor);
        }
    }

    private void openPopover(HBox anchor) {
        popover = new Popup();
        popover.setAutoHide(true);
        popover.setAutoFix(true);

        Bounds buttonBounds = anchor.localToScreen(anchor.getBoundsInLocal());
        double popoverX = buttonBounds.getMinX();
        double popoverY = buttonBounds.getMaxY();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/profile-popover.fxml"));
            AnchorPane root = fxmlLoader.load();
            popover.getContent().add(root);
            popover.show(anchor, popoverX, popoverY);
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