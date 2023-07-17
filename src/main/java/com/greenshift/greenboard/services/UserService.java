package com.greenshift.greenboard.services;

import com.google.gson.reflect.TypeToken;
import com.greenshift.greenboard.models.entities.User;

import java.util.ArrayList;

public class UserService extends BaseCrudService<User> {

    public UserService() {
        super("http://localhost:3000/api/v1/users");
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        String userId = "0c3a60af-b13e-40b8-ab58-edca130ec2b4";

        User user = userService.getById(userId);
        System.out.println("User: " + user);

/*
        user.setFirstName("Adia");
        user.setLastName("Dev");
        user.setUsername("adia-dev");
        User updatedUser = userService.update(user, User.class);
        System.out.println("Updated User: " + updatedUser);

        user.setFirstName("ThePrime");
        user.setLastName("Agen");
        user.setUsername("theprimeagen");
        user.setEmail("theprimeagen@gmail.com");
        user.setPassword("Respons11");
        user.setId(null);
        User newUser = userService.create(user, User.class);
        System.out.println("New User: " + newUser);

        User[] users = userService.getAll(User[].class);
        System.out.println("All Users: " + Arrays.toString(users));

        User deletedUser = userService.delete(newUser.getId(), User.class);
        System.out.println("Deleted User: " + deletedUser);
*/
    }

    @Override
    protected String getEntityId(User entity) {
        return entity.getId();
    }

    @Override
    protected TypeToken<User> getTypeToken() {
        return new TypeToken<>() {};
    }

    @Override
    protected TypeToken<User[]> getArrayTypeToken() {
        return new TypeToken<>() {};
    }
}
