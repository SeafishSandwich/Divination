package com.example.demo.JDBC.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserSession {
    @Id
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
