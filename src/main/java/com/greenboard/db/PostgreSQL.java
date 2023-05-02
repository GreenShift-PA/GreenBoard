package com.greenboard.db;

import com.greenboard.enums.TaskPriority;
import com.greenboard.enums.TaskStatus;
import com.greenboard.models.Task;
import com.greenboard.models.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostgreSQL {
    public static final String JDBC_DRIVER = "org.postgresql.Driver";

    public static final String DB_URL = "jdbc:postgresql://localhost:54321/greenboard?stringtype=unspecified";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";

    private static final String AUTH_USER_QUERY = "SELECT * FROM users WHERE email = ? AND password = ?";

    public static boolean authenticate(String email, String password) {
        try (Connection conn = java.sql.DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            java.sql.PreparedStatement stmt = conn.prepareStatement(AUTH_USER_QUERY);
            stmt.setString(1, email);
            stmt.setString(2, password);
            java.sql.ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (java.sql.SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public static User getUserByEmail(String email) {
        try (Connection conn = java.sql.DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
            stmt.setString(1, email);
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("id"), rs.getString("email"), rs.getString("password_hash"), rs.getString("first_name"), rs.getString("last_name"));
            } else {
                return null;
            }
        } catch (java.sql.SQLException e) {
            printSQLException(e);
            return null;
        }
    }

    public static User getUserByUsername(String username) {
        try (Connection conn = java.sql.DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getString("first_name"),
                        rs.getString("last_name"));
            } else {
                return null;
            }
        } catch (java.sql.SQLException e) {
            printSQLException(e);
            return null;
        }
    }

    public static Task createTask(Task task) {
        try (Connection conn = java.sql.DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            java.sql.PreparedStatement stmt = conn.prepareStatement("INSERT INTO tasks (name, description, status) VALUES (?, ?, ?) RETURNING id");
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getStatus().name());

            java.sql.ResultSet rs = stmt.executeQuery();

            // get the result set
            if(rs.next()) {
                List<User> users = task.getUsers();
                task = getTaskById(UUID.fromString(rs.getString("id")));

                System.out.println("Task created with id " + task.getId());
                // add users to task
                if(users != null) {
                    for(User user : users) {
                        System.out.println("Adding user " + user.getId() + " to task " + task.getId());
                        stmt = conn.prepareStatement("INSERT INTO task_users (task_id, user_id) VALUES (?, ?)");
                        stmt.setString(1, task.getId());
                        stmt.setString(2, user.getId());
                        stmt.executeUpdate();
                    }
                }
            }

            return task;
        } catch (java.sql.SQLException e) {
            printSQLException(e);
            return null;
        }
    }

    private static List<User> getUsersByTaskId(UUID id) {
        try (Connection conn = java.sql.DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM task_users WHERE task_id = ?");
            stmt.setString(1, id.toString());
            java.sql.ResultSet rs = stmt.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(getUserById(UUID.fromString(rs.getString("user_id"))));
            }
            return users;
        } catch (java.sql.SQLException e) {
            printSQLException(e);
            return null;
        }
    }

    private static Task getTaskById(UUID id) {
        try (Connection conn = java.sql.DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tasks WHERE id = ?");
            stmt.setString(1, id.toString());
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                /*Task(String id, String name, String description, List<User> users, TaskStatus status, TaskPriority priority, LocalDate created_date, LocalDate due_date) {*/
                return new Task(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        getUsersByTaskId(id),
                        TaskStatus.fromString(rs.getString("status")),
                        TaskPriority.fromString(rs.getString("priority")),
                        rs.getDate("created_date").toLocalDate(),
                        rs.getDate("due_date") != null ? rs.getDate("due_date").toLocalDate() : null);
            } else {
                return null;
            }
        } catch (java.sql.SQLException e) {
            printSQLException(e);
            return null;
        }
    }

    public static User createUser(User user) {
        try (Connection conn = java.sql.DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            java.sql.PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (email, password_hash, first_name, last_name) VALUES (?, ?, ?, ?) RETURNING id");
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPasswordHash());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getLastName());
            java.sql.ResultSet rs = stmt.executeQuery();

            // get the result set
            if(rs.next()) {
                user = getUserById(UUID.fromString(rs.getString("id")));
            }

            return user;
        } catch (java.sql.SQLException e) {
            printSQLException(e);
            return null;
        }

    }

    public static User updateUser(User newUser) {
        // check if the user exists with the given id
        User oldUser = getUserById(newUser.getUserId());
        if (oldUser == null) {
            return null;
        }

        // update the user with the given id
        try (Connection conn = java.sql.DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            java.sql.PreparedStatement stmt = conn.prepareStatement("UPDATE users SET email = ?, password_hash = ?, first_name = ?, last_name = ? WHERE id = ?");
            stmt.setString(1, newUser.getEmail());
            stmt.setString(2, newUser.getPasswordHash());
            stmt.setString(3, newUser.getFirstName());
            stmt.setString(4, newUser.getLastName());
            stmt.setString(5, newUser.getId());
            stmt.executeUpdate();
            return newUser;
        } catch (java.sql.SQLException e) {
            printSQLException(e);
            return null;
        }
    }

    public static User getUserById(UUID userId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();

            // get the result set
            if(rs.next()) {
                return new User(rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getString("first_name"),
                        rs.getString("last_name"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            printSQLException(e);
            return null;
        }
    }

    public static void deleteUser(User newUser) {
        // check if the user exists with the given id
        User oldUser = getUserById(newUser.getUserId());
        if (oldUser == null) {
            return;
        }

        // delete the user with the given id
        try (Connection conn = java.sql.DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            java.sql.PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            stmt.setString(1, newUser.getId());
            stmt.executeUpdate();
        } catch (java.sql.SQLException e) {
            printSQLException(e);
        }
    }

    public static Task updateTask(Task t) {
        // check if the task exists with the given id
        Task oldTask = getTaskById(UUID.fromString(t.getId()));
        if (oldTask == null) {
            return null;
        }

        // update the task with the given id
        try (Connection conn = java.sql.DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            java.sql.PreparedStatement stmt = conn.prepareStatement("UPDATE tasks SET name = ?, description = ?, status = ?, priority = ?, created_date = ?, due_date = ? WHERE id = ?");
            stmt.setString(1, t.getName());
            stmt.setString(2, t.getDescription());
            stmt.setString(3, t.getStatus().name());
            stmt.setString(4, t.getPriority().name());
            stmt.setDate(5, java.sql.Date.valueOf(t.getCreatedDate()));
            stmt.setDate(6, t.getDueDate() != null ? java.sql.Date.valueOf(t.getDueDate()) : null);
            stmt.setString(7, t.getId());
            stmt.executeUpdate();
            return t;
        } catch (java.sql.SQLException e) {
            printSQLException(e);
            return null;
        }
    }

    public static List<Task> getTasksFromUserId(UUID id) {
        try (Connection conn = java.sql.DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tasks WHERE id IN (SELECT task_id FROM task_users WHERE user_id = ?)");
            stmt.setString(1, id.toString());
            java.sql.ResultSet rs = stmt.executeQuery();
            List<Task> tasks = new ArrayList<>();
            while (rs.next()) {
                tasks.add(getTaskById(UUID.fromString(rs.getString("id"))));
            }
            return tasks;
        } catch (java.sql.SQLException e) {
            printSQLException(e);
            return null;
        }
    }


    // create a new Task

}
