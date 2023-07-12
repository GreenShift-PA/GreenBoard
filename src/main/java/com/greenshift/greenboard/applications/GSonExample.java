package com.greenshift.greenboard.applications;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenshift.greenboard.adapters.LocalDateTimeTypeAdapter;
import com.greenshift.greenboard.models.Person;
import com.greenshift.greenboard.models.entities.User;

import java.time.LocalDateTime;

public class GSonExample {
    public static void main(String[] args) {
        String json = """
                {
                  "name": "John",
                  "age": 30,
                  "created": "2019-03-20T12:00:00.000Z",
                  "friends": [
                    {
                      "name": "Jane",
                      "age": 28,
                      "created": "2019-03-20"
                    },
                    {
                      "name": "Jack",
                      "age": 27,
                      "created": "2019-03-20T12:00:00.000Z"
                    }
                  ]
                }""";

        String userJson = """
                {
                  "id": "5e7c7b3b9c6b4e0017b0b3b0",
                  "name": "Abdou",
                  "email": "test@test.com",
                  "password": "test",
                  "createdAt": "2020-03-26T12:00:00.000Z",
                  "updatedAt": "2020-03-26T12:00:00.000Z"
                }""";


        // Create a Gson instance
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();

        // Deserialize the JSON to a Person object
        Person person = gson.fromJson(json, Person.class);

        User user = gson.fromJson(userJson, User.class);
        System.out.println(user);

        // Print the person object
        System.out.println(person);
    }
}
