package com.example.demo.JDBC.entity;

public class UserSession {
    private int userId;
    private String username;

    public void setUser(int id, String name) {
        this.userId = id;
        this.username = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void clear() {
        this.userId = 0;
        this.username = null;
    }
}
