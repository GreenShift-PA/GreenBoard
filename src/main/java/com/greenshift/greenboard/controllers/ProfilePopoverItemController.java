package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.models.ui.ProfilePopoverItem;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Objects;

public class ProfilePopoverItemController {
    public AnchorPane root;
    public FontIcon orderIcon;
    public FontIcon organizationIcon;
    public Label nameLabel;
    public Label infoLabel;

    public void init(ProfilePopoverItem item) {

        if(item.getIcon() != null)
            organizationIcon.setIconLiteral(item.getIcon());

        nameLabel.setText(item.getName());
        infoLabel.setText(item.getInfo());
    }
}
