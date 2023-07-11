package com.greenshift.greenboard.factories.impl;

import com.greenshift.greenboard.builders.CustomContextMenuBuilder;
import com.greenshift.greenboard.controllers.TreeItemViewController;
import com.greenshift.greenboard.features.CustomContextMenu;
import com.greenshift.greenboard.models.ui.CustomContextMenuItem;
import com.greenshift.greenboard.models.ui.LeftMenuItem;
import com.jfoenix.controls.JFXTreeCell;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class LeftMenuItemCellImpl extends JFXTreeCell<LeftMenuItem> {

    private TextField textField;
    private final CustomContextMenu addMenu = new CustomContextMenu();
    TreeItemViewController controller = null;
    List<CustomContextMenuItem> customContextMenuItems = new ArrayList<>();

    public LeftMenuItemCellImpl() {
        CustomContextMenuItem addMenuItem = new CustomContextMenuBuilder()
                .setId("add")
                .setLabel("Ajouter un projet")
                .setLeftIcon("anto-plus")
                .setActive(true)
                .setAction(actionEvent -> {
                    TreeItem<LeftMenuItem> newLeftMenuItem = new TreeItem<>(new LeftMenuItem("New LeftMenuItem", null));
                    getTreeItem().getChildren().add(newLeftMenuItem);
                    if (!getTreeItem().isExpanded()) {
                        getTreeItem().setExpanded(true);
                    }
                    return null;
                })
                .build();

        CustomContextMenuItem deleteMenuItem = new CustomContextMenuBuilder()
                .setId("delete")
                .setLabel("Supprimer")
                .setLeftIcon("fas-trash-alt")
                .setActive(true)
                .setAction(item -> {
                    System.out.println("Trash clicked");
                    return null;
                })
                .build();

        CustomContextMenuItem favoriteMenuItem = new CustomContextMenuBuilder()
                .setId("favorite")
                .setLabel("Ajouter aux favoris")
                .setLeftIcon("far-star")
                .setActive(true)
                .setAction(item -> {
                    System.out.println("Trash clicked");
                    return null;
                })
                .build();

        CustomContextMenuItem duplicateMenuItem = new CustomContextMenuBuilder()
                .setId("duplicate")
                .setLabel("Dupliquer")
                .setLeftIcon("far-clone")
                .setActive(true)
                .setAction(item -> {
                    System.out.println("Trash clicked");
                    return null;
                })
                .build();

        CustomContextMenuItem linkMenuItem = new CustomContextMenuBuilder()
                .setId("link")
                .setLabel("Copier le lien")
                .setLeftIcon("fas-link")
                .setActive(true)
                .setAction(item -> {
                    System.out.println("Trash clicked");
                    return null;
                })
                .build();

        CustomContextMenuItem renameMenuItem = new CustomContextMenuBuilder()
                .setId("rename")
                .setLabel("Renommer")
                .setLeftIcon("far-edit")
                .setActive(true)
                .setSeparator(true)
                .setAction(item -> {
                    System.out.println("Trash clicked");
                    return null;
                })
                .build();

        CustomContextMenuItem moveMenuItem = new CustomContextMenuBuilder()
                .setId("rename")
                .setLabel("Déplacer vers")
                .setLeftIcon("mdi2s-share")
                .setActive(true)
                .setSeparator(true)
                .setAction(item -> {
                    System.out.println("Trash clicked");
                    return null;
                })
                .build();

        CustomContextMenuItem transformIntoMenuItem = new CustomContextMenuBuilder()
                .setId("item1")
                .setLabel("Transformer en")
                .setLeftIcon("mdi2t-twitter-retweet")
                .setActive(true)
                .setCategory("Settings")
                .setSeparator(true)
                .setAction(item -> {
                    System.out.println("Menu Item 1 clicked");
                    return null;
                })
                .addSubMenuItem(new CustomContextMenuBuilder()
                        .setLabel("Dark Theme")
                        .setLeftIcon("fas-moon")
                        .setAction(subItem -> {
                            System.out.println("Changing to dark theme");
                            return null;
                        })
                        .build())
                .addSubMenuItem(new CustomContextMenuBuilder()
                        .setLabel("Delete Account")
                        .setRightIcon("fas-times")
                        .setAction(subItem -> {
                            System.out.println("Deleting the account");
                            return null;
                        })
                        .build())
                .build();

        CustomContextMenuItem infoMenuItem = new CustomContextMenuBuilder()
                .setId("info")
                .setLabel("Dernière modification par Eh Eh 29 avr. 2020, 23:05")
                .setActive(true)
                .setAction(item -> {
                    System.out.println("Trash clicked");
                    return null;
                })
                .build();


        customContextMenuItems.add(addMenuItem);
        customContextMenuItems.add(deleteMenuItem);
        customContextMenuItems.add(favoriteMenuItem);
        customContextMenuItems.add(duplicateMenuItem);
        customContextMenuItems.add(linkMenuItem);
        customContextMenuItems.add(renameMenuItem);
        customContextMenuItems.add(moveMenuItem);
        customContextMenuItems.add(transformIntoMenuItem);
        customContextMenuItems.add(infoMenuItem);


        addMenu.buildMenuItems(customContextMenuItems);

        setOnDragDetected(this::handleDragDetected);
        setOnDragOver(this::handleDragOver);
        setOnDragDropped(this::handleDragDropped);
        setOnMouseClicked(this::handleMouseClicked);
    }


    @Override
    public void startEdit() {
        super.startEdit();

        loadFXML();

        if (textField == null) {
            createTextField();
        }
        setText(null);
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        loadFXML();

        setText(getItem().getName());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(LeftMenuItem item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
            } else {
                setText(null);
                loadFXML();

                if (!getTreeItem().isLeaf() && getTreeItem().getParent() != null) {
                    setContextMenu(addMenu);
                }

            }
        }
    }

    private void loadFXML() {
        try {
            String resourceName = isEditing() ? "/fxml/item-edit-view.fxml" : "/fxml/item-view.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceName));
            AnchorPane root = loader.load();
            controller = loader.getController();
            textField = controller.textField;

            if (getItem() != null && getTreeItem() != null)
                controller.init(getItem().getIconLiteral(), getItem().getName(), !getTreeItem().isLeaf(), getTreeItem().isExpanded());

            createTextField();
            setGraphic(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!getTreeItem().isLeaf() && getTreeItem().getParent() != null) {
            setContextMenu(addMenu);
        }
    }

    private void createTextField() {
        if (textField == null) {
            if (controller != null && controller.textField != null)
                textField = controller.textField;
            else
                return;
        }

        textField.setOnKeyReleased(t -> {
            if (t.getCode() == KeyCode.ENTER) {
                getItem().setName(textField.getText());

                commitEdit(getItem());
                cancelEdit();

                if (getGraphic() != null) {
                    getGraphic().requestFocus();
                }
            } else if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });

        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                commitEdit(getItem());
            }
        });
    }

    private void handleMouseClicked(MouseEvent event) {
        if (getItem() == null) {
            return;
        }

        getItem().onClick();
    }

    private void handleDragDetected(MouseEvent event) {
        if (getItem() == null) {
            return;
        }
        Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(getItem().getId());
        dragboard.setContent(content);
        event.consume();
    }

    private void handleDragOver(DragEvent event) {
        if (event.getGestureSource() != this && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    private void handleDragDropped(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;
        if (dragboard.hasString()) {
            TreeItem<LeftMenuItem> draggedItem = getTreeView().getRoot().getChildren().stream()
                    .filter(item -> item.getValue().getId().equals(dragboard.getString()))
                    .findFirst().orElse(null);
            TreeItem<LeftMenuItem> parentItem = getTreeItem();

            if (draggedItem != null && parentItem != null) {
                TreeItem<LeftMenuItem> oldParentItem = draggedItem.getParent();

                if (oldParentItem != null) {
                    oldParentItem.getChildren().remove(draggedItem);
                }

                parentItem.getChildren().add(draggedItem);
                draggedItem.setValue(parentItem.getValue());

                success = true;
            }
        }

        event.setDropCompleted(success);
        event.consume();
    }

    private String getString() {
        return getItem() == null ? "" : getItem().getName();
    }
}
