package com.greenshift.greenboard.interfaces;

import javafx.scene.Node;

@FunctionalInterface
public interface IContentLoadedCallback {
    void onContentLoaded(Node content);
}