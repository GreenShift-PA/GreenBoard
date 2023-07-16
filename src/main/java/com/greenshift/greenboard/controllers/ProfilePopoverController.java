package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.cells.ProfilePopoverItemCell;
import com.greenshift.greenboard.models.entities.Organization;
import com.greenshift.greenboard.models.entities.Team;
import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.models.ui.ProfilePopoverItem;
import com.greenshift.greenboard.singletons.SessionManager;
import com.jfoenix.controls.JFXListView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.List;

public class ProfilePopoverController {
    public Label userEmailLabel;
    public FontIcon optionIcon;
    public JFXListView<ProfilePopoverItem> organizationListView;
    public HBox logout;

    public void initialize() {

        User currentUser = SessionManager.getInstance().getCurrentUser();

        if (currentUser == null)
            return;

        userEmailLabel.setText(currentUser.getEmail());
        List<Organization> organizations = currentUser.getTeams().stream().map(Team::getOrganization).toList();

        organizations.forEach(o -> {
            ProfilePopoverItem item = new ProfilePopoverItem(o);
            organizationListView.getItems().add(item);
        });

        organizationListView.setCellFactory(param -> new ProfilePopoverItemCell());


        logout.setOnMouseClicked(event -> {
            SessionManager.getInstance().logout();
            System.out.println("Log out");
        });
    }
}
