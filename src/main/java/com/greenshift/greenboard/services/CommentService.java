package com.greenshift.greenboard.services;

import com.greenshift.greenboard.models.entities.Comment;
import com.greenshift.greenboard.models.entities.Task;
import com.greenshift.greenboard.models.entities.User;

import java.util.Arrays;
import java.util.List;

public class CommentService extends BaseCrudService<Comment> {

    public CommentService(String baseUrl) {
        super(baseUrl);
    }

    public static void main(String[] args) {
        CommentService commentService = new CommentService("http://localhost:3000/api/v1/comments");
        TaskService taskService = new TaskService("http://localhost:3000/api/v1/tasks");
        UserService userService = new UserService("http://localhost:3000/api/v1/users");
        List<Task> allTasks = Arrays.stream(taskService.getAll(Task[].class)).toList();
        List<User> allUsers = Arrays.stream(userService.getAll(User[].class)).toList();

        String commentId = "cb383b19-770f-4a1a-8b46-3ea753e1efa4";

        Comment comment = commentService.getById(commentId, Comment.class);
        System.out.println("Comment: " + comment);

        if (allUsers.size() > 0) {
            comment.setAuthor(allUsers.get(0));
        }
        if (allTasks.size() > 0) {
            comment.setTask(allTasks.get(0));
        }
        comment.setMentions(allUsers);
        comment.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam aliquam, nisl nisl aliquet nisl.");
        Comment updatedComment = commentService.update(comment, Comment.class);
        System.out.println("Updated Comment: " + updatedComment);

        comment.setContent("New Comment");
        comment.setId(null);
        Comment newComment = commentService.create(comment, Comment.class);
        System.out.println("New Comment: " + newComment);

        Comment[] comments = commentService.getAll(Comment[].class);
        System.out.println("All Comments: " + Arrays.toString(comments));

        Comment deletedComment = commentService.delete(newComment.getId(), Comment.class);
        System.out.println("Deleted Comment: " + deletedComment);
    }

    @Override
    protected String getEntityId(Comment entity) {
        return entity.getId();
    }
}
