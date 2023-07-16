package com.greenshift.greenboard.controllers;

import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.models.ui.ProfilePopoverItem;
import com.greenshift.greenboard.services.UserService;
import com.greenshift.greenboard.singletons.SceneManager;
import com.greenshift.greenboard.singletons.SessionManager;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Objects;

public class ProfilePopoverItemController {
    public AnchorPane root;
    public FontIcon orderIcon;
    public FontIcon organizationIcon;
    public Label nameLabel;
    public Label infoLabel;

    public void init(ProfilePopoverItem item) {

        if(item.getIcon() != null){
            organizationIcon.setIconLiteral(item.getIcon());
        }

        if(item.getColor() != null && !item.getColor().isEmpty()){
            organizationIcon.setIconColor(Paint.valueOf(item.getColor()));
        }

        nameLabel.setText(item.getName());
        infoLabel.setText(item.getInfo());

        root.setOnMouseClicked(e -> {
            User currentUser = SessionManager.getInstance().getCurrentUser();

            if(currentUser == null) {
                return;
            }

            if(item.getOrganization() != null){
                currentUser.setLastOrganizationId(item.getOrganization().getId());
                UserService userService = new UserService("http://localhost:3000/api/v1/users");
                userService.update(currentUser, User.class);
                SessionManager.getInstance().refetchCurrentUser();

                SceneManager.getInstance().switchToScene("/fxml/main-view.fxml", null, null, scene -> {
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/kanban.css")).toExternalForm());
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/hierarchy.css")).toExternalForm());
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/settings.css")).toExternalForm());
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/organization.css")).toExternalForm());
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/popover.css")).toExternalForm());
                });
            }

        });
    }
}
