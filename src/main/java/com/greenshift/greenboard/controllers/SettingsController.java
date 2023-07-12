package com.greenshift.greenboard.controllers;

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

    private String currentTab = "my-account";

    private HashMap<String, String> tabNames = new HashMap<>();

    public void initialize() {

        tabNames.put("my-account", "My Account");
        tabNames.put("preference", "Preference");
        tabNames.put("notifications", "Notifications");
        tabNames.put("connexions", "Connexions");
        tabNames.put("language", "Language");

        myAccountTab.setOnMouseClicked(event -> {
            System.out.println("My Account");
            loadTab("my-account");
            settingsViewTitle.setText(tabNames.get("my-account"));
        });
        preferenceTab.setOnMouseClicked(event -> {
            System.out.println("Preference");
            loadTab("preference");
            settingsViewTitle.setText(tabNames.get("preference"));
        });
        notificationsTab.setOnMouseClicked(event -> {
            System.out.println("Notifications");
            loadTab("notifications");
            settingsViewTitle.setText(tabNames.get("notifications"));
        });
        connexionsTab.setOnMouseClicked(event -> {
            System.out.println("Connexions");
            loadTab("connexions");
            settingsViewTitle.setText(tabNames.get("connexions"));
        });
        languageTab.setOnMouseClicked(event -> {
            System.out.println("Language");
            loadTab("language");
            settingsViewTitle.setText(tabNames.get("language"));
        });
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
}
