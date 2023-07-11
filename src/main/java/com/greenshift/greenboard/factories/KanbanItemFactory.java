package com.greenshift.greenboard.factories;

import com.greenshift.greenboard.cells.KanbanItemCell;
import com.greenshift.greenboard.models.ui.KanbanItem;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class KanbanItemFactory implements Callback<ListView<KanbanItem>, ListCell<KanbanItem>> {

    @Override
    public ListCell<KanbanItem> call(ListView<KanbanItem> kanbanItemListView) {
        return new KanbanItemCell();
    }
}
