package com.greenshift.greenboard.converters;

import com.greenshift.greenboard.models.entities.User;

public class UserCheckComboBoxConverter extends javafx.util.StringConverter<User> {
    @Override
    public String toString(User user) {
        return user.getFirstName() + " " + user.getLastName() + " (" + user.getUsername() + ")";
    }

    @Override
    public User fromString(String s) {
        return null;
    }
}
