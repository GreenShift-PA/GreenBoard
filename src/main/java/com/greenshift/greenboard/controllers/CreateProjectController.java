package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.models.entities.Project;
import com.greenshift.greenboard.services.ProjectService;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import net.synedra.validatorfx.Check;
import net.synedra.validatorfx.Validator;

public class CreateProjectController {

    public TextField nameTextField;
    public ColorPicker colorPicker;
    public TextField colorTextField;
    public TextArea descriptionTextArea;
    public JFXButton createButton;
    public HBox learnButton;

    private final Validator validator = new Validator();

    public void initialize() {
        colorPicker.setOnAction(event -> {
            colorTextField.setText(formatColorPickerValue(colorPicker.getValue()));
        });

        colorTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (validateColor(newValue)) {
                colorPicker.setValue(javafx.scene.paint.Color.valueOf(newValue));
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

        createButton.setOnAction(event -> createProject());
    }


    private boolean validateColor(String color) {
        return color.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    }

    private boolean createProject() {

        if (!validator.validate()) {
            return false;
        }

        System.out.println("Creating team...");
        System.out.println("Name: " + nameTextField.getText());
        System.out.println("Color: " + colorTextField.getText());
        System.out.println("Description: " + descriptionTextArea.getText());
        System.out.println("Learn more: " + learnButton);

        Project newProject = new Project(nameTextField.getText(), descriptionTextArea.getText(), "mdi2p-plus-circle", colorTextField.getText());
        ProjectService projectService = new ProjectService("http://localhost:3000/api/v1/projects");
        Project createdProject = projectService.create(newProject, Project.class);

        if (createdProject != null) {
            System.out.println("Project created successfully.");
            return true;
        }

        System.out.println("Project creation failed.");

        return false;
    }

    private String formatColorPickerValue(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
