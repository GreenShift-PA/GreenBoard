package com.greenshift.greenboard.applications;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class LabelTextExtractor {

    public static void main(String[] args) {
        String directoryPath = "/Users/abdoulayedia/Projects/Dev/Java/GreenShift/GreenBoard/src/main/resources/fxml";
        extractLabelTextFromDirectory(directoryPath);
    }

    public static void extractLabelTextFromDirectory(String directoryPath) {
        try {
            Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".fxml"))
                    .forEach(LabelTextExtractor::extractLabelTextFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extractLabelTextFromFile(Path filePath) {
        try {
            FXMLLoader loader = new FXMLLoader(LabelTextExtractor.class.getResource("/fxml/" + "auth-view.fxml"));
            Parent root = loader.load();

            traverseNode(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void traverseNode(javafx.scene.Node node) {
        if (node instanceof Label) {
            Label label = (Label) node;
            String labelText = label.getText();
            System.out.println(labelText);
        }

        if (node instanceof Parent) {
            Parent parent = (Parent) node;
            parent.getChildrenUnmodifiable().forEach(LabelTextExtractor::traverseNode);
        }
    }
}
