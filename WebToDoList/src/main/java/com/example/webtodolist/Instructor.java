package com.example.webtodolist;

public class Instructor extends Account {

    public Instructor(int id_user, String username, String userPassword) {
        super(id_user, username, userPassword, "instructor");
    }

    public Instructor(String username, String userPassword) {
        super(username, userPassword, "instructor");
    }

    public String toString () {
        return "Instructor ["+getId_user()+
                ", "+getUsername()+
                ", "+getUserPassword()+
                ", "+getRole();
    }
}
