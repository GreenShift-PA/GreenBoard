package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.models.entities.TaskStatus;
import com.greenshift.greenboard.models.ui.KanbanItem;
import com.jfoenix.controls.JFXListView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class KanBanColumnController {
    public JFXListView<KanbanItem> listview;
    public HBox newHBox;
    public VBox column;
    public FontIcon icon;
    public Label label;
    public Label count;

    public void initialize() {
    }

    public void init(TaskStatus taskStatus, int count) {

        if(taskStatus.getName() != null)
            this.label.setText(taskStatus.getName());
        else
            this.label.setText("Untitled");

        if(taskStatus.getIcon() != null)
            this.icon.setIconLiteral(taskStatus.getIcon());

        if(taskStatus.getColor() != null)
            this.column.setStyle("-fx-background-color: " + taskStatus.getColor());

        this.count.setText(String.valueOf(count));
    }

    public void setListView(JFXListView listview) {
        this.listview = listview;
    }
}
