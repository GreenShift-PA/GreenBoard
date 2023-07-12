package com.greenshift.greenboard.controllers.settings;

import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.services.UserService;
import com.greenshift.greenboard.singletons.SessionManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import net.synedra.validatorfx.Check;
import net.synedra.validatorfx.Validator;

import java.io.IOException;
import java.util.function.Consumer;


public class MyAccountController {
    public StackPane root;
    public TextField usernameTextField;
    public Label emailLabel;
    public JFXButton changeEmailBtn;
    public JFXButton changePasswordBtn;
    public JFXToggleButton setPermanentPasswordToggle;
    public JFXToggleButton set2FAToggle;
    public JFXToggleButton setSupportAccessToggle;
    public JFXButton logoutSessionsBtn;
    public JFXButton deleteAccountBtn;
    public JFXButton updateUsernameBtn;

    @FXML
    public JFXDialog dialog;
    public Label updateEmailLabel;
    public TextField updateEmailVerificationCodeTextfield;
    public TextField updateEmailNewEmailTextfield;
    public JFXButton updateEmailBtn;
    public TextField currentPasswordTextField;
    public TextField newPasswordTextField;
    public TextField confirmPasswordTextField;
    public JFXButton updatePasswordBtn;

    private StackPane context;

    private User currentUser;

    private final Validator validator = new Validator();

    public void initialize() {

        if (root != null) {
            context = root;
            root.getChildren().remove(dialog);
            // dialog.getContent().setPadding(new javafx.geometry.Insets(0, 0, 0, 0));
            currentUser = SessionManager.getInstance().getCurrentUser();
            if (currentUser == null) {
                System.out.println("MyAccountController.initialize: currentUser is null");
                return;
            }

            usernameTextField.setText(currentUser.getUsername());
            emailLabel.setText(hiddenEmail(currentUser.getEmail()));

            Check usernameCheck = validator.createCheck()
                    .dependsOn("username", usernameTextField.textProperty())
                    .withMethod(c -> {
                        String username = c.get("username");
                        if (username.length() < 3) {
                            c.error("Username must be at least 3 characters long.");
                        }
                    })
                    .decorates(usernameTextField);


            usernameTextField.setOnKeyReleased(event -> {
                if (event.getCode().toString().equals("ENTER")) {
                    System.out.println("MyAccountController.initialize: enter pressed");
                    root.requestFocus();
                }

                usernameCheck.recheck();

                updateUsernameBtn.setDisable(validator.containsErrorsProperty().get() || currentUser.getUsername().equals(usernameTextField.getText()));
            });

            changeEmailBtn.setOnAction(event -> {
                System.out.println("MyAccountController.initialize: changeEmailBtn clicked");

                openModal("/fxml/settings/update-email.fxml", loader -> {
                    MyAccountController controller = loader.getController();

                    updateEmailLabel = controller.updateEmailLabel;
                    updateEmailVerificationCodeTextfield = controller.updateEmailVerificationCodeTextfield;
                    updateEmailNewEmailTextfield = controller.updateEmailNewEmailTextfield;
                    updateEmailBtn = controller.updateEmailBtn;

                    updateEmailLabel.setText(hiddenEmail(currentUser.getEmail()));

                    updateEmailBtn.setOnAction(e -> {
                        System.out.println("MyAccountController.initialize: updateEmailBtn clicked");
                        UserService userService = new UserService("http://localhost:3000/api/v1/users");
                        currentUser.setEmail(updateEmailNewEmailTextfield.getText());
                        User updatedUser = userService.update(currentUser, User.class);

                        if (updatedUser == null) {
                            System.out.println("MyAccountController.initialize: updatedUser is null");
                            return;
                        }

                        SessionManager.getInstance().setCurrentUser(updatedUser);
                        currentUser = updatedUser;

                        updateEmailNewEmailTextfield.setText("");
                        updateEmailVerificationCodeTextfield.setText("");
                        dialog.close();
                    });
                });
            });


            changePasswordBtn.setOnAction(event -> {
                System.out.println("MyAccountController.initialize: changePasswordBtn clicked");

                openModal("/fxml/settings/update-password.fxml", loader -> {

                    MyAccountController controller = loader.getController();
                    newPasswordTextField = controller.newPasswordTextField;
                    confirmPasswordTextField = controller.confirmPasswordTextField;
                    currentPasswordTextField = controller.currentPasswordTextField;
                    updatePasswordBtn = controller.updatePasswordBtn;

                    updatePasswordBtn.setOnAction(e -> {
                        System.out.println("MyAccountController.initialize: updatePasswordBtn clicked");
                        UserService userService = new UserService("http://localhost:3000/api/v1/users");
                        currentUser.setPassword(newPasswordTextField.getText());
                        User updatedUser = userService.update(currentUser, User.class);

                        if (updatedUser == null) {
                            System.out.println("MyAccountController.initialize: updatedUser is null");
                            return;
                        }

                        SessionManager.getInstance().setCurrentUser(updatedUser);
                        currentUser = updatedUser;

                        currentPasswordTextField.setText("");
                        newPasswordTextField.setText("");
                        confirmPasswordTextField.setText("");
                        dialog.close();
                    });
                });
            });


            updateUsernameBtn.setOnAction(event -> updateUsername());
        }

    }

    private String hiddenEmail(String email) {

        if(email == null || email.length()  <= 3) {
            return email;
        }

        String[] emailParts = email.split("@");
        String hiddenEmail = emailParts[0].substring(0, 2);
        for (int i = 2; i < emailParts[0].length(); i++) {
            hiddenEmail += "*";
        }
        hiddenEmail += "@" + emailParts[1];
        return hiddenEmail;
    }

    private void updateUsername() {

        if (validator.containsErrorsProperty().get()) {
            System.out.println("MyAccountController.updateUsername: there are errors");
            return;
        }

        UserService userService = new UserService("http://localhost:3000/api/v1/users");
        currentUser.setUsername(usernameTextField.getText());
        User updatedUser = userService.update(currentUser, User.class);

        if (updatedUser == null) {
            System.out.println("MyAccountController.updateUsername: updatedUser is null");
            return;
        }

        SessionManager.getInstance().setCurrentUser(updatedUser);
        currentUser = updatedUser;
        updateUsernameBtn.setDisable(true);
    }

    public void openModal(String fxmlFile, Consumer<FXMLLoader> loaderConsumer) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            dialog.setContent(loader.load());
            dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);

            if (loaderConsumer != null) {
                loaderConsumer.accept(loader);
            }

            dialog.show(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
