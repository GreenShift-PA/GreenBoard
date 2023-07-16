package com.greenshift.greenboard.cells;

import com.greenshift.greenboard.controllers.ProfilePopoverItemController;
import com.greenshift.greenboard.models.ui.ProfilePopoverItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ProfilePopoverItemCell extends ListCell<ProfilePopoverItem> {

    private ProfilePopoverItemController controller;

    public ProfilePopoverItemCell() {
    }

    @Override
    public void startEdit() {
        super.startEdit();
    }

    @Override
    protected void updateItem(ProfilePopoverItem item, boolean empty) {
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/profile-popover-item.fxml"));
            AnchorPane root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.init(getItem());

            setGraphic(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
