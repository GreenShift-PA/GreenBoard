package com.greenshift.greenboard.controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.synedra.validatorfx.Check;
import net.synedra.validatorfx.TooltipWrapper;
import net.synedra.validatorfx.Validator;

public class AuthController {

    public Button loginWithGoogleBtn;
    public Button loginWithAppleBtn;

    public VBox credentialsVBox;
    public VBox emailVBox;
    public TextField emailTextField;
    public VBox passwordVBox;
    public PasswordField passwordField;
    public Button loginWithEmailBtn;

    public Label forgotPassword;

    private Validator validator = new Validator();

    private boolean showPassword = false;

    public void initialize() {

        Check emailCheck = validator.createCheck()
                .dependsOn("email", emailTextField.textProperty())
                .withMethod(c -> {
                    String email = c.get("email");
                    if (!email.contains("@")) {
                        c.error("Please enter a valid email address.");
                    }else{
                    }

                })
                .decorates(emailTextField);

        loginWithEmailBtn.disableProperty().bind(validator.containsErrorsProperty());

        TooltipWrapper<Button> loginWithEmailWrapper = new TooltipWrapper<>(
                loginWithEmailBtn,
                validator.containsErrorsProperty(),
                validator.createStringBinding()
        );

        passwordVBox.managedProperty().bind(passwordVBox.visibleProperty());
        passwordVBox.setVisible(false);

        credentialsVBox.getChildren().add(loginWithEmailWrapper);

        // when typing in email, recheck password
        emailTextField.setOnKeyReleased(event -> {
            emailCheck.recheck();
        });

    }

    @FXML
    public void loginWithEmail() {
        System.out.println("Login with email");
        System.out.println("Email: " + emailTextField.getText());
        System.out.println("Password: " + passwordField.getText());

        validator.createCheck()
                .dependsOn("password", passwordField.textProperty())
                .withMethod(c -> {
                    String password = c.get("password");
                    if (password.isEmpty()) {
                        c.error("Password is required.");
                    }
                })
                .decorates(passwordField)
                .immediate();


        passwordVBox.setVisible(true);
    }
}
