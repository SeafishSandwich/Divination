package com.example.demo.JDBC.entity;

public class UsersHexa {
    private byte[] imageBytes;
    private String question;

    public UsersHexa(byte[] imageBytes, String question) {
        this.imageBytes = imageBytes;
        this.question = question;
    }

    public byte[] getImageBytes() { return imageBytes; }
    public String getQuestion() { return question; }
}

