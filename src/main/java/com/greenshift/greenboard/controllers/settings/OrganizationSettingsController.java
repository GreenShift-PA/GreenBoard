package com.greenshift.greenboard.controllers.settings;

import com.greenshift.greenboard.models.entities.Organization;
import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.services.OrganizationService;
import com.greenshift.greenboard.singletons.SceneManager;
import com.greenshift.greenboard.singletons.SessionManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import net.synedra.validatorfx.Check;
import net.synedra.validatorfx.Validator;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Objects;

public class OrganizationSettingsController {
    private final Validator validator = new Validator();
    private final OrganizationService organizationService = new OrganizationService();
    public TextField organizationNameTextField;
    public TextField organizationColorTextField;
    public ColorPicker organizationColorPicker;
    public JFXButton updateOrganizationNameButton;
    public JFXButton updateOrganizationColorButton;
    public FontIcon organizationIcon;
    public TextField organizationIconTextField;
    public ChoiceBox organizationManagerChoiceBox;
    public JFXButton deleteOrganizationButton;
    public JFXDialog dialog;
    public JFXButton updateOrganizationIconButton;
    boolean isIconValid = false;

    public void initialize() {
        User currentUser = SessionManager.getInstance().getCurrentUser();

        if (currentUser == null || currentUser.getLastTeam() == null || currentUser.getLastTeam().getOrganization() == null) {
            return;
        }

        Organization organization = currentUser.getLastTeam().getOrganization();

        organizationNameTextField.setText(organization.getName());
        organizationColorTextField.setText(organization.getColor());

        if (organization.getColor() != null) {
            organizationColorPicker.setValue(javafx.scene.paint.Color.valueOf(organization.getColor()));
            organizationIcon.setIconColor(Color.valueOf(organization.getColor()));
        }

        if (organization.getIcon() != null)
            organizationIcon.setIconLiteral(organization.getIcon());

        organizationIconTextField.setText(organization.getIcon());

        organizationColorPicker.setOnAction(event -> {
            organizationColorTextField.setText(formatColorPickerValue(organizationColorPicker.getValue()));
        });

        organizationColorTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (validateColor(newValue)) {
                organizationColorPicker.setValue(javafx.scene.paint.Color.valueOf(newValue));
            }
        });


        updateOrganizationNameButton.disableProperty().bind(validator.containsErrorsProperty());
        updateOrganizationColorButton.disableProperty().bind(validator.containsErrorsProperty());
        updateOrganizationIconButton.disableProperty().bind(validator.containsErrorsProperty());

        Check nameCheck = validator.createCheck()
                .dependsOn("name", organizationNameTextField.textProperty())
                .withMethod(c -> {
                    String name = c.get("name");
                    if (name.length() < 3) {
                        c.error("Name must be at least 3 characters long.");
                    }
                })
                .decorates(organizationNameTextField);

        Check colorCheck = validator.createCheck()
                .dependsOn("color", organizationColorTextField.textProperty())
                .withMethod(c -> {
                    String color = c.get("color");
                    if (!validateColor(color)) {
                        c.error("Please enter a valid color.");
                    } else {
                        organizationIcon.setIconColor(Color.valueOf(color));
                    }
                })
                .decorates(organizationColorTextField);

        Check iconCheck = validator.createCheck()
                .dependsOn("icon", organizationIconTextField.textProperty())
                .withMethod(c -> {
                    String icon = c.get("icon");
                    if (icon == null || icon.length() < 3 || !isIconValid) {
                        c.error("Icon must be at least 3 characters long and a valid ikonli FontIcon.");
                    }
                })
                .decorates(organizationIconTextField);

        organizationNameTextField.setOnKeyPressed(event -> nameCheck.recheck());
        organizationColorTextField.setOnKeyPressed(event -> colorCheck.recheck());
        organizationIconTextField.setOnKeyPressed(event -> iconCheck.recheck());

        organizationIconTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                organizationIcon.setIconLiteral(newValue);
                isIconValid = true;
            } catch (Exception e) {
                isIconValid = false;
            }
        });


        organizationColorPicker.setOnAction(event -> {
            organizationColorTextField.setText(formatColorPickerValue(organizationColorPicker.getValue()));
            organizationIcon.setIconColor(organizationColorPicker.getValue());
        });

        updateOrganizationNameButton.setOnAction(event -> {
            update();
        });

        updateOrganizationColorButton.setOnAction(event -> {
            update();
        });

        updateOrganizationIconButton.setOnAction(event -> {
            update();
        });

    }

    private boolean validateColor(String color) {
        return color.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    }

    private String formatColorPickerValue(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public void update() {
        User currentUser = SessionManager.getInstance().getCurrentUser();

        if (currentUser == null || currentUser.getLastTeam() == null || currentUser.getLastTeam().getOrganization() == null) {
            return;
        }

        // check if there are any errors
        if (validator.containsErrorsProperty().get()) {
            return;
        }

        Organization organization = currentUser.getLastTeam().getOrganization();

        organization.setName(organizationNameTextField.getText());
        organization.setColor(organizationColorTextField.getText());

        if (isIconValid)
            organization.setIcon(organizationIconTextField.getText());


        organizationService.update(organization);
        SessionManager.getInstance().refetchCurrentUser();

        SceneManager.getInstance().switchToScene("/fxml/main-view.fxml", null, null, scene -> {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/kanban.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/hierarchy.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/settings.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/organization.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/popover.css")).toExternalForm());
        });

    }
}
