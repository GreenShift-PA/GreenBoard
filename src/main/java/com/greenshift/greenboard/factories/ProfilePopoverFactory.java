package com.greenshift.greenboard.factories;

import com.greenshift.greenboard.cells.ProfilePopoverItemCell;
import com.greenshift.greenboard.models.ui.ProfilePopoverItem;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ProfilePopoverFactory implements Callback<ListView<ProfilePopoverItem>, ListCell<ProfilePopoverItem>> {

    @Override
    public ListCell<ProfilePopoverItem> call(ListView<ProfilePopoverItem> profilePopoverItemListView) {
        return new ProfilePopoverItemCell();
    }
}
