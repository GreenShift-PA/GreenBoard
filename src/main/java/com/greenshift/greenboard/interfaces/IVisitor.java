package com.greenshift.greenboard.interfaces;

import com.greenshift.greenboard.models.entities.*;

public interface IVisitor {

    public void visit(User user);
    public void visit(Activity activity);
    public void visit(Comment comment);
    public void visit(Notification notification);
    public void visit(Organization organization);
    public void visit(Project project);
    public void visit(Role role);
    public void visit(Tag tag);
    public void visit(Task task);
    public void visit(TaskAttachment taskAttachment);
    public void visit(TaskStatus taskStatus);
    public void visit(Team team);
    public void visit(Token token);
    public void visit(Trash trash);

}
