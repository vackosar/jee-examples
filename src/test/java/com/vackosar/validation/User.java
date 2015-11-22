package com.vackosar.validation;

public class User {

    public User(String email) {
        this.email = email;
    }

    @Email
    public String email;
}
