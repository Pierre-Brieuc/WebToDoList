package com.example.webtodolist;

public class Instructor extends User {

    public Instructor(int id_user, String username, String userPassword) {
        super(id_user, username, userPassword, "instructor");
    }

    public Instructor(String username, String userPassword) {
        super(username, userPassword, "instructor");
    }

    // Fonction edit
    // Fonction Create
    // Fonction Delete
    // Fonction voir liste
}
