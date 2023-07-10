package com.greenshift.greenboard.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomContextMenuViewController implements Initializable {

    @FXML
    private HBox root;

    @FXML
    private FontIcon leftIcon;

    @FXML
    private Label menuItemLabel;

    @FXML
    private FontIcon rightIcon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization logic for the component
        leftIcon.setVisible(false);
        rightIcon.setVisible(false);
    }

    public void setLeftIcon(String glyphName) {
        if (glyphName == null)
            return;

        leftIcon.setIconLiteral(glyphName);
        leftIcon.setVisible(true);
    }

    public void setRightIcon(String glyphName) {
        if (glyphName == null)
            return;

        rightIcon.setIconLiteral(glyphName);
        rightIcon.setVisible(true);
    }

    public void setMenuItemLabel(String labelText) {
        menuItemLabel.setText(labelText);
        // I don't want ellipsis to show up in the label
        // If the text is too long, it should be wrapped and increase the height of the component
        if(labelText.length() > 20){
            menuItemLabel.setEllipsisString("");
            menuItemLabel.setPrefHeight(Float.MAX_VALUE);
            root.setPrefHeight(40);
        }
    }

    public void setLabelColor(String colorHex) {
        Color color = Color.web(colorHex);
        menuItemLabel.setTextFill(color);
    }

    public void setIconColor(String colorHex) {
        Color color = Color.web(colorHex);
        leftIcon.setFill(color);
        rightIcon.setFill(color);
    }

    public void setFontSize(double fontSize) {
        Font font = menuItemLabel.getFont();
        menuItemLabel.setFont(new Font(font.getName(), fontSize));
    }

    public void hideLeftIcon() {
        leftIcon.setVisible(false);
    }

    public void hideRightIcon() {
        rightIcon.setVisible(false);
    }

    public HBox getRoot() {
        return root;
    }
}
