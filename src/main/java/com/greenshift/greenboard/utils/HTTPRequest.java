package com.greenshift.greenboard.utils;

import com.google.gson.Gson;
import com.greenshift.greenboard.models.HTTPResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HTTPRequest {
    private static final int MAX_RESPONSE_LENGTH = 1024;
    protected final Gson gson;
    private final String url;
    private final String method;
    private final String requestBody;
    private final Map<String, String> headers;

    private HTTPRequest(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.requestBody = builder.requestBody;
        this.headers = builder.headers;
        this.gson = new Gson();
    }

    public HTTPResponse<String> sendRequest() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(method);

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        if (requestBody != null && !requestBody.isEmpty()) {
            byte[] postDataBytes = requestBody.getBytes(StandardCharsets.UTF_8);
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);
        }

        int responseCode = connection.getResponseCode();

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null && response.length() < MAX_RESPONSE_LENGTH) {
                response.append(line);
                response.append(System.lineSeparator());
            }
            reader.close();
            return new HTTPResponse<>(responseCode, "", response.toString());
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            return new HTTPResponse<>(responseCode, e.getMessage(), "");
        }
    }

    public static class Builder {
        private final String url;
        private final String method;
        private String requestBody;
        private Map<String, String> headers;

        public Builder(String url, String method) {
            this.url = url;
            this.method = method;
        }

        public Builder setRequestBody(String requestBody) {
            this.requestBody = requestBody;
            return this;
        }

        public Builder addHeader(String key, String value) {
            if (headers == null) {
                headers = new HashMap<>();
            }
            headers.put(key, value);
            return this;
        }

        public HTTPRequest build() {
            return new HTTPRequest(this);
        }
    }
}
