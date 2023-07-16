package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.singletons.SessionManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class SettingsController {
    public HBox myAccountTab;
    public HBox preferenceTab;
    public HBox notificationsTab;
    public HBox connexionsTab;
    public HBox languageTab;
    public Label settingsViewTitle;
    public VBox settingsView;
    public VBox organizationSettings;
    public HBox importExportTab;
    public HBox updateTab;
    public HBox organizationSettingsTab;
    public HBox organizationTeamsTab;

    private String currentTab = "my-account";

    private HashMap<String, String> tabNames = new HashMap<>();

    private User currentUser;

    public void initialize() {

        currentUser = SessionManager.getInstance().getCurrentUser();

        initTabVisibility();

        tabNames.put("my-account", "My Account");
        tabNames.put("preferences", "Preferences");
        tabNames.put("notifications", "Notifications");
        tabNames.put("connections", "Connections");
        tabNames.put("language", "Language");
        tabNames.put("import-export", "Import/Export");
        tabNames.put("update", "Update");
        tabNames.put("organization-settings", "Organization Settings");
        tabNames.put("organization-teams", "Organization Teams");


        myAccountTab.setOnMouseClicked(event -> {
            System.out.println("My Account");
            loadTab("my-account");
            settingsViewTitle.setText(tabNames.get("my-account"));
        });
        preferenceTab.setOnMouseClicked(event -> {
            System.out.println("Preferences");
            loadTab("preferences");
            settingsViewTitle.setText(tabNames.get("preferences"));
        });
        notificationsTab.setOnMouseClicked(event -> {
            System.out.println("Notifications");
            loadTab("notifications");
            settingsViewTitle.setText(tabNames.get("notifications"));
        });
        connexionsTab.setOnMouseClicked(event -> {
            System.out.println("Connections");
            loadTab("connections");
            settingsViewTitle.setText(tabNames.get("connections"));
        });
        languageTab.setOnMouseClicked(event -> {
            System.out.println("Language");
            loadTab("language");
            settingsViewTitle.setText(tabNames.get("language"));
        });

        importExportTab.setOnMouseClicked(event -> {
            System.out.println("Import/Export");
            loadTab("import-export");
            settingsViewTitle.setText(tabNames.get("import-export"));
        });

        updateTab.setOnMouseClicked(event -> {
            System.out.println("Update");
            loadTab("update");
            settingsViewTitle.setText(tabNames.get("update"));
        });

        organizationSettingsTab.setOnMouseClicked(event -> {
            System.out.println("Organization Settings");
            loadTab("organization-settings");
            settingsViewTitle.setText(tabNames.get("organization-settings"));
        });

        organizationTeamsTab.setOnMouseClicked(event -> {
            System.out.println("Organization Teams");
            loadTab("organization-teams");
            settingsViewTitle.setText(tabNames.get("organization-teams"));
        });

        loadTab("my-account");

    }

    private void loadTab(String tabName) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/settings/" + tabName + ".fxml"));
        try {
            settingsView.getChildren().clear();
            settingsView.getChildren().add(fxmlLoader.load());

            currentTab = tabName;
            settingsViewTitle.setText(tabNames.get(tabName));
        } catch (Exception e) {
            e.printStackTrace();
            settingsView.getChildren().clear();
            settingsView.getChildren().add(new Label("Error loading tab"));
        }
    }


    private void initTabVisibility() {
        if (currentUser == null)
            return;

        if (currentUser.getRole().getName().contains("ADMIN") || (currentUser.getLastTeam() != null && currentUser.getLastTeam().getOrganization().getType().equals("PERSONAL"))) {
            organizationSettings.setVisible(true);
        } else {
            organizationSettings.setVisible(false);
        }
    }
}
