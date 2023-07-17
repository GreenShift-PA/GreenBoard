package com.greenshift.greenboard.models;


import com.greenshift.greenboard.services.UserService;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
    private String name;
    private int age;

    private Person spouse;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    public Person() {
        userService = new UserService();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
