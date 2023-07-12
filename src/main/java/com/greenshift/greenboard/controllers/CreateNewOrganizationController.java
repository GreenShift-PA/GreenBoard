package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.singletons.SceneManager;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;

public class CreateNewOrganizationController {
    public AnchorPane root;

    public VBox content;
    public VBox teamOption;
    public VBox personalOption;
    public JFXButton continueButton;
    public FontIcon teamIcon;
    public FontIcon personalIcon;
    public Label cancel;

    // Fields
    private String selectedOption = "";
    private String currentStep = "type";


    public void initialize() {

        handleOptionEvent(teamOption, personalOption, teamIcon, personalIcon, "personal");
        handleOptionEvent(personalOption, teamOption, personalIcon, teamIcon, "team");

        cancel.setOnMouseClicked(event -> {
            SceneManager.getInstance().goBack();
        });

        continueButton.setOnAction(event -> {
            if (selectedOption.equals("personal") && currentStep.equals("type")) {
                currentStep = "name";
                loadContent("/fxml/create-new-organization-name.fxml");
            } else if (currentStep.equals("name")) {
                currentStep = "invite";
                System.out.println("Invite");
                loadContent("/fxml/create-new-organization-invite.fxml");
            } else {
                System.out.println("Done");
            }
        });
    }

    private void handleOptionEvent(VBox firstOption, VBox secondOption, FontIcon firstIcon, FontIcon secondIcon, String optionName) {
        firstOption.setOnMouseClicked(event -> {
            if(selectedOption.equals(optionName)) return;

            firstOption.getStyleClass().add("choice-card--selected");
            secondOption.getStyleClass().remove("choice-card--selected");

            firstIcon.setIconLiteral("fas-check-circle");
            firstIcon.setIconColor(Paint.valueOf("blue"));

            secondIcon.setIconLiteral("far-circle");
            secondIcon.setIconColor(Paint.valueOf("#000000"));

            continueButton.getStyleClass().remove("choice-continue-btn--disabled");

            selectedOption = optionName;
            continueButton.setDisable(false);
        });
    }

    private void loadContent(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            VBox pane = loader.load();
            content.getChildren().clear();
            content.getChildren().add(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
