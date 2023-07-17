package com.greenshift.greenboard.interfaces;

import com.greenshift.greenboard.models.entities.*;

public interface IVisitor {

    void visit(User user);
    void visit(Activity activity);
    void visit(Comment comment);
    void visit(Notification notification);
    void visit(Organization organization);
    void visit(Project project);
    void visit(Role role);
    void visit(Tag tag);
    void visit(Task task);
    void visit(TaskAttachment taskAttachment);
    void visit(TaskStatus taskStatus);
    void visit(Team team);
    void visit(Token token);
    void visit(Trash trash);

}
