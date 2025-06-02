package com.example.demo.JDBC.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UsersHexa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 设置自增主键
    private Long id;

    private byte[] imageBytes;
    private String question;

    public UsersHexa() {}

    public UsersHexa(byte[] imageBytes, String question) {
        this.imageBytes = imageBytes;
        this.question = question;
    }

    // getter 和 setter

    public Long getId() {
        return id;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
