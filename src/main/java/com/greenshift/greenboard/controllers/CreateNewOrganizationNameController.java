package com.greenshift.greenboard.controllers;

import javafx.scene.control.TextField;
import net.synedra.validatorfx.Check;
import net.synedra.validatorfx.Validator;


public class CreateNewOrganizationNameController {
    public TextField newOrganizationNameTextField;


    public Validator validator = new Validator();

    public void initialize() {
        Check nameCheck = validator.createCheck()
                .dependsOn("name", newOrganizationNameTextField.textProperty())
                .withMethod(c -> {
                    String name = c.get("name");
                    if (name.length() < 3) {
                        c.error("Name must be at least 3 characters long");
                    }
                });

        newOrganizationNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            nameCheck.recheck();
        });
    }
}
