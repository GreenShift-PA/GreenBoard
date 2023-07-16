package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.models.entities.TaskStatus;
import com.greenshift.greenboard.models.ui.KanbanItem;
import com.greenshift.greenboard.singletons.SceneManager;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class KanBanColumnController {
    public StackPane root;
    public JFXListView<KanbanItem> listview;
    public HBox newHBox;
    public VBox column;
    public FontIcon icon;
    public Label label;
    public Label count;

    public StackPane context;
    public JFXDialog dialog;

    public TaskStatus taskStatus;


    public void initialize() {

        context = root;

        newHBox.setOnMouseClicked(e -> {
            if (SceneManager.getInstance().getMainController() != null)
                context = SceneManager.getInstance().getMainController().context;

            SceneManager.getInstance().setCurrentTaskStatus(taskStatus.getName());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/task-view.fxml"));
            try {
                VBox taskViewRoot = loader.load();
                TaskViewController controller = loader.getController();
                controller.init(taskStatus);
                dialog.setContent(taskViewRoot);
                dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
                dialog.show(context);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void init(TaskStatus taskStatus, int count) {

        if (taskStatus.getName() != null)
            this.label.setText(taskStatus.getName());
        else
            this.label.setText("Untitled");

        if (taskStatus.getIcon() != null)
            this.icon.setIconLiteral(taskStatus.getIcon());

        if (taskStatus.getColor() != null)
            this.column.setStyle("-fx-background-color: " + taskStatus.getColor());

        this.count.setText(String.valueOf(count));
    }

    public void setListView(JFXListView listview) {
        this.listview = listview;
    }
}
