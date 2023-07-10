package com.greenshift.greenboard.applications;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.synedra.validatorfx.TooltipWrapper;
import net.synedra.validatorfx.Validator;

public class ValidatorFXExample extends Application {

    private Validator validator = new Validator();

    @Override
    public void start(Stage primaryStage) throws Exception {

        TextField userTextField = new TextField();

        validator.createCheck()
                .dependsOn("username", userTextField.textProperty())
                .withMethod(c -> {
                    String userName = c.get("username");
                    if (!userName.toLowerCase().equals(userName)) {
                        c.error("Please use only lowercase letters.");
                    }
                })
                .decorates(userTextField)
                .immediate();
        ;

        Button signUp = new Button("Sign Up");
        signUp.disableProperty().bind(validator.containsErrorsProperty());


        TooltipWrapper<Button> signUpWrapper = new TooltipWrapper<>(
                signUp,
                validator.containsErrorsProperty(),
                Bindings.concat("Cannot sign up:\n", validator.createStringBinding())
        );

        GridPane grid = createGrid();
        grid.add(userTextField, 1, 1);
        grid.add(signUpWrapper, 1, 2);

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPrefSize(400,  200);
        return grid;
    }

    public static void main(String[] args) {
        launch();
    }
}