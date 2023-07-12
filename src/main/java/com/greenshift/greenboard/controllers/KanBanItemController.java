package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.models.ui.KanbanItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class KanBanItemController {
    @FXML
    public AnchorPane root;
    public HBox titleHBox;
    public FontIcon icon;
    public Label title;
    public HBox actionsHBox;
    public HBox assigneesHBox;
    public ImageView userPfp;
    public Label userDisplayName;
    public HBox projectHBox;
    public FontIcon projectIcon;
    public Label projectName;
    public HBox dueDateHBox;
    public Label dueDate;

    public void initialize() {
    }

    public void init(KanbanItem item) {
        if (item.getTitle() != null) {
            title.setText(item.getTitle());
        } else {
            title.setText("Untitled");
        }

        if (item.getIcon() != null) {
            icon.setIconLiteral(item.getIcon());
        } else {
            icon.setIconLiteral("fas-file");
        }

        if (item.getDueDate() != null) {
            dueDate.setText(item.getDueDate().toString());
        } else {
            dueDateHBox.setVisible(false);
            dueDateHBox.setManaged(false);
        }

        if (item.getProject() != null) {
            projectName.setText(item.getProject().getName());
            projectIcon.setIconLiteral(item.getProject().getIcon());
        } else {
            projectHBox.setVisible(false);
            projectHBox.setManaged(false);
        }

        if (item.getUsers() != null && item.getUsers().size() > 0) {
            userDisplayName.setText(item.getUsers().get(0).getFirstName() + " " + item.getUsers().get(0).getLastName());
            if (item.getUsers().get(0).getAvatar() != null) {
                Image image = new Image(item.getUsers().get(0).getAvatar());
                userPfp.setImage(image);
            }
        } else {
            assigneesHBox.setVisible(false);
            assigneesHBox.setManaged(false);
        }
    }
}
