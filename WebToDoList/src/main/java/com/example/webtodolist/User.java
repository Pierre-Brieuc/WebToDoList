package com.example.webtodolist;

public class User {
    private int id_user;
    private String username;
    private String userPassword;
    private String role;

    public User(int id_user, String username, String userPassword, String role) {
        this.id_user = id_user;
        this.username = username;
        this.userPassword = userPassword;
        this.role = role;
    }
    public User(String username, String userPassword, String role) {
        this.username = username;
        this.userPassword = userPassword;
        this.role = role;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
