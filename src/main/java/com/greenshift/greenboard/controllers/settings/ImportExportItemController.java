package com.greenshift.greenboard.controllers.settings;

import com.greenshift.greenboard.models.ui.ImportExportItem;
import com.greenshift.greenboard.singletons.SceneManager;
import com.jfoenix.controls.JFXDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;

public class ImportExportItemController {
    public FontIcon icon;
    public Label label;
    public FontIcon helpIcon;
    public HBox root;

    JFXDialog dialog;
    StackPane context;

    public void init(ImportExportItem item) {
        if (item.getIcon() != null && !item.getIcon().isEmpty()) {
            icon.setIconLiteral(item.getIcon());
        }

        if (item.getColor() != null && !item.getColor().isEmpty()) {
            icon.setIconColor(Paint.valueOf(item.getColor()));
        }

        if (item.getDescription() != null && !item.getDescription().isEmpty()) {
            helpIcon.setVisible(true);
            helpIcon.setOnMouseClicked(event -> {
                        if (context == null) {
                            context = SceneManager.getInstance().getMainController().context;
                        }

                        if (dialog == null) {
                            dialog = new JFXDialog(context, new Label(item.getDescription()), JFXDialog.DialogTransition.CENTER);
                        }

                        dialog.show();
                    }
            );
        }

        label.setText(item.getName());
    }
}
