package com.greenshift.greenboard.features;

import com.greenshift.greenboard.controllers.CustomContextMenuViewController;
import com.greenshift.greenboard.models.ui.CustomContextMenuItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

import java.io.IOException;
import java.util.List;

public class CustomContextMenu extends ContextMenu {
    public CustomContextMenu() {
    }

    public CustomContextMenu(List<CustomContextMenuItem> customContextMenuItems) {
        super();
        buildMenuItems(customContextMenuItems);
    }


    public void buildMenuItems(List<CustomContextMenuItem> customContextMenuItems) {
        for (CustomContextMenuItem item : customContextMenuItems) {
            Menu menuItem = new Menu();
            loadMenuItemContent(menuItem, item);
            menuItem.setOnAction(event -> {
                if (item.getAction() != null) {
                    item.getAction().apply(item);
                }
            });
            getItems().add(menuItem);

            if (item.hasSeparator()) {
                getItems().add(new SeparatorMenuItem());
            }

            for (CustomContextMenuItem subItem : item.getSubMenuItems()) {
                MenuItem submenuItem = new MenuItem();
                loadMenuItemContent(submenuItem, subItem);
                submenuItem.setOnAction(event -> {
                    if (subItem.getAction() != null) {
                        subItem.getAction().apply(subItem);
                    }
                });
                menuItem.getItems().add(submenuItem);
            }
        }
    }

    private void loadMenuItemContent(MenuItem menuItem, CustomContextMenuItem item) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/context-menu-item-view.fxml"));
            Node menuItemContent = loader.load();
            CustomContextMenuViewController controller = loader.getController();
            controller.setMenuItemLabel(item.getLabel());
            controller.setLeftIcon(item.getLeftIcon());

            if (item.getRightIcon() == null && item.getSubMenuItems().size() > 0) {
                controller.setRightIcon("fas-chevron-right");
            } else {
                controller.setRightIcon(item.getRightIcon());
            }

            menuItem.setGraphic(menuItemContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
