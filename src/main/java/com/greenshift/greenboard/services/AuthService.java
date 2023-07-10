package com.greenshift.greenboard.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenshift.greenboard.adapters.LocalDateTimeTypeAdapter;
import com.greenshift.greenboard.exceptions.AuthenticationFailedException;
import com.greenshift.greenboard.models.HTTPResponse;
import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.singletons.UserSession;
import com.greenshift.greenboard.utils.HTTPRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class AuthService {

    public static final String AUTH_URL = "http://localhost:3000/api/v1/auth/authenticate/email";

    public static boolean authenticate(String email, String password)  {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);

        HTTPRequest httpRequest = new HTTPRequest.Builder(AUTH_URL, "POST")
                .setRequestBody(gson.toJson(requestBody))
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            HTTPResponse<String> response = httpRequest.sendRequest();

            if (response.getStatusCode() == 200) {
                HashMap<String, String> responseBody = gson.fromJson(response.getBody(), HashMap.class);
                System.out.println(responseBody.get("token"));

                return true;
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        return false;
    }

    public static User fetchUser(String token) {
        String url = "http://localhost:3000/api/v1/auth/me";

        HTTPRequest httpRequest = new HTTPRequest.Builder(url, "GET")
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try {
            HTTPResponse<String> response = httpRequest.sendRequest();

            if (response.getStatusCode() == 200) {

                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                        .create();

                HashMap<String, String> responseBody = gson.fromJson(response.getBody(), HashMap.class);
                String userJson = gson.toJson(responseBody.get("user"));
                User user = gson.fromJson(userJson, User.class);

                if(user == null) {
                    throw new AuthenticationFailedException();
                }

                UserSession.getInstance().setCurrentUser(user);

                return user;
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        return null;
    }

    public static boolean userExists(String email) {
        if(email == null || email.isEmpty()) {
            return false;
        }

        HTTPRequest httpRequest = new HTTPRequest.Builder("http://localhost:3000/api/v1/users?email=" + email, "GET")
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            HTTPResponse<String> response = httpRequest.sendRequest();
            return response.getStatusCode() == 200;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        boolean authenticationSucceeded = AuthService.authenticate("abdoudu78130@gmail.com", "Respons11");

        boolean userExists = AuthService.userExists("admin@mail.com");

        User fetchedUser = AuthService.fetchUser("eyJhbGciOiJSUzI1NiIsImtpZCI6ImE1MWJiNGJkMWQwYzYxNDc2ZWIxYjcwYzNhNDdjMzE2ZDVmODkzMmIiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vZ2YtZ3JlZW5ib2FyZCIsImF1ZCI6ImdmLWdyZWVuYm9hcmQiLCJhdXRoX3RpbWUiOjE2ODkwMDA3NTksInVzZXJfaWQiOiJ0QjhzSGxlM3lHZlNmRGRqc3FSTVNNY1Z3THAxIiwic3ViIjoidEI4c0hsZTN5R2ZTZkRkanNxUk1TTWNWd0xwMSIsImlhdCI6MTY4OTAwMDc1OSwiZXhwIjoxNjg5MDA0MzU5LCJlbWFpbCI6ImFkbWluQG1haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImVtYWlsIjpbImFkbWluQG1haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.ZCoqG3yHy_R9jzBqLiOEKCxY7WmUDlzcSeCiMEBqh6h--ur33B6bGJVACzrcV0G_GsuGR5665yOwHKn5NSA1wSpS3QH0ne0rxzaX6f4uYNfNOwqq57qSdjtEWF3LrqPyL3HU7IygzRr1JQE9KZVBmj0nqxJz-BX-61iP1dZCwfpWetEWSgN35bbfA56lkzmfLEtV_jx9HQm7_aMa5aW1j83QylXYCZGgz3cD42P1_b0HZa3OkS5pIUHQ6LMuas6RFp2BdpYwnqPgea6bymfEvz7cwernbpYWPnnO-350TNQB5UdHgKq0wiYOGgBfbOuuZUikfUvNMn8bX48zid2wjA");
        System.out.println("Fetched user: " + fetchedUser);

        System.out.println("User exists: " + userExists);

        System.out.println("Authentication: " + authenticationSucceeded);

        System.out.println("Session: " + UserSession.getInstance());

    }

}
