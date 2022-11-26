package com.example.webtodolist;

public class Student extends Account {
    public Student(int id_user, String username, String userPassword) {
        super(id_user, username, userPassword, "student");
    }

    public Student(String username, String userPassword) {
        super(username, userPassword, "student");
    }

    public String toString () {
        return "Student ["+getId_user()+
                      ", "+getUsername()+
                      ", "+getUserPassword()+
                      ", "+getRole();
    }
}
