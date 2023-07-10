package com.greenshift.greenboard.applications;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenshift.greenboard.adapters.LocalDateTimeTypeAdapter;
import com.greenshift.greenboard.models.Person;
import com.greenshift.greenboard.models.entities.User;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;

public class GSonExample {
    public static void main(String[] args) {
        String json = "{\n" +
                "  \"name\": \"John\",\n" +
                "  \"age\": 30,\n" +
                "  \"created\": \"2019-03-20T12:00:00.000Z\",\n" +
                "  \"friends\": [\n" +
                "    {\n" +
                "      \"name\": \"Jane\",\n" +
                "      \"age\": 28,\n" +
                "      \"created\": \"2019-03-20\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Jack\",\n" +
                "      \"age\": 27,\n" +
                "      \"created\": \"2019-03-20T12:00:00.000Z\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        String userJson = "{\n" +
                "  \"id\": \"5e7c7b3b9c6b4e0017b0b3b0\",\n" +
                "  \"name\": \"Abdou\",\n" +
                "  \"email\": \"test@test.com\",\n" +
                "  \"password\": \"test\",\n" +
                "  \"createdAt\": \"2020-03-26T12:00:00.000Z\",\n" +
                "  \"updatedAt\": \"2020-03-26T12:00:00.000Z\"\n" +
                "}";




        // Create a Gson instance
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();

        // Deserialize the JSON to a Person object
        Person person = gson.fromJson(json, Person.class);
        person.getFriends();

        User user = gson.fromJson(userJson, User.class);
        System.out.println(user);

        // Print the person object
        System.out.println(person);
    }
}
