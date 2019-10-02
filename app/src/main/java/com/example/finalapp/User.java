package com.example.finalapp;

public class User {

    String name;
    String age;
    String Gender;
    String email;

    public User()
    {

    }

    public User(String name, String age, String gender, String email) {
        this.name = name;
        this.age = age;
        Gender = gender;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return Gender;
    }

    public String getEmail() {
        return email;
    }
}
