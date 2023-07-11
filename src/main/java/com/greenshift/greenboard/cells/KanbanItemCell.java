package com.greenshift.greenboard.cells;

import com.greenshift.greenboard.controllers.KanBanItemController;
import com.greenshift.greenboard.models.ui.KanbanItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class KanbanItemCell extends ListCell<KanbanItem> {

    private KanBanItemController controller;

    public KanbanItemCell() {
    }

    @Override
    public void startEdit() {
        super.startEdit();
    }

    @Override
    protected void updateItem(KanbanItem item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(null);
            if (controller == null) {
                loadFXML();
            }
        }
    }

    private void loadFXML() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/kanban-item.fxml"));
            AnchorPane root = fxmlLoader.load();
            controller = fxmlLoader.getController();

            controller.init(getItem());

            setGraphic(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
