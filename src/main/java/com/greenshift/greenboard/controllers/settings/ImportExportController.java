package com.greenshift.greenboard.controllers.settings;

import com.greenshift.greenboard.exporters.JsonExporter;
import com.greenshift.greenboard.exporters.XmlExporter;
import com.greenshift.greenboard.interfaces.IDumper;
import com.greenshift.greenboard.models.ui.ImportExportItem;
import com.greenshift.greenboard.utils.StorageUtils;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import net.synedra.validatorfx.Check;
import net.synedra.validatorfx.Validator;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImportExportController {

    public FlowPane importExportOptionFlow;
    public TextField importExportPathTextField;
    public FontIcon selectDirectoryDialogIcon;
    public JFXButton exportButton;
    public Label errorMessageLabel;
    public Label statusMessageLabel;
    private List<ImportExportItem> importExportItems;
    private Node selectedImportExportItemNode;
    private ImportExportItem selectedImportExportItem;

    private Validator validator = new Validator();
    private IDumper dumper;

    String baseFilePath;

    private String dumpedContent;

    public void initialize() {

        importExportItems = new ArrayList<>();

        importExportItems.add(new ImportExportItem("Asana", "Import data with Asana", "mdi2g-google-drive", "#fa6b70"));
        importExportItems.add(new ImportExportItem("Json", "Import data with Json", "mdi2c-code-json", "#fa6b70", new JsonExporter()));
        importExportItems.add(new ImportExportItem("XML", "Import data with XML", "mdi2x-xml", "#7c7b75", new XmlExporter()));
        importExportItems.add(new ImportExportItem("Dropbox", "Import data with Dropbox", "mdi2d-dropbox", "#2466f6"));
        importExportItems.add(new ImportExportItem("Trello", "Import data with Trello", "mdi2t-trello", "#0279c0"));
        importExportItems.add(new ImportExportItem("Markdown", "Import data with Markdown", "mdi2l-language-markdown", "#fa6b70"));
        importExportItems.add(new ImportExportItem("Add import source", "Add a new importing source", "anto-plus", "#fa6b70"));

        Check pathCheck = validator.createCheck()
                .dependsOn("path", importExportPathTextField.textProperty())
                .withMethod(c -> {
                    String path = c.get("path");
                    if (path.isEmpty()) {
                        c.error("Please enter a valid path.");
                        errorMessageLabel.setText("Please enter a valid path.");
                    }else {
                        errorMessageLabel.setText("");
                    }

                })
                .decorates(importExportPathTextField)
                .immediate();

        importExportPathTextField.setOnKeyReleased(event -> {
            baseFilePath = importExportPathTextField.getText();
            pathCheck.recheck();
        });

        exportButton.disableProperty().bind(validator.containsErrorsProperty());

        exportButton.setOnAction(event -> {
            if(validator.containsErrors())
                return;

            tryDump();
        });

        selectDirectoryDialogIcon.setOnMouseClicked(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Directory");

            // Show the file dialog and wait for user selection
            Stage stage = (Stage) selectDirectoryDialogIcon.getScene().getWindow();
            File selectedDirectory = directoryChooser.showDialog(stage);

            if (selectedDirectory != null) {
                String path = selectedDirectory.getAbsolutePath();
                importExportPathTextField.setText(path);
                baseFilePath = path;
            }
        });

        buildImportExportOptionFlow();
    }

    private void tryDump() {
        errorMessageLabel.setText("");
        statusMessageLabel.setText("");

        if (selectedImportExportItem != null) {
            dumper = selectedImportExportItem.getDumper();
            if (dumper != null) {
                if(StorageUtils.dumpToFile(dumper, importExportPathTextField.getText())) {
                    System.out.println("Dumped to " + importExportPathTextField.getText());
                    statusMessageLabel.setText("Dumped to " + importExportPathTextField.getText());
                } else {
                    System.out.println("Failed to dump to " + importExportPathTextField.getText());
                    errorMessageLabel.setText("Failed to dump to " + importExportPathTextField.getText());
                }
            } else {
                System.out.println("No dumper found for " + selectedImportExportItem.getName());
                errorMessageLabel.setText("No dumper found for " + selectedImportExportItem.getName());
            }
        }
    }

    private void buildImportExportOptionFlow() {
        importExportItems.forEach(importExportItem -> {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/settings/import-export-item.fxml"));
            try {
                HBox root = fxmlLoader.load();
                root.setOnMouseClicked(event -> {
                    if (selectedImportExportItemNode != null) {
                        selectedImportExportItemNode.setStyle("-fx-background-color: white;");
                    }
                    selectedImportExportItemNode = root;
                    selectedImportExportItem = importExportItem;

                    selectedImportExportItemNode.setStyle(String.format("-fx-background-color: %s33;", importExportItem.getColor()));
                    maybeAddFilenameExtension();
                });
                ImportExportItemController controller = fxmlLoader.getController();
                controller.init(importExportItem);
                importExportOptionFlow.getChildren().add(root);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    public void maybeAddFilenameExtension() {
        if(importExportPathTextField.getText().isEmpty())
            return;

        if(selectedImportExportItem != null) {
            dumper = selectedImportExportItem.getDumper();
            if(dumper == null)
                return;
            String path = baseFilePath;
            if(!path.endsWith(dumper.getExtension())) {
                path +="." + dumper.getExtension();
                importExportPathTextField.setText(path);
            }
        }
    }
}
