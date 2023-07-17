package com.greenshift.greenboard.exporters;

import com.greenshift.greenboard.interfaces.IDumper;
import com.greenshift.greenboard.models.entities.*;
import com.greenshift.greenboard.services.*;
import com.greenshift.greenboard.singletons.ApplicationManager;
import com.greenshift.greenboard.singletons.SessionManager;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class XmlExporter extends BaseExporter implements IDumper {


    private JAXBContext jaxbContext;
    Marshaller marshaller;
    StringWriter writer;
    StringBuilder rootXml;

    public XmlExporter() {
        writer = new StringWriter();
        rootXml = new StringBuilder();
    }

    @Override
    public void visit(User user) {
        if (user == null) return;
        visitXMLEntity(user);
    }

    @Override
    public void visit(Activity activity) {
        visitXMLEntity(activity);
    }

    @Override
    public void visit(Comment comment) {
        visitXMLEntity(comment);
    }

    @Override
    public void visit(Notification notification) {
        visitXMLEntity(notification);
    }

    @Override
    public void visit(Organization organization) {
        visitXMLEntity(organization);
    }

    @Override
    public void visit(Project project) {
        visitXMLEntity(project);
    }

    @Override
    public void visit(Role role) {
        visitXMLEntity(role);
    }

    @Override
    public void visit(Tag tag) {
        visitXMLEntity(tag);
    }

    @Override
    public void visit(Task task) {
        visitXMLEntity(task);
    }

    @Override
    public void visit(TaskAttachment taskAttachment) {
        visitXMLEntity(taskAttachment);
    }

    @Override
    public void visit(TaskStatus taskStatus) {
        visitXMLEntity(taskStatus);
    }

    @Override
    public void visit(Team team) {
        visitXMLEntity(team);
    }

    @Override
    public void visit(Token token) {
        visitXMLEntity(token);
    }

    @Override
    public void visit(Trash trash) {
        visitXMLEntity(trash);
    }


    private void visitXMLEntity(BaseEntity entity) {
        try {
            jaxbContext = JAXBContext.newInstance(entity.getClass());
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(entity, writer);
            String content = removeHeader(writer.toString());
            rootXml.append(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String wrapWith(String tag, String content) {
        return "<" + tag + ">" + content + "</" + tag + ">";
    }

    private String wrapWith(String tag, String content, HashMap<String, String> attributes) {
        StringBuilder builder = new StringBuilder();
        builder.append("<").append(tag);
        for (String key : attributes.keySet()) {
            builder.append(" ").append(key).append("=\"").append(attributes.get(key)).append("\"");
        }
        builder.append(">").append(content).append("</").append(tag).append(">");
        return builder.toString();
    }

    private String removeHeader(String xml) {
        if (xml.contains("<?xml")) {
            System.out.println("Removing header...");
            return xml.substring(xml.indexOf("\n") + 1);
        }
        return xml;
    }

    private String createHeader() {
        StringBuilder header = new StringBuilder();
        header.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        header.append("<metadata>\n");
        header.append("\t<generated>").append(System.currentTimeMillis()).append("</generated>\n");
        header.append("\t<generator>").append(ApplicationManager.getInstance().getName()).append("</generator>\n");
        header.append("\t<version>").append(ApplicationManager.getInstance().getVersion().toString()).append("</version>\n");
        header.append("\t<author>").append(ApplicationManager.getInstance().getAuthor()).append("</author>\n");
        header.append("</metadata>\n");

        return header.toString();
    }

    public static void main(String[] args) {
        XmlExporter xmlExporter = new XmlExporter();
        SessionManager.getInstance().useDummyUser();
        User user = SessionManager.getInstance().getCurrentUser();
        user.accept(xmlExporter);
        System.out.println(xmlExporter.getOutput());
    }

    @Override
    public String getOutput() {
        return rootXml.toString();
    }

    @Override
    public void clear() {
        rootXml = new StringBuilder();
    }

    @Override
    public String dump() {
        System.out.println("[XML] Dumping all data...");

        CommentService commentService = new CommentService();
        OrganizationService organizationService = new OrganizationService();
        ProjectService projectService = new ProjectService();
        RoleService roleService = new RoleService();
        TagService tagService = new TagService();
        TaskService taskService = new TaskService();
        UserService userService = new UserService();

        rootXml.append(wrapWith("users", dumpEntities(Arrays.stream(userService.getAll()).toList())));
        rootXml.append(wrapWith("comments", dumpEntities(Arrays.stream(commentService.getAll()).toList())));
        rootXml.append(wrapWith("organizations", dumpEntities(Arrays.stream(organizationService.getAll()).toList())));
        rootXml.append(wrapWith("projects", dumpEntities(Arrays.stream(projectService.getAll()).toList())));
        rootXml.append(wrapWith("roles", dumpEntities(Arrays.stream(roleService.getAll()).toList())));
        rootXml.append(wrapWith("tags", dumpEntities(Arrays.stream(tagService.getAll()).toList())));
        rootXml.append(wrapWith("tasks", dumpEntities(Arrays.stream(taskService.getAll()).toList())));


        System.out.println("[XML] Dumping all data done.");

        rootXml.insert(0, createHeader());

        return rootXml.toString();
    }

    @Override
    public String getExtension() {
        return "xml";
    }

    public static String dumpEntities(List<? extends BaseEntity> entities) {
        XmlExporter xmlExporter = new XmlExporter();

        for (BaseEntity e : entities) {
            e.accept(xmlExporter);
        }

        return xmlExporter.getOutput();
    }
}
