package com.example.demo.JDBC.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UsersHexa {
    @Id
    private byte[] imageBytes;
    private String question;

    public UsersHexa(byte[] imageBytes, String question) {
        this.imageBytes = imageBytes;
        this.question = question;
    }

    public byte[] getImageBytes() { return imageBytes; }
    public String getQuestion() { return question; }
}

