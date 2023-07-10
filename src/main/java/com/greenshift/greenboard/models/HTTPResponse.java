package com.greenshift.greenboard.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HTTPResponse<T> {
    private int statusCode;
    private String message;
    private T body;

    public HTTPResponse(int statusCode, String message, T body) {
        this.statusCode = statusCode;
        this.message = message;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static <T> HTTPResponse<T> fromJson(String json, Class<T> type) {
        Gson gson = new Gson();
        return (HTTPResponse<T>) gson.fromJson(json, new HTTPResponseType<T>(type));
    }

    @Override
    public String toString() {
        return "HTTPResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }

    private static class HTTPResponseType<T> extends TypeToken<T> {
        private Class<T> type;

        public HTTPResponseType(Class<T> type) {
            this.type = type;
        }
    }
}
