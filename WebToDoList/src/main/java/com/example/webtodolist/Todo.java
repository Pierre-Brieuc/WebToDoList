package com.example.webtodolist;

public class Todo {
    private int id_todo;
    private String description;

    public Todo(int id_todo, String description) {
        this.id_todo = id_todo;
        this.description = description;
    }
    public Todo(String description) {
        this.description = description;
    }

    public int getId_todo() {
        return id_todo;
    }

    public void setId_todo(int id_todo) {
        this.id_todo = id_todo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString () {
        return "Todo " + getId_todo() + " / description : " + getDescription();
    }
}
