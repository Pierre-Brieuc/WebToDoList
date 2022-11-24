package com.example.webtodolist;

public class Student extends User {
    public Student(int id_user, String username, String userPassword) {
        super(id_user, username, userPassword, "student");
    }

    public Student(String username, String userPassword) {
        super(username, userPassword, "student");
    }

    //Fonction voir liste
}
