package com.greenshift.greenboard.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.greenshift.greenboard.exporters.JsonExporter;
import com.greenshift.greenboard.models.entities.BaseEntity;
import com.greenshift.greenboard.services.*;
import com.greenshift.greenboard.singletons.ApplicationManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class StorageUtils {
    public static void dump() {
        System.out.println("Dumping all data...");

        String outputFileName = String.format("dump-%s.json", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")));

        CommentService commentService = new CommentService();
        OrganizationService organizationService = new OrganizationService();
        ProjectService projectService = new ProjectService();
        RoleService roleService = new RoleService();
        TagService tagService = new TagService();
        TaskService taskService = new TaskService();
        UserService userService = new UserService();

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("metadata", new JsonObject());

        jsonObject.get("metadata").getAsJsonObject().addProperty("name", ApplicationManager.getInstance().getName());
        jsonObject.get("metadata").getAsJsonObject().addProperty("author", ApplicationManager.getInstance().getAuthor());
        jsonObject.get("metadata").getAsJsonObject().addProperty("version", ApplicationManager.getInstance().getVersion().toString());

        jsonObject.add("data", new JsonObject());

        jsonObject.get("data").getAsJsonObject().add("users", dumpEntities( Arrays.stream(userService.getAll()).toList()));
        jsonObject.get("data").getAsJsonObject().add("comments", dumpEntities( Arrays.stream(commentService.getAll()).toList()));
        jsonObject.get("data").getAsJsonObject().add("organizations", dumpEntities( Arrays.stream(organizationService.getAll()).toList()));
        jsonObject.get("data").getAsJsonObject().add("projects", dumpEntities( Arrays.stream(projectService.getAll()).toList()));
        jsonObject.get("data").getAsJsonObject().add("roles", dumpEntities( Arrays.stream(roleService.getAll()).toList()));
        jsonObject.get("data").getAsJsonObject().add("tags", dumpEntities( Arrays.stream(tagService.getAll()).toList()));
        jsonObject.get("data").getAsJsonObject().add("tasks", dumpEntities( Arrays.stream(taskService.getAll()).toList()));

        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(outputFileName);
            fileWriter.write(jsonObject.toString());
            fileWriter.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        System.out.println("Dumping all data done.");
    }

    public static JsonArray dumpEntities(List<? extends BaseEntity> entities) {
        JsonExporter jsonExporter = new JsonExporter();
        for (BaseEntity e : entities) {
            e.accept(jsonExporter);
        }
        return jsonExporter.getJsonArray();
    }

    public static void main(String[] args) {
        dump();
    }

}
