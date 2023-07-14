package com.greenshift.greenboard.controllers.settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;

public class LanguageController {
    public StackPane root;
    public ChoiceBox<String> languageChoiceBox;

    private ObservableList<String> languages;
    private String selectedLanguage;

    public void initialize()
    {
        languages = FXCollections.observableArrayList();

        languages.add("English");
        languages.add("French");
        languages.add("Spanish");

        languageChoiceBox.setItems(languages);

        selectedLanguage = (String) languageChoiceBox.getSelectionModel().getSelectedItem();

        languageChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected language: " + newValue);
        });
    }

    public void changeLanguage()
    {
        System.out.println("Language changed to: " + selectedLanguage);
    }
}
