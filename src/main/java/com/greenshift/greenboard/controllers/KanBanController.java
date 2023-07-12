package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.builders.CustomContextMenuBuilder;
import com.greenshift.greenboard.cells.KanbanItemCell;
import com.greenshift.greenboard.features.CustomContextMenu;
import com.greenshift.greenboard.models.entities.Task;
import com.greenshift.greenboard.models.entities.TaskStatus;
import com.greenshift.greenboard.models.ui.CustomContextMenuItem;
import com.greenshift.greenboard.models.ui.KanbanItem;
import com.greenshift.greenboard.services.TaskService;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class KanBanController {

    @FXML
    public HBox columns;

    public HashMap<TaskStatus, JFXListView<KanbanItem>> columnsListViews;
    public JFXListView<KanbanItem> notStartedListView;
    public JFXListView<KanbanItem> inProgressListView;
    public JFXListView<KanbanItem> doneListView;

    private JFXListView<KanbanItem> sourceList;

    private TaskService taskService;
    private String draggedItemId;
    private JFXListView<KanbanItem> draggedFrom;
    private Rectangle dragFeedback;

    public void initialize() {

        taskService = new TaskService("http://localhost:3000/api/v1/tasks");

        buildKanbanColumns();
    }

    private void setupDragAndDropSupport(JFXListView<KanbanItem> listView) {
        listView.setOnDragDetected(e -> {
            KanbanItem item = listView.getSelectionModel().getSelectedItem();
            if (item == null) {
                e.consume();
                return;
            }

            draggedItemId = item.getId();
            draggedFrom = listView;

            Dragboard dragboard = listView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(draggedItemId);
            dragboard.setContent(content);

            Label label = new Label(item.getTitle());
            label.setStyle("-fx-background-color: #3498db; -fx-text-fill: #fff");
            label.setTextFill(Color.WHITE);

            dragFeedback = new Rectangle(0, 0, listView.getWidth(), 10);
            dragFeedback.setOpacity(0.5);
            dragFeedback.setStyle("-fx-fill: #3498db");

            AnchorPane dragView = new AnchorPane();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/kanban-item.fxml"));
            loader.setControllerFactory(c -> new KanBanItemController());
            try {
                dragView = loader.load();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            dragboard.setDragView(dragView.snapshot(null, null));

            listView.startFullDrag();

            e.consume();
        });

        listView.setOnDragOver(e -> {
            Dragboard dragboard = e.getDragboard();
            if (dragboard.hasString()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        listView.setOnDragEntered(e -> {
            if (dragFeedback != null) {
                dragFeedback.setVisible(true);
            }

            listView.setStyle("-fx-background-color: #3498db");
            e.consume();
        });

        listView.setOnDragExited(e -> {
            if (dragFeedback != null) {
                dragFeedback.setVisible(false);
            }

            listView.setStyle("-fx-background-color: #fff");
            e.consume();
        });

        listView.setOnDragDropped(e -> {
            Dragboard dragboard = e.getDragboard();
            boolean success = false;

            JFXListView<KanbanItem> targetList = (JFXListView<KanbanItem>) e.getSource();
            ObservableList<KanbanItem> targetItems = targetList.getItems();
            ObservableList<KanbanItem> sourceItems = draggedFrom.getItems();

            if (targetList != draggedFrom) {
                KanbanItem item = draggedFrom.getItems().stream()
                        .filter(i -> i.getId().equals(draggedItemId))
                        .findFirst()
                        .orElse(null);


                if (item != null) {
                    sourceItems.remove(item);
                    targetItems.add(item);
                    success = true;
                }
            }

            // Refresh the source and target list views
            draggedFrom.setItems(FXCollections.observableArrayList(sourceItems));
            targetList.setItems(FXCollections.observableArrayList(targetItems));

            e.setDropCompleted(success);
            e.consume();
        });
    }

    private void setupContextMenu(JFXListView<KanbanItem> listView) {
        CustomContextMenu contextMenu = new CustomContextMenu();
        List<CustomContextMenuItem> customContextMenuItems = new ArrayList<>();

        CustomContextMenuItem addMenuItem = new CustomContextMenuBuilder()
                .setId("add")
                .setLabel("Ajouter un projet")
                .setLeftIcon("anto-plus")
                .setActive(true)
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


        contextMenu.buildMenuItems(customContextMenuItems);

        listView.setContextMenu(contextMenu);
    }

    private KanbanItem getCurrentSelectedItem() {
        JFXListView<KanbanItem> currentList = getCurrentList();
        if (currentList == null) return null;
        return currentList.getSelectionModel().getSelectedItem();
    }

    private JFXListView<KanbanItem> getCurrentList() {
        if (notStartedListView.isFocused()) {
            return notStartedListView;
        } else if (inProgressListView.isFocused()) {
            return inProgressListView;
        } else if (doneListView.isFocused()) {
            return doneListView;
        }
        return null;
    }

    private void buildKanbanColumns() {
        this.columnsListViews = new HashMap<>();

        List<Task> allTasks = Arrays.stream(taskService.getAll(Task[].class)).toList();
        // Group the tasks by their status name
        Map<TaskStatus, List<Task>> groupedTasks = allTasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus));

        // Create a JFXListView for each status and populate it with KanbanItems
        groupedTasks.forEach((status, tasks) -> {

            KanBanColumnController controller = null;
            JFXListView<KanbanItem> listView = null;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/kanban-column.fxml"));
            try {
                VBox column = loader.load();
                controller = loader.getController();
                listView = controller.listview;
                listView.setCellFactory(param -> new KanbanItemCell());

                columns.getChildren().add(column);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            setupDragAndDropSupport(listView);
            setupContextMenu(listView);


            // Create KanbanItems for each task in the status
            List<KanbanItem> kanbanItems = tasks.stream()
                    .map(task -> {
                        KanbanItem kanbanItem = KanbanItem.fromTask(task);
                        kanbanItem.setGroup(status.getName());
                        return kanbanItem;
                    })
                    .collect(Collectors.toList());

            // Add the KanbanItems to the ListView
            ObservableList<KanbanItem> kanbanItemsObservable = FXCollections.observableArrayList(kanbanItems);
            listView.setItems(kanbanItemsObservable);
            controller.init(status, listView.getItems().size());


            // Add the ListView to the HBox
            columnsListViews.put(status, listView);
        });

        columnsListViews.forEach((statusName, listView) -> {

        });

    }


}
