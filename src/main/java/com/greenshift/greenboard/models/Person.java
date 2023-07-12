package com.greenshift.greenboard.models;

import java.time.LocalDateTime;
import java.util.List;

public class Person {
    public String name;
    public int age;
    List<Person> friends;
    private LocalDateTime createdAt;

    public Person(String name, int age, List<Person> friends, LocalDateTime createdAt) {
        this.name = name;
        this.age = age;
        this.friends = friends;
        this.createdAt = createdAt;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, LocalDateTime createdAt) {
        this.name = name;
        this.age = age;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", friends=" + friends +
                ", createdAt=" + createdAt +
                '}';
    }

    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Getters and setters (or use lombok annotations)

}
