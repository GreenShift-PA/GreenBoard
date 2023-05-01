package com.greenboard.factories;

import com.greenboard.models.Task;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class TaskCellFactory implements Callback<ListView<Task>, ListCell<Task>> {
    @Override
    public ListCell<Task> call(ListView<Task> param) {
        return new ListCell<Task>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);

                if (empty || task == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    VBox vbox = new VBox(5);
                    HBox hbox = new HBox(10);
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    /*ImageView avatar = new ImageView(new Image(task.getUserAvatar()));
                    avatar.setFitWidth(30);
                    avatar.setFitHeight(30);
                    hbox.getChildren().add(avatar);*/
                    Label title = new Label(task.getName());
                    hbox.getChildren().add(title);
                    vbox.getChildren().add(hbox);
                    Label status = new Label(task.getStatus().toString());
                    vbox.getChildren().add(status);
                    vbox.setAlignment(Pos.TOP_LEFT);
                    setGraphic(vbox);
                }
            }
        };
    }
}