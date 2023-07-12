package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.services.AuthService;
import com.greenshift.greenboard.singletons.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import net.synedra.validatorfx.Check;
import net.synedra.validatorfx.TooltipWrapper;
import net.synedra.validatorfx.Validator;

import java.util.Objects;

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

    private final Validator validator = new Validator();

    private boolean showPassword = false;

    public void initialize() {

        Check emailCheck = validator.createCheck()
                .dependsOn("email", emailTextField.textProperty())
                .withMethod(c -> {
                    String email = c.get("email");
                    if (!email.contains("@")) {
                        c.error("Please enter a valid email address.");
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
        emailTextField.setOnKeyReleased(event -> emailCheck.recheck());

    }

    @FXML
    public void loginWithEmail() {
        System.out.println("Login with email");
        System.out.println("Email: " + emailTextField.getText());
        System.out.println("Password: " + passwordField.getText());


        boolean userExists = AuthService.userExists(emailTextField.getText());

        if (userExists) {
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

            // if validator contains no errors, then login
            if (showPassword && !validator.containsErrorsProperty().get()) {
                boolean authenticationSuccessful = AuthService.authenticate(emailTextField.getText(), passwordField.getText());
                if (authenticationSuccessful) {
                    SceneManager.getInstance().switchToScene("/fxml/main-view.fxml", null, scene -> {
                        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
                        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/kanban.css")).toExternalForm());
                    });
                } else {
                    System.out.println("Authentication failed");
                }
            }

            showPassword = true;
            passwordVBox.setVisible(true);
        } else {
            passwordVBox.setVisible(false);
            showPassword = false;
        }
    }
}
