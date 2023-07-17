package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.converters.UserCheckComboBoxConverter;
import com.greenshift.greenboard.models.entities.Team;
import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.services.TeamService;
import com.greenshift.greenboard.services.UserService;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import net.synedra.validatorfx.Check;
import net.synedra.validatorfx.Validator;
import org.controlsfx.control.CheckComboBox;

import java.util.Arrays;
import java.util.List;

public class CreateTeamController {

    private final Validator validator = new Validator();
    public TextField nameTextField;
    public ColorPicker colorPicker;
    public TextField colorTextField;
    public TextArea descriptionTextArea;
    public JFXButton createButton;
    public HBox learnButton;
    public CheckComboBox<User> userCheckComboBox;
    private List<User> users;

    public void initialize() {

        UserService userService = new UserService();
        users = Arrays.stream(userService.getAll()).toList();

        userCheckComboBox.setConverter(new UserCheckComboBoxConverter());
        userCheckComboBox.getItems().addAll(users);

        colorTextField.setText(formatColorPickerValue(colorPicker.getValue()));

        colorPicker.setOnAction(event -> {
            colorTextField.setText(formatColorPickerValue(colorPicker.getValue()));
        });

        colorTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (validateColor(newValue)) {
                colorPicker.setValue(Color.valueOf(newValue));
            }
        });

        createButton.disableProperty().bind(validator.containsErrorsProperty());

        Check nameCheck = validator.createCheck()
                .dependsOn("name", nameTextField.textProperty())
                .withMethod(c -> {
                    String name = c.get("name");
                    if (name.length() < 3) {
                        c.error("Name must be at least 3 characters long.");
                    }
                })
                .decorates(nameTextField);

        Check colorCheck = validator.createCheck()
                .dependsOn("color", colorTextField.textProperty())
                .withMethod(c -> {
                    String color = c.get("color");
                    if (!validateColor(color)) {
                        c.error("Please enter a valid color.");
                    }
                })
                .decorates(colorTextField);

        Check descriptionCheck = validator.createCheck()
                .dependsOn("description", descriptionTextArea.textProperty())
                .withMethod(c -> {
                    String description = c.get("description");
                    if (description.length() < 3) {
                        c.error("Description must be at least 3 characters long.");
                    }
                })
                .decorates(descriptionTextArea);

        nameTextField.setOnKeyPressed(event -> nameCheck.recheck());
        colorTextField.setOnKeyPressed(event -> colorCheck.recheck());
        descriptionTextArea.setOnKeyPressed(event -> descriptionCheck.recheck());

        createButton.setOnAction(event -> createTeam());
    }


    private boolean validateColor(String color) {
        return color.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    }

    private boolean createTeam() {

        if (!validator.validate()) {
            return false;
        }

        System.out.println("Creating team...");
        System.out.println("Name: " + nameTextField.getText());
        System.out.println("Color: " + colorTextField.getText());
        System.out.println("Description: " + descriptionTextArea.getText());
        System.out.println("Learn more: " + learnButton);

        Team newTeam = new Team(nameTextField.getText(), descriptionTextArea.getText(), "mdi2p-plus-circle", colorTextField.getText());
        newTeam.setMembers(userCheckComboBox.getCheckModel().getCheckedItems());
        TeamService teamService = new TeamService();
        Team createdTeam = teamService.create(newTeam);

        System.out.println(createdTeam);

        if (createdTeam != null) {
            System.out.println("Team created successfully.");
            return true;
        }

        System.out.println("Team creation failed.");

        return false;
    }

    private String formatColorPickerValue(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
