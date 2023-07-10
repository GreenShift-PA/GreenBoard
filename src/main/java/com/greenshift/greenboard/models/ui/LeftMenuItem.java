package com.greenshift.greenboard.models.ui;

import com.greenshift.greenboard.interfaces.IHierarchicalItem;
import com.greenshift.greenboard.interfaces.IMenuItemCallback;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.util.Objects;
import java.util.UUID;

public class LeftMenuItem implements IHierarchicalItem {


    private final String id;
    private int index;
    private final SimpleStringProperty name;
    private String iconLiteral;


    private IMenuItemCallback callback;

    public LeftMenuItem(String id, String name, String iconLiteral) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.iconLiteral = iconLiteral;
    }

    public LeftMenuItem(String name, String iconLiteral) {
        this.id = UUID.randomUUID().toString();
        this.name = new SimpleStringProperty(name);
        this.iconLiteral = iconLiteral;
    }

    public LeftMenuItem(String name, String iconLiteral, IMenuItemCallback callback) {
        this.id = UUID.randomUUID().toString();
        this.name = new SimpleStringProperty(name);
        this.iconLiteral = iconLiteral;
        this.callback = callback;
    }

    @Override
    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String getName() {
        return name.get();
    }

    @Override
    public String getIconLiteral() {
        return iconLiteral;
    }

    @Override
    public void onClick() {
        if (this.callback != null)
            this.callback.execute(this);
    }

    @Override
    public IHierarchicalItem getParent() {
        return null;
    }

    @Override
    public ObservableList<IHierarchicalItem> getChildren() {
        return null;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setIconLiteral(String iconLiteral) {
        this.iconLiteral = iconLiteral;
    }

    public IMenuItemCallback getCallback() {
        return callback;
    }

    public void setCallback(IMenuItemCallback callback) {
        this.callback = callback;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LeftMenuItem)) return false;
        LeftMenuItem that = (LeftMenuItem) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
