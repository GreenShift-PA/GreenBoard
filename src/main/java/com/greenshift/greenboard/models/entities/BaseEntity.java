package com.greenshift.greenboard.models.entities;

import com.greenshift.greenboard.interfaces.IVisitable;
import com.greenshift.greenboard.interfaces.IVisitor;

public abstract class BaseEntity implements IVisitable {
    protected String id;

    public BaseEntity() {
    }
    public BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        this.id = id;
    }
}
