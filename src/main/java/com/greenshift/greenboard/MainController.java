package com.greenshift.greenboard;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class MainController {
    @FXML
    private Label welcomeText;
    @FXML
    private SplitPane split;

    private boolean isLeftMenuOpen = false;

    @FXML
    private TopBarController topBarController;

    @FXML
    protected void toggleLeftMenu() {
        if (isLeftMenuOpen) {
            split.setDividerPositions(0.0);
            isLeftMenuOpen = false;
        } else {
            split.setDividerPositions(0.2);
            isLeftMenuOpen = true;
        }
    }

    /* print a message on startup */
    public void initialize() {
        System.out.println("Application started!");
        split.setMinSize(0, 0);
    }

}