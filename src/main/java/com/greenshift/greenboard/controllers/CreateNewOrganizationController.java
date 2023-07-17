package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.models.entities.Organization;
import com.greenshift.greenboard.models.entities.Team;
import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.services.OrganizationService;
import com.greenshift.greenboard.services.TeamService;
import com.greenshift.greenboard.singletons.SceneManager;
import com.greenshift.greenboard.singletons.SessionManager;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

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

    private TextField newOrganizationNameTextField;


    public void initialize() {

        handleOptionEvent(teamOption, personalOption, teamIcon, personalIcon, "personal");
        handleOptionEvent(personalOption, teamOption, personalIcon, teamIcon, "team");

        cancel.setOnMouseClicked(event -> {
            SceneManager.getInstance().goBack();
        });

        continueButton.setOnAction(event -> {
            if (selectedOption.equals("personal") && currentStep.equals("type")) {
                currentStep = "name";
                loadContent("/fxml/create-new-organization-name.fxml", fxmlLoader -> {
                    CreateNewOrganizationNameController controller = fxmlLoader.getController();
                    newOrganizationNameTextField = controller.newOrganizationNameTextField;
                });
            } else if (currentStep.equals("name")) {
                currentStep = "invite";
                System.out.println("Invite");
                loadContent("/fxml/create-new-organization-invite.fxml", fxmlLoader -> {

                });
            } else {
                User currentUser = SessionManager.getInstance().getCurrentUser();

                OrganizationService organizationService = new OrganizationService();
                TeamService teamService = new TeamService();
                Team newTeam = new Team("Me", "Just me and me", "anto-user", null);
                newTeam = teamService.create(newTeam);

                List<User> members = new ArrayList<>();
                members.add(currentUser);
                newTeam.setMembers(members);
                Organization organization = new Organization(newOrganizationNameTextField.getText(), null, null, null);
                List<Team> teams = new ArrayList<>();
                teams.add(newTeam);
                organization.setTeams(teams);
                Organization createdOrganization = organizationService.create(organization);

                newTeam.setOrganization(createdOrganization);
                teamService.update(newTeam);

                if (createdOrganization != null) {

                    currentUser.setLastOrganization(createdOrganization);

                    SceneManager.getInstance().switchToScene("/fxml/main-view.fxml", null, null, scene -> {
                        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
                        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/kanban.css")).toExternalForm());
                        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/hierarchy.css")).toExternalForm());
                        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/settings.css")).toExternalForm());
                        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/organization.css")).toExternalForm());
                        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/popover.css")).toExternalForm());
                    });
                }
                System.out.println("Done");
            }
        });
    }

    private void handleOptionEvent(VBox firstOption, VBox secondOption, FontIcon firstIcon, FontIcon secondIcon, String optionName) {
        firstOption.setOnMouseClicked(event -> {
            if (selectedOption.equals(optionName)) return;

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

    private void loadContent(String fxml, Consumer<FXMLLoader> loaderConsumer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            VBox pane = loader.load();
            if (loaderConsumer != null) loaderConsumer.accept(loader);
            content.getChildren().clear();
            content.getChildren().add(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
