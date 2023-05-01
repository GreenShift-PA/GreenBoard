package com.greenboard.factories;

import com.greenboard.models.Task;
import com.greenboard.controllers.TaskCardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TaskCardFactory extends ListCell<Task> {
    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);

        if (empty || task == null) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/greenboard/task-card.fxml"));
                Node card = loader.load();

                AnchorPane.setTopAnchor(card, 0.0);
                AnchorPane.setRightAnchor(card, 0.0);
                AnchorPane.setBottomAnchor(card, 0.0);
                AnchorPane.setLeftAnchor(card, 0.0);



                TaskCardController controller = loader.getController();
                controller.setName(task.getName());
                controller.setDescription(task.getDescription());
                controller.setStatus(task.getStatus().toString());
                controller.setUsers(task.getUsers());
                setGraphic(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
