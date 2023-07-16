package com.greenshift.greenboard.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenshift.greenboard.adapters.LocalDateTimeTypeAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

public abstract class BaseCrudService<T> {

    protected static Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .create();
    protected final String BASE_URL;

    public BaseCrudService(String baseUrl) {
        this.BASE_URL = baseUrl;
    }

    public T getById(String id, Class<T> entityType) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                System.out.println(response);

                return gson.fromJson(response.toString(), entityType);
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        return null;
    }

    public T create(T entity, Class<T> entityType) {
        if (entity == null) {
            return null;
        }

        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String json = gson.toJson(entity);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(json.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();

            switch (responseCode) {

                case HttpURLConnection.HTTP_OK, HttpURLConnection.HTTP_CREATED -> {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();
                    return gson.fromJson(response.toString(), entityType);
                }
                default -> {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    System.out.println(response);
                    System.out.println("Error occurred: " + responseCode);
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        return null;
    }

    public T update(T entity, Class<T> entityType) {
        if (entity == null) {
            return null;
        }

        try {
            URL url = new URL(BASE_URL + "/" + getEntityId(entity));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String json = gson.toJson(entity);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(json.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();

            switch (responseCode) {

                case HttpURLConnection.HTTP_OK, HttpURLConnection.HTTP_CREATED -> {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();
                    return gson.fromJson(response.toString(), entityType);
                }
                default -> {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    System.out.println(response);
                    System.out.println("Error occurred: " + responseCode);
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        return null;
    }

    public T delete(String id, Class<T> entityType) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                return gson.fromJson(response.toString(), entityType);
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        return null;
    }

    public T[] getAll(Class<T[]> entityArrayType) {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                return gson.fromJson(response.toString(), entityArrayType);
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        return null;
    }

    protected abstract String getEntityId(T entity);
}
