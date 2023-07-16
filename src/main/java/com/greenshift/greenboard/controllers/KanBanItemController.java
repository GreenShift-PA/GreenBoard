package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.models.entities.Task;
import com.greenshift.greenboard.models.ui.KanbanItem;
import com.greenshift.greenboard.singletons.SceneManager;
import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

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

    public JFXDialog dialog;

    public StackPane context;


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

        root.setOnMouseClicked(e -> {
            if(SceneManager.getInstance().getMainController() != null) {
                context = SceneManager.getInstance().getMainController().context;
                dialog = SceneManager.getInstance().getMainController().dialog;
            }

            Task task = item.getTask();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/task-view.fxml"));
            try {
                VBox taskViewRoot = loader.load();
                TaskViewController controller = loader.getController();
                controller.initWithTask(task);
                dialog.setContent(taskViewRoot);
                dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
                dialog.show(context);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
