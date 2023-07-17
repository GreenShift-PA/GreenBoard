package com.greenshift.greenboard.services;

import com.google.gson.reflect.TypeToken;
import com.greenshift.greenboard.models.entities.Comment;
import com.greenshift.greenboard.models.entities.Task;
import com.greenshift.greenboard.models.entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommentService extends BaseCrudService<Comment> {

    public CommentService() {
        super("http://localhost:3000/api/v1/comments");
    }

    public static void main(String[] args) {
        CommentService commentService = new CommentService();
        TaskService taskService = new TaskService();
        UserService userService = new UserService();
        List<Task> allTasks = Arrays.stream(taskService.getAll()).toList();
        List<User> allUsers = Arrays.stream(userService.getAll()).toList();

        String commentId = "cb383b19-770f-4a1a-8b46-3ea753e1efa4";

        Comment comment = commentService.getById(commentId);
        System.out.println("Comment: " + comment);

        if (allUsers.size() > 0) {
            comment.setAuthor(allUsers.get(0));
        }
        if (allTasks.size() > 0) {
            comment.setTask(allTasks.get(0));
        }
        comment.setMentions(allUsers);
        comment.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam aliquam, nisl nisl aliquet nisl.");
        Comment updatedComment = commentService.update(comment);
        System.out.println("Updated Comment: " + updatedComment);

        comment.setContent("New Comment");
        comment.setId(null);
        Comment newComment = commentService.create(comment);
        System.out.println("New Comment: " + newComment);

        Comment[] comments = commentService.getAll();
        System.out.println("All Comments: " + Arrays.toString(comments));

        Comment deletedComment = commentService.delete(newComment.getId());
        System.out.println("Deleted Comment: " + deletedComment);
    }

    @Override
    protected String getEntityId(Comment entity) {
        return entity.getId();
    }

    @Override
    protected TypeToken<Comment> getTypeToken() {
        return new TypeToken<>() {
        };
    }

    @Override
    protected TypeToken<Comment[]> getArrayTypeToken() {
        return new TypeToken<>() {
        };
    }
}
