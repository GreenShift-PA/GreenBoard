package com.greenshift.greenboard.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class TreeItemViewController {

    @FXML
    public AnchorPane root;
    @FXML
    public FontIcon leftIcon;
    @FXML
    public FontIcon icon;
    @FXML
    public Label label;
    public TextField textField;
    @FXML
    public HBox actions;
    @FXML
    public FontIcon options;
    @FXML
    public FontIcon add;


    private static final String CHEVRON_DOWN_ICON_CODE = "fas-chevron-down";
    private static final String CHEVRON_RIGHT_ICON_CODE = "fas-chevron-right";
    private static final String CIRCLE_ICON_CODE = "fas-circle";
    private static final String ELLIPSIS_ICON_CODE = "fas-ellipsis-h";
    private static final String PLUS_ICON_CODE = "anto-plus";

    public void initialize() {
        actions.setVisible(false); // Hide actions by default
        root.setOnMouseEntered(this::handleMouseEntered);
        root.setOnMouseExited(this::handleMouseExited);
    }

    private void handleMouseEntered(MouseEvent event) {
        actions.setVisible(true);
    }

    private void handleMouseExited(MouseEvent event) {
        actions.setVisible(false);
    }

    public void init(String icon, String label, boolean hasChildren, boolean isExpanded) {
        if (hasChildren) {
            leftIcon.setIconLiteral(isExpanded ? CHEVRON_DOWN_ICON_CODE : CHEVRON_RIGHT_ICON_CODE);
            leftIcon.setIconSize(10);
        } else {
            leftIcon.setIconLiteral(CIRCLE_ICON_CODE);
            leftIcon.setIconSize(3);
        }

        if (icon == null) {
            this.icon.setVisible(false);
            this.icon.setIconSize(1);
        } else {
            this.icon.setVisible(true);
            this.icon.setIconSize(8);
            this.icon.setIconLiteral(icon);
        }

        if (label != null && this.label != null)
            this.label.setText(label);

        if (this.textField != null) {
            this.textField.setText(label);
        }
    }
}
