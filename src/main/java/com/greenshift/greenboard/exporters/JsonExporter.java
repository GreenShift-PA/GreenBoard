package com.greenshift.greenboard.exporters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.greenshift.greenboard.adapters.LocalDateTimeTypeAdapter;
import com.greenshift.greenboard.interfaces.IDumper;
import com.greenshift.greenboard.models.entities.*;
import com.greenshift.greenboard.services.*;
import com.greenshift.greenboard.singletons.ApplicationManager;
import com.greenshift.greenboard.singletons.SessionManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class JsonExporter extends BaseExporter implements IDumper {

    private Gson gson;
    private JsonArray jsonArray = new JsonArray();


    public JsonExporter() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();
    }

    @Override
    public void visit(User user) {
        if(user == null) return;


        jsonArray.add(gson.toJsonTree(user));
    }

    @Override
    public void visit(Activity activity) {
        jsonArray.add(gson.toJsonTree(activity));
    }

    @Override
    public void visit(Comment comment) {
        jsonArray.add(gson.toJsonTree(comment));
    }

    @Override
    public void visit(Notification notification) {
        jsonArray.add(gson.toJsonTree(notification));
    }

    @Override
    public void visit(Organization organization) {
        jsonArray.add(gson.toJsonTree(organization));
    }

    @Override
    public void visit(Project project) {
        jsonArray.add(gson.toJsonTree(project));
    }

    @Override
    public void visit(Role role) {
        jsonArray.add(gson.toJsonTree(role));
    }

    @Override
    public void visit(Tag tag) {
        jsonArray.add(gson.toJsonTree(tag));
    }

    @Override
    public void visit(Task task) {
        jsonArray.add(gson.toJsonTree(task));
    }

    @Override
    public void visit(TaskAttachment taskAttachment) {
        jsonArray.add(gson.toJsonTree(taskAttachment));
    }

    @Override
    public void visit(TaskStatus taskStatus) {
        jsonArray.add(gson.toJsonTree(taskStatus));
    }

    @Override
    public void visit(Team team) {
        jsonArray.add(gson.toJsonTree(team));
    }

    @Override
    public void visit(Token token) {
        jsonArray.add(gson.toJsonTree(token));
    }

    @Override
    public void visit(Trash trash) {
        jsonArray.add(gson.toJsonTree(trash));
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }


    public JsonArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JsonArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public String toString() {
        return "JsonExporter{" +
                "gson=" + gson +
                ", jsonObject=" + jsonArray +
                '}';
    }


    public static void main(String[] args) {
        JsonExporter jsonExporter = new JsonExporter();
        jsonExporter.setOutputFileName("test.json");
        SessionManager.getInstance().useDummyUser();
        UserService userService = new UserService();

        User user = SessionManager.getInstance().getCurrentUser();

        if(user == null) {
            System.out.println("User is null");
            return;
        }

        user.accept(jsonExporter);
        jsonExporter.export();
        System.out.println(jsonExporter.jsonArray);

        jsonExporter.clear();
        jsonExporter.setOutputFileName("test2.json");
        List<User> users = Arrays.stream(userService.getAll()).toList();

        for(User u : users) {
            u.accept(jsonExporter);
        }

        jsonExporter.export();
        System.out.println(jsonExporter.jsonArray);
    }

    @Override
    public String getOutput() {
        if(jsonArray.size() == 0) return "";
        if(jsonArray.size() == 1) return jsonArray.get(0).toString();

        return jsonArray.toString();
    }

    @Override
    public void clear() {
        jsonArray = new JsonArray();
    }

    @Override
    public String dump() {
        System.out.println("[JSON] Dumping all data...");

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

        System.out.println("[JSON] Dumping all data done.");

        return jsonObject.toString();
    }

    @Override
    public String getExtension() {
        return "json";
    }

    public static JsonArray dumpEntities(List<? extends BaseEntity> entities) {
        JsonExporter jsonExporter = new JsonExporter();
        for (BaseEntity e : entities) {
            e.accept(jsonExporter);
        }
        return jsonExporter.getJsonArray();
    }
}
