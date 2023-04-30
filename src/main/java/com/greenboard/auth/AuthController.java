package com.greenboard.auth;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;
import com.greenboard.db.PostgreSQL;

import java.security.Key;

public class AuthController {
    @FXML
    private TextField emailField;
    @FXML
    private Label emailFieldError;
    private static final String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

    @FXML
    private TextField passwordField;
    @FXML
    private Label passwordFieldError;

    @FXML
    private Button submitButton;

    @FXML
    protected void OnLogin() {
        System.out.println("Login");

        String email = emailField.getText();
        String password = passwordField.getText();

        // clear the error messages
        emailFieldError.setText("");
        passwordFieldError.setText("");

        // check if the fields are empty, if so, display an error message and focus on the empty field
        if (email.isEmpty()) {
            // showAlert(Alert.AlertType.ERROR, submitButton.getScene().getWindow(), "Login Error!", "Please enter an email");
            emailFieldError.setText("Please enter an email");
            emailField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            // showAlert(Alert.AlertType.ERROR, submitButton.getScene().getWindow(), "Login Error!", "Please enter a password");
            passwordFieldError.setText("Please enter a password");
            passwordField.requestFocus();
            return;
        }

        // check if the email is valid, if not, display an error message and focus on the email field
        if (!email.matches(emailRegex)) {
            // showAlert(Alert.AlertType.ERROR, submitButton.getScene().getWindow(), "Login Error!", "Invalid email");
            emailFieldError.setText("Invalid email");
            emailField.requestFocus();
            return;
        }

        if(PostgreSQL.authenticate(email, password))
        {
            System.out.println("Login successful");
            showAlert(Alert.AlertType.INFORMATION, submitButton.getScene().getWindow(), "Login Successful!", "Welcome " + email);
        }
        else
        {
            System.out.println("Login failed");
            showAlert(Alert.AlertType.ERROR, submitButton.getScene().getWindow(), "Login Error!", "Invalid email or password");
        }
    }

    @FXML
    // onKeyPressed event trigger => maybeSubmit, if enter key is pressed
    protected void maybeSubmit(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            OnLogin();
        }
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
